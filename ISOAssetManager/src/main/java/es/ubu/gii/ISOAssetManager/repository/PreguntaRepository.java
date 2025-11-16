// PreguntaRepository.java
package es.ubu.gii.ISOAssetManager.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import es.ubu.gii.ISOAssetManager.model.Pregunta;

public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {
    List<Pregunta> findByControl_Id(String controlId);
    List<Pregunta> findByControl_Categoria_Id(String categoriaId);
    List<Pregunta> findByControl_IdOrderByIdAsc(String controlId);
}