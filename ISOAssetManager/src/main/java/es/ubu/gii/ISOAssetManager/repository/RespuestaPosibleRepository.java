// RespuestaPosibleRepository.java
package es.ubu.gii.ISOAssetManager.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import es.ubu.gii.ISOAssetManager.model.RespuestaPosible;

public interface RespuestaPosibleRepository extends JpaRepository<RespuestaPosible, Long> {
    Optional<RespuestaPosible> findByTextoOpcionIgnoreCase(String textoOpcion);
    List<RespuestaPosible> findAllByOrderByOrdenAscIdAsc();
}