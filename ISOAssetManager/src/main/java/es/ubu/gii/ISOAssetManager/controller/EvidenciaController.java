package es.ubu.gii.ISOAssetManager.controller;

import es.ubu.gii.ISOAssetManager.config.details.SuperCustomerUserDetails;
import es.ubu.gii.ISOAssetManager.model.Control;
import es.ubu.gii.ISOAssetManager.model.Evidencia;
import es.ubu.gii.ISOAssetManager.model.Empresa;
import es.ubu.gii.ISOAssetManager.model.Usuario;
import es.ubu.gii.ISOAssetManager.repository.ControlRepository;
import es.ubu.gii.ISOAssetManager.repository.EvidenciaRepository;
import es.ubu.gii.ISOAssetManager.repository.EmpresaRepository;
import es.ubu.gii.ISOAssetManager.repository.UsuarioRepository;
import es.ubu.gii.ISOAssetManager.service.BlockchainService;
import es.ubu.gii.ISOAssetManager.repository.BloqueRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Controlador para la gestión de evidencias (archivos adjuntos) en los
 * controles.
 * <p>
 * Maneja el ciclo de vida de las evidencias: subida, descarga, eliminación y
 * verificación.
 * Incluye funcionalidades avanzadas como:
 * <ul>
 * <li>Validación de tipo y tamaño de archivo.</li>
 * <li>Cálculo de hash SHA-256 para integridad.</li>
 * <li>Firma digital opcional con clave privada del usuario.</li>
 * <li>Registro inmutable en Blockchain.</li>
 * </ul>
 * </p>
 */
@Controller
@RequestMapping("/empresas/{empresaId}/cuestionario/evidencias")
public class EvidenciaController {

    private static final Logger logger = LoggerFactory.getLogger(EvidenciaController.class);
    private static final long MAX_FILE_SIZE = 50 * 1024 * 1024; // 50 MB
    private static final int BUFFER_SIZE = 8192;

    private final EvidenciaRepository evidenciaRepo;
    private final EmpresaRepository empresaRepo;
    private final ControlRepository controlRepo;
    private final UsuarioRepository usuarioRepo;
    private final BlockchainService blockchainService;
    private final BloqueRepository bloqueRepo;

    private final Path rootLocation = Paths.get("uploads/evidencias");

    /**
     * Constructor con inyección de dependencias.
     *
     * @param evidenciaRepo     Repositorio de evidencias.
     * @param empresaRepo       Repositorio de empresas.
     * @param controlRepo       Repositorio de controles.
     * @param usuarioRepo       Repositorio de usuarios.
     * @param blockchainService Servicio de blockchain.
     * @param bloqueRepo        Repositorio de bloques.
     */
    public EvidenciaController(EvidenciaRepository evidenciaRepo,
            EmpresaRepository empresaRepo,
            ControlRepository controlRepo,
            UsuarioRepository usuarioRepo,
            BlockchainService blockchainService,
            BloqueRepository bloqueRepo) {
        this.evidenciaRepo = evidenciaRepo;
        this.empresaRepo = empresaRepo;
        this.controlRepo = controlRepo;
        this.usuarioRepo = usuarioRepo;
        this.blockchainService = blockchainService;
        this.bloqueRepo = bloqueRepo;
    }

