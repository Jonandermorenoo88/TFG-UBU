// RespuestaPosibleRepository.java
package es.ubu.gii.ISOAssetManager.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import es.ubu.gii.ISOAssetManager.model.RespuestaPosible;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad
 * {@link RespuestaPosible}.
 * <p>
 * Permite acceder a las opciones de respuesta predefinidas (ej. "Sí", "No",
 * "Parcialmente")
 * disponibles para las preguntas.
 * </p>
 */
public interface RespuestaPosibleRepository extends JpaRepository<RespuestaPosible, Long> {
    /**
     * Busca una opción de respuesta por su texto (ignorando mayúsculas/minúsculas).
     *
     * @param textoOpcion Texto de la opción (ej. "Sí").
     * @return La opción encontrada, si existe.
     */
    Optional<RespuestaPosible> findByTextoOpcionIgnoreCase(String textoOpcion);

    /**
     * Obtiene todas las opciones de respuesta ordenadas por su campo 'orden' y
     * luego por ID.
     * <p>
     * Se utiliza para mostrar las opciones en el orden correcto en la interfaz de
     * usuario.
     * </p>
     * 
     * @return Lista de opciones ordenadas.
     */
    List<RespuestaPosible> findAllByOrderByOrdenAscIdAsc();
}