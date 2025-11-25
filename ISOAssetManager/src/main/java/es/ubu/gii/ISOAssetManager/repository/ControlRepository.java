// ControlRepository.java
package es.ubu.gii.ISOAssetManager.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import es.ubu.gii.ISOAssetManager.model.Control;

public interface ControlRepository extends JpaRepository<Control, String> {
    List<Control> findByCategoria_IdOrderByOrdenAscIdAsc(String categoriaId);
}
