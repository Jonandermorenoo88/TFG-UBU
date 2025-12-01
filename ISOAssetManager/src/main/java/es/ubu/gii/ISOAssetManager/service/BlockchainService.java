package es.ubu.gii.ISOAssetManager.service;

import es.ubu.gii.ISOAssetManager.config.details.SuperCustomerUserDetails;
import es.ubu.gii.ISOAssetManager.model.Bloque;
import es.ubu.gii.ISOAssetManager.model.Evidencia;
import es.ubu.gii.ISOAssetManager.model.RespuestaEmpresa;
import es.ubu.gii.ISOAssetManager.model.Usuario;
import es.ubu.gii.ISOAssetManager.repository.BloqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.PrivateKey;
import java.security.Signature;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class BlockchainService {

    private static final Logger logger = LoggerFactory.getLogger(BlockchainService.class);
    private final BloqueRepository bloqueRepo;

    public BlockchainService(BloqueRepository bloqueRepo) {
        this.bloqueRepo = bloqueRepo;
    }

    /**
     * Crea un nuevo bloque en la cadena para una respuesta dada.
     */
    public Bloque crearBloque(RespuestaEmpresa respuesta, Usuario usuario, Object principal) {
        // 1. Obtener el hash del último bloque (o "0" si es el primero)
        String previousHash = "0";
        Optional<Bloque> lastBlock = bloqueRepo.findTopByOrderByIdDesc();
        if (lastBlock.isPresent()) {
            previousHash = lastBlock.get().getHash();
        }

        // 2. Preparar los datos del bloque (JSON simple)
        String data = String.format("{\"id\":%d,\"empresa\":%d,\"pregunta\":%d,\"respuesta\":%d,\"fecha\":\"%s\"}",
                respuesta.getId(),
                respuesta.getEmpresa().getId(),
                respuesta.getPregunta().getId(),
                respuesta.getRespuesta().getId(),
                respuesta.getFechaRespuesta().toString());

        // 3. Crear instancia del bloque
        Bloque nuevoBloque = new Bloque();
        nuevoBloque.setPreviousHash(previousHash);
        nuevoBloque.setData(data);
        nuevoBloque.setRespuesta(respuesta);
        nuevoBloque.setTimeStamp(System.currentTimeMillis());

        // 4. Calcular Hash del bloque actual
        String hashCalculado = calcularHashBloque(nuevoBloque);
        nuevoBloque.setHash(hashCalculado);

        // 5. Firmar el bloque si el usuario tiene clave privada
        if (principal instanceof SuperCustomerUserDetails details) {
            try {
                PrivateKey privateKey = details.getPrivateKey();
                if (privateKey != null) {
                    Signature signature = Signature.getInstance("SHA256withRSA");
                    signature.initSign(privateKey);
                    signature.update(hashCalculado.getBytes(StandardCharsets.UTF_8));
                    nuevoBloque.setFirma(signature.sign());
                    logger.debug("Bloque firmado correctamente por usuario {}", usuario.getEmail());
                }
            } catch (Exception e) {
                logger.error("Error al firmar el bloque", e);
            }
        }

        return bloqueRepo.save(nuevoBloque);
    }

    /**
     * Crea un nuevo bloque en la cadena para una evidencia subida.
     */
    public Bloque crearBloque(Evidencia evidencia, Usuario usuario, Object principal) {
        // 1. Obtener el hash del último bloque
        String previousHash = "0";
        Optional<Bloque> lastBlock = bloqueRepo.findTopByOrderByIdDesc();
        if (lastBlock.isPresent()) {
            previousHash = lastBlock.get().getHash();
        }

        // 2. Preparar los datos del bloque (JSON simple)
        String hashEvidenciaHex = bytesToHex(evidencia.getHash());
        String data = String.format(
                "{\"id\":%d,\"empresa\":%d,\"control\":\"%s\",\"archivo\":\"%s\",\"hashEvidencia\":\"%s\",\"fecha\":\"%s\"}",
                evidencia.getId(),
                evidencia.getEmpresa().getId(),
                evidencia.getControl().getId(),
                evidencia.getNombreArchivo(),
                hashEvidenciaHex,
                evidencia.getFechaSubida().toString());

        // 3. Crear instancia del bloque
        Bloque nuevoBloque = new Bloque();
        nuevoBloque.setPreviousHash(previousHash);
        nuevoBloque.setData(data);
        nuevoBloque.setEvidencia(evidencia);
        nuevoBloque.setTimeStamp(System.currentTimeMillis());

        // 4. Calcular Hash del bloque actual
        String hashCalculado = calcularHashBloque(nuevoBloque);
        nuevoBloque.setHash(hashCalculado);

        // 5. Firmar el bloque
        if (principal instanceof SuperCustomerUserDetails details) {
            try {
                PrivateKey privateKey = details.getPrivateKey();
                if (privateKey != null) {
                    Signature signature = Signature.getInstance("SHA256withRSA");
                    signature.initSign(privateKey);
                    signature.update(hashCalculado.getBytes(StandardCharsets.UTF_8));
                    nuevoBloque.setFirma(signature.sign());
                    logger.debug("Bloque de evidencia firmado correctamente por usuario {}", usuario.getEmail());
                }
            } catch (Exception e) {
                logger.error("Error al firmar el bloque de evidencia", e);
            }
        }

        return bloqueRepo.save(nuevoBloque);
    }

    /**
     * Valida la integridad de la cadena completa.
     */
    public boolean validarCadena() {
        List<Bloque> cadena = bloqueRepo.findAll();
        cadena.sort(Comparator.comparing(Bloque::getId));

        for (int i = 0; i < cadena.size(); i++) {
            Bloque actual = cadena.get(i);
            Bloque anterior = (i == 0) ? null : cadena.get(i - 1);

            // 1. Recalcular hash del bloque actual
            String hashCalculado = calcularHashBloque(actual);
            if (!hashCalculado.equals(actual.getHash())) {
                logger.error("Integridad rota en bloque ID {}: Hash actual no coincide", actual.getId());
                return false;
            }

            // 2. Verificar enlace con el anterior
            if (anterior != null) {
                if (!actual.getPreviousHash().equals(anterior.getHash())) {
                    logger.error("Integridad rota en bloque ID {}: Hash previo no coincide con el del bloque anterior",
                            actual.getId());
                    return false;
                }
            } else {
                // Bloque génesis
                if (!"0".equals(actual.getPreviousHash())) {
                    logger.error("Bloque génesis ID {} tiene previousHash inválido", actual.getId());
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Valida la integridad de los bloques asociados a un control específico.
     */
    public boolean validarIntegridadControl(String controlId, Long empresaId) {
        List<Bloque> cadena = bloqueRepo.findAll();
        List<Bloque> bloquesControl = cadena.stream()
                .filter(b -> perteneceAControl(b, controlId, empresaId))
                .sorted(Comparator.comparing(Bloque::getId))
                .toList();

        if (bloquesControl.isEmpty()) {
            return true;
        }

        for (Bloque b : bloquesControl) {
            String hashCalculado = calcularHashBloque(b);
            if (!hashCalculado.equals(b.getHash())) {
                logger.error("Integridad rota en bloque ID {} (Control {}): Hash no coincide", b.getId(), controlId);
                return false;
            }
        }

        return true;
    }

    private boolean perteneceAControl(Bloque b, String controlId, Long empresaId) {
        if (b.getRespuesta() != null) {
            return b.getRespuesta().getEmpresa().getId().equals(empresaId) &&
                    b.getRespuesta().getPregunta().getControl().getId().equals(controlId);
        }
        if (b.getEvidencia() != null) {
            return b.getEvidencia().getEmpresa().getId().equals(empresaId) &&
                    b.getEvidencia().getControl().getId().equals(controlId);
        }
        return false;
    }

    private String calcularHashBloque(Bloque b) {
        return aplicarSha256(
                b.getPreviousHash()
                        + Long.toString(b.getTimeStamp())
                        + b.getData());
    }

    private String aplicarSha256(String input) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(input.getBytes(StandardCharsets.UTF_8));
            return bytesToHex(hash);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        if (bytes == null)
            return "";
        StringBuilder hexString = new StringBuilder();
        for (byte b : bytes) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1)
                hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }
}
