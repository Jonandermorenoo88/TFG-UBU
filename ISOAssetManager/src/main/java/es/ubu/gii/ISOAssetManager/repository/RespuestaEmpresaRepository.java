package es.ubu.gii.ISOAssetManager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import es.ubu.gii.ISOAssetManager.model.RespuestaEmpresa;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad
 * {@link RespuestaEmpresa}.
 * <p>
 * Permite gestionar las respuestas dadas por las empresas a las preguntas de
 * los controles.
 * </p>
 */
public interface RespuestaEmpresaRepository extends JpaRepository<RespuestaEmpresa, Long> {

    /**
     * Busca las respuestas de una empresa para un control específico.
     *
     * @param empresaId ID de la empresa.
     * @param controlId ID del control (ej. "A.5.1").
     * @return Lista de respuestas encontradas.
     */
    List<RespuestaEmpresa> findByEmpresa_IdAndPregunta_Control_Id(Long empresaId, String controlId);

    /**
     * Busca la respuesta de una empresa a una pregunta específica.
     *
     * @param empresaId  ID de la empresa.
     * @param preguntaId ID de la pregunta.
     * @return La respuesta si existe.
     */
    Optional<RespuestaEmpresa> findByEmpresa_IdAndPregunta_Id(Long empresaId, Long preguntaId);

    /**
     * Busca las respuestas de una empresa filtradas por categoría de control.
     *
     * @param empresaId   ID de la empresa.
     * @param categoriaId ID de la categoría (ej. "A.5").
     * @return Lista de respuestas de la categoría.
     */
    @Query("""
                select re
                from RespuestaEmpresa re
                where re.empresa.id = :emp
                  and re.pregunta.control.categoria.id = :cat
            """)
    List<RespuestaEmpresa> findByEmpresaIdAndCategoriaId(@Param("emp") Long empresaId,
            @Param("cat") String categoriaId);

    /**
     * Elimina todas las respuestas asociadas a una pregunta específica.
     *
     * @param preguntaId ID de la pregunta.
     */
    @Modifying
    @Transactional
    @Query("delete from RespuestaEmpresa re where re.pregunta.id = :preguntaId")
    void deleteByPreguntaId(@Param("preguntaId") Long preguntaId);

    /**
     * Elimina la respuesta de una empresa a una pregunta específica.
     *
     * @param empresaId  ID de la empresa.
     * @param preguntaId ID de la pregunta.
     */
    @Modifying
    @Transactional
    @Query("delete from RespuestaEmpresa re where re.empresa.id = :emp and re.pregunta.id = :preg")
    void deleteByEmpresaIdAndPreguntaId(@Param("emp") Long empresaId, @Param("preg") Long preguntaId);
}
