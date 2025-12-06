package es.ubu.gii.ISOAssetManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ubu.gii.ISOAssetManager.model.Evidencia;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad
 * {@link Evidencia}.
 * <p>
 * Permite acceder a los archivos de evidencia subidos por los usuarios para
 * justificar
 * el cumplimiento de los controles.
 * </p>
 */
public interface EvidenciaRepository extends JpaRepository<Evidencia, Long> {

    /**
     * Busca las evidencias asociadas a una empresa y un control específico,
     * ordenadas por fecha de subida (más recientes primero).
     *
     * @param empresaId ID de la empresa.
     * @param controlId ID del control (ej. "A.5.1").
     * @return Lista de evidencias encontradas.
     */
    List<Evidencia> findByEmpresa_IdAndControl_IdOrderByFechaSubidaDesc(Long empresaId, String controlId);

}