    /**
     * Sube una nueva evidencia para un control específico.
     * <p>
     * Realiza validaciones de seguridad (tamaño, tipo MIME), guarda el archivo
     * físico,
     * calcula su hash, intenta firmarlo digitalmente si el usuario tiene clave
     * privada,
     * guarda los metadatos en BD y registra la operación en la blockchain.
     * </p>
     *
     * @param empresaId ID de la empresa.
     * @param controlId ID del control.
     * @param archivo   Archivo subido (MultipartFile).
     * @param auth      Información de autenticación del usuario.
     * @return Redirección a la lista de preguntas del control con parámetro de
     *         éxito o error.
     */
    @PostMapping("/subir")
    public String subirEvidencia(@PathVariable Long empresaId,
            @RequestParam("controlId") String controlId,
            @RequestParam("archivo") MultipartFile archivo,
            Authentication auth) {
        try {
            if (archivo.isEmpty()) {
                return "redirect:/empresas/" + empresaId +
                        "/cuestionario/control/" + controlId + "/preguntas";
            }

            // ====== Validar tamaño de archivo ======
            if (archivo.getSize() > MAX_FILE_SIZE) {
                logger.warn("Intento de subida de archivo demasiado grande: {} bytes (máx: {})",
                        archivo.getSize(), MAX_FILE_SIZE);
                return "redirect:/empresas/" + empresaId +
                        "/cuestionario/control/" + controlId + "/preguntas?error=2";
            }

            // ====== Crear directorio por control si no existe ======
            Path absRoot = rootLocation.toAbsolutePath().resolve(controlId);
            Files.createDirectories(absRoot);
            logger.debug("Directorio de evidencias para control {}: {}", controlId, absRoot);

            // ====== Nombre original e interno + sanitización ======
            String nombreOriginal = archivo.getOriginalFilename();
            if (nombreOriginal == null || nombreOriginal.isBlank()) {
                nombreOriginal = "archivo";
            }
            // Sanitizar nombre: solo letras, números, guiones y puntos
            nombreOriginal = nombreOriginal.replaceAll("[^a-zA-Z0-9._-]", "_");

            String extension = "";
            int dot = nombreOriginal.lastIndexOf('.');
            if (dot != -1) {
                extension = nombreOriginal.substring(dot).toLowerCase();
            }
            String nombreInterno = UUID.randomUUID().toString() + extension;

            // ====== Validar MIME type ======
            String mimeType = archivo.getContentType();
            if (mimeType == null || (!mimeType.startsWith("image/") &&
                    !mimeType.startsWith("application/pdf") &&
                    !mimeType.contains("wordprocessingml"))) {
                logger.warn("MIME type no permitido para upload: {}", mimeType);
                return "redirect:/empresas/" + empresaId +
                        "/cuestionario/control/" + controlId + "/preguntas?error=3";
            }

            // ====== Copiar fichero físico ======
            Path destino = absRoot.resolve(nombreInterno).normalize();
            try (InputStream is = archivo.getInputStream()) {
                Files.copy(is, destino, StandardCopyOption.REPLACE_EXISTING);
            }
            logger.debug("Fichero copiado a {}", destino);

            // ====== Cargar empresa y control ======
            Empresa empresa = empresaRepo.findById(empresaId)
                    .orElseThrow(() -> new IllegalStateException("Empresa no encontrada: " + empresaId));
            Control control = controlRepo.findById(controlId)
                    .orElseThrow(() -> new IllegalStateException("Control no encontrado: " + controlId));
            logger.debug("Empresa y control cargados: {} / {}", empresa.getId(), control.getId());

            // ====== Usuario autenticado (puede ser null) ======
            Usuario usuario = null;
            if (auth != null) {
                usuario = usuarioRepo.findByEmail(auth.getName()).orElse(null);
            }

            // ========= HASH del fichero (SHA-256) =========
            byte[] hash;
            try {
                hash = calcularHashSHA256(destino);
                logger.debug("Hash calculado ({} bytes)", hash.length);
            } catch (Exception e) {
                logger.error("Error calculando hash de la evidencia", e);
                return "redirect:/empresas/" + empresaId +
                        "/cuestionario/control/" + controlId + "/preguntas?error=1";
            }

            // ========= FIRMA RSA del hash (opcional) =========
            byte[] firma = null;
            if (auth != null && auth.getPrincipal() instanceof SuperCustomerUserDetails details) {
                try {
                    PrivateKey privateKey = details.getPrivateKey();
                    if (privateKey != null) {
                        Signature signature = Signature.getInstance("SHA256withRSA");
                        signature.initSign(privateKey);
                        signature.update(hash);
                        firma = signature.sign();
                        logger.debug("Firma generada ({} bytes)", firma.length);
                    } else {
                        logger.debug("Usuario autenticado sin clave privada, no se firma la evidencia");
                    }
                } catch (Exception e) {
                    logger.warn("No se pudo firmar la evidencia con la clave privada del usuario", e);
                }
            }

            // ====== Construir entidad Evidencia ======
            Evidencia ev = new Evidencia();
            ev.setEmpresa(empresa);
            ev.setControl(control);
            ev.setUsuario(usuario);
            ev.setFechaSubida(LocalDateTime.now());
            ev.setNombreArchivo(nombreOriginal);
            ev.setRutaFichero(destino.toAbsolutePath().toString());
            ev.setContentType(archivo.getContentType());
            ev.setTamanoBytes(archivo.getSize());
            ev.setHash(hash);
            ev.setFirma(firma);

            logger.debug("Guardando evidencia en BD: nombre={}, ruta={}", ev.getNombreArchivo(), ev.getRutaFichero());
            evidenciaRepo.save(ev);
            logger.info("Evidencia guardada correctamente para empresa={}, control={}, id={}",
                    empresaId, controlId, ev.getId());

            // --- BLOCKCHAIN INTEGRATION ---
            // Registramos la evidencia en la cadena de bloques
            blockchainService.crearBloque(ev, usuario, auth != null ? auth.getPrincipal() : null);
            // ------------------------------

            return "redirect:/empresas/" + empresaId + "/cuestionario/control/" + controlId + "/preguntas";

        } catch (IOException ioe) {
            logger.error("I/O error al manejar fichero de evidencia", ioe);
            return "redirect:/empresas/" + empresaId +
                    "/cuestionario/control/" + controlId + "/preguntas?error=1";
        } catch (IllegalStateException ise) {
            logger.error("Entidad no encontrada al subir evidencia", ise);
            return "redirect:/empresas/" + empresaId +
                    "/cuestionario/control/" + controlId + "/preguntas?error=1";
        } catch (DataIntegrityViolationException dive) {
            logger.error("Violación de integridad al guardar evidencia en BD: {}",
                    dive.getMostSpecificCause() != null
                            ? dive.getMostSpecificCause().getMessage()
                            : dive.getMessage(),
                    dive);
            return "redirect:/empresas/" + empresaId +
                    "/cuestionario/control/" + controlId + "/preguntas?error=1";
        } catch (Exception ex) {
            logger.error("Error al subir evidencia (empresaId={}, controlId={})",
                    empresaId, controlId, ex);
            return "redirect:/empresas/" + empresaId +
                    "/cuestionario/control/" + controlId + "/preguntas?error=1";
        }
    }

