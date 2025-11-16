package es.ubu.gii.ISOAssetManager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import es.ubu.gii.ISOAssetManager.model.RespuestaEmpresa;

public interface RespuestaEmpresaRepository extends JpaRepository<RespuestaEmpresa, Long> {

    // Para precargar las respuestas seleccionadas en un control
    List<RespuestaEmpresa> findByEmpresa_IdAndPregunta_Control_Id(Long empresaId, String controlId);

    // Para crear/actualizar una respuesta de una pregunta concreta
    Optional<RespuestaEmpresa> findByEmpresa_IdAndPregunta_Id(Long empresaId, Long preguntaId);

    // (Opcional) por categoría
    @Query("""
        select re
        from RespuestaEmpresa re
        where re.empresa.id = :emp
          and re.pregunta.control.categoria.id = :cat
    """)
    List<RespuestaEmpresa> findByEmpresaIdAndCategoriaId(@Param("emp") Long empresaId,
                                                         @Param("cat") String categoriaId);

    // Borrar todas las respuestas asociadas a una pregunta
    @Modifying
    @Transactional
    @Query("delete from RespuestaEmpresa re where re.pregunta.id = :preguntaId")
    void deleteByPreguntaId(@Param("preguntaId") Long preguntaId);

    // Borrar de una empresa para una pregunta concreta (si lo necesitas)
    @Modifying
    @Transactional
    @Query("delete from RespuestaEmpresa re where re.empresa.id = :emp and re.pregunta.id = :preg")
    void deleteByEmpresaIdAndPreguntaId(@Param("emp") Long empresaId, @Param("preg") Long preguntaId);
}
