package es.ubu.gii.ISOAssetManager.repository;

import es.ubu.gii.ISOAssetManager.model.Bloque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad {@link Bloque}.
 * <p>
 * Permite acceder a los bloques de la cadena (Blockchain) y realizar búsquedas
 * específicas
 * por respuesta o evidencia.
 * </p>
 */
@Repository
public interface BloqueRepository extends JpaRepository<Bloque, Long> {
    /**
     * Obtiene el último bloque añadido a la cadena.
     * <p>
     * Se utiliza para obtener el hash previo necesario para crear un nuevo bloque.
     * </p>
     * 
     * @return El último bloque, si existe.
     */
    Optional<Bloque> findTopByOrderByIdDesc();

    /**
     * Busca un bloque asociado a una respuesta específica.
     *
     * @param respuestaId ID de la respuesta.
     * @return El bloque asociado, si existe.
     */
    Optional<Bloque> findByRespuestaId(Long respuestaId);

    /**
     * Busca un bloque asociado a una evidencia específica.
     *
     * @param evidenciaId ID de la evidencia.
     * @return El bloque asociado, si existe.
     */
    Optional<Bloque> findByEvidenciaId(Long evidenciaId);
}