    /**
     * Descarga el archivo físico de una evidencia.
     *
     * @param empresaId   ID de la empresa.
     * @param evidenciaId ID de la evidencia a descargar.
     * @return ResponseEntity con el recurso del archivo para su descarga.
     */
    @GetMapping("/{evidenciaId}/descargar")
    public ResponseEntity<Resource> descargarEvidencia(@PathVariable Long empresaId,
            @PathVariable Long evidenciaId) {
        try {
            Evidencia evidencia = evidenciaRepo.findById(evidenciaId)
                    .orElseThrow(() -> new IllegalStateException("Evidencia no encontrada: " + evidenciaId));

            Path rutaFichero = Paths.get(evidencia.getRutaFichero());
            Resource recurso = new UrlResource(rutaFichero.toUri());

            if (!recurso.exists() || !recurso.isReadable()) {
                logger.warn("Fichero de evidencia no existe o no es legible: {}", rutaFichero);
                return ResponseEntity.notFound().build();
            }

            String contentType = evidencia.getContentType() != null
                    ? evidencia.getContentType()
                    : MediaType.APPLICATION_OCTET_STREAM_VALUE;

            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType(contentType))
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment; filename=\"" + evidencia.getNombreArchivo() + "\"")
                    .body(recurso);
        } catch (IllegalStateException e) {
            logger.error("Evidencia no encontrada: {}", evidenciaId, e);
            return ResponseEntity.notFound().build();
        } catch (MalformedURLException e) {
            logger.error("Ruta de fichero inválida para evidencia: {}", evidenciaId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * Elimina una evidencia del sistema.
     * <p>
     * Borra el archivo físico, el registro en BD y el bloque de blockchain asociado
     * (nota: borrar el bloque rompe la cadena, pero es necesario para la
     * funcionalidad de borrado).
     * </p>
     *
     * @param empresaId   ID de la empresa.
     * @param evidenciaId ID de la evidencia a eliminar.
     * @return Redirección a la lista de preguntas del control.
     */
    @PostMapping("/{evidenciaId}/eliminar")
    public String eliminarEvidencia(@PathVariable Long empresaId,
            @PathVariable Long evidenciaId) {

        Evidencia ev = evidenciaRepo.findById(evidenciaId).orElseThrow();
        String controlId = ev.getControl().getId();

        if (ev.getRutaFichero() != null) {
            Path ruta = Paths.get(ev.getRutaFichero());
            try {
                Files.deleteIfExists(ruta);
            } catch (Exception ex) {
                logger.warn("No se pudo borrar el fichero físico de la evidencia {}", evidenciaId, ex);
            }
        }

        // Eliminar bloque asociado si existe
        bloqueRepo.findByEvidenciaId(ev.getId()).ifPresent(bloqueRepo::delete);

        evidenciaRepo.delete(ev);

        return "redirect:/empresas/" + empresaId +
                "/cuestionario/control/" + controlId + "/preguntas";
    }

    /**
     * Muestra la vista de verificación de integridad y autenticidad de una
     * evidencia.
     * <p>
     * Verifica dos aspectos:
     * 1. Integridad: Recalcula el hash del archivo físico y lo compara con el
     * almacenado.
     * 2. Autenticidad: Si está firmada, verifica la firma digital usando la clave
     * pública del usuario.
     * </p>
     *
     * @param empresaId   ID de la empresa.
     * @param evidenciaId ID de la evidencia a verificar.
     * @param model       Modelo para pasar los resultados de la verificación.
     * @return Nombre de la vista de verificación de evidencia.
     */
    @GetMapping("/{evidenciaId}/verificar")
    public String verificarEvidencia(@PathVariable Long empresaId,
            @PathVariable Long evidenciaId,
            org.springframework.ui.Model model) {

        Evidencia ev = evidenciaRepo.findById(evidenciaId).orElse(null);
        if (ev == null) {
            model.addAttribute("error", "La evidencia no existe.");
            return "verificar-evidencia";
        }

        // 1️ Recalcular hash del fichero
        boolean hashCoincide = false;
        try {
            Path ruta = Paths.get(ev.getRutaFichero());
            byte[] hashActual = calcularHashSHA256(ruta);
            hashCoincide = java.util.Arrays.equals(hashActual, ev.getHash());
        } catch (Exception e) {
            model.addAttribute("error", "Error al calcular el hash del fichero.");
            return "verificar-evidencia";
        }

        // 2 Verificación de firma
        boolean firmaValida = false;
        try {
            Usuario u = ev.getUsuario();
            if (u != null && u.getPublicKey() != null && ev.getFirma() != null) {
                java.security.KeyFactory keyFactory = java.security.KeyFactory.getInstance("RSA");
                java.security.spec.X509EncodedKeySpec pubSpec = new java.security.spec.X509EncodedKeySpec(
                        u.getPublicKey());
                java.security.PublicKey publicKey = keyFactory.generatePublic(pubSpec);

                Signature sig = Signature.getInstance("SHA256withRSA");
                sig.initVerify(publicKey);
                sig.update(ev.getHash());
                firmaValida = sig.verify(ev.getFirma());
            }
        } catch (Exception e) {
            model.addAttribute("error", "Error al verificar la firma digital.");
        }

        // 3️ Datos para la vista
        model.addAttribute("empresaId", empresaId);
        model.addAttribute("evidencia", ev);
        model.addAttribute("hashCoincide", hashCoincide);
        model.addAttribute("firmaValida", firmaValida);

        return "verificar-evidencia"; // Thymeleaf
    }

    /**
     * Método privado para calcular el hash SHA-256 de un fichero.
     * 
     * @param rutaFichero ruta al fichero
     * @return array de bytes del hash
     * @throws Exception si hay error al leer el fichero
     */
    private byte[] calcularHashSHA256(Path rutaFichero) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        try (InputStream is = Files.newInputStream(rutaFichero)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;
            while ((bytesRead = is.read(buffer)) != -1) {
                md.update(buffer, 0, bytesRead);
            }
        }
        return md.digest();
    }
}
