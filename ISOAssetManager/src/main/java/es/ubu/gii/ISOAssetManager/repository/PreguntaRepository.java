package es.ubu.gii.ISOAssetManager.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import es.ubu.gii.ISOAssetManager.model.Pregunta;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad
 * {@link Pregunta}.
 * <p>
 * Permite acceder a las preguntas de evaluación asociadas a los controles de
 * seguridad.
 * </p>
 */
public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    /**
     * Busca todas las preguntas asociadas a un control específico.
     *
     * @param controlId ID del control (ej. "A.5.1").
     * @return Lista de preguntas del control.
     */
    List<Pregunta> findByControl_Id(String controlId);

    /**
     * Busca todas las preguntas asociadas a una categoría de controles.
     *
     * @param categoriaId ID de la categoría (ej. "A.5").
     * @return Lista de preguntas de la categoría.
     */
    List<Pregunta> findByControl_Categoria_Id(String categoriaId);

    /**
     * Busca todas las preguntas asociadas a un control específico, ordenadas por su
     * ID de forma ascendente.
     *
     * @param controlId ID del control.
     * @return Lista de preguntas ordenadas.
     */
    List<Pregunta> findByControl_IdOrderByIdAsc(String controlId);
}