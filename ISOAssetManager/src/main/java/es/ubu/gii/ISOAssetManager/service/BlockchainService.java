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

        // 5. Firmar el bloque si el usuario tiene clave privada (Autenticado con
        // certificado)
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
                // No fallamos la transacción, simplemente se guarda sin firma
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
        // Incluimos el hash del fichero para vincularlo criptográficamente
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
        nuevoBloque.setEvidencia(evidencia); // Vinculamos la evidencia
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
     * Valida la integridad de toda la cadena de bloques.
     * 
     * @return true si la cadena es válida, false si ha sido manipulada.
     */
    public boolean validarCadena() {
        List<Bloque> cadena = bloqueRepo.findAll(); // Ojo: en prod esto debería ser paginado o iterativo

        for (int i = 0; i < cadena.size(); i++) {
            Bloque actual = cadena.get(i);

            // 1. Validar Hash del bloque actual
            String hashCalculado = calcularHashBloque(actual);
            if (!actual.getHash().equals(hashCalculado)) {
                logger.error("Integridad rota en bloque ID {}: Hash actual no coincide", actual.getId());
                return false;
            }

            // 2. Validar enlace con el anterior
            if (i > 0) {
                Bloque anterior = cadena.get(i - 1);
                if (!actual.getPreviousHash().equals(anterior.getHash())) {
                    logger.error("Integridad rota en bloque ID {}: PreviousHash no coincide con bloque anterior",
                            actual.getId());
                    return false;
                }
            } else {
                // Bloque génesis
                if (!"0".equals(actual.getPreviousHash())) {
                    logger.error("Bloque génesis inválido");
                    return false;
                }
            }
        }
        return true;
    }

    private String calcularHashBloque(Bloque bloque) {
        String input = bloque.getPreviousHash()
                + bloque.getData()
                + Long.toString(bloque.getTimeStamp());
        return aplicarSha256(input);
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
