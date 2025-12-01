package es.ubu.gii.ISOAssetManager.repository;

import es.ubu.gii.ISOAssetManager.model.Bloque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BloqueRepository extends JpaRepository<Bloque, Long> {
    // Find the last block to link the chain
    Optional<Bloque> findTopByOrderByIdDesc();

    // Find block by answer ID
    Optional<Bloque> findByRespuestaId(Long respuestaId);

    // Find block by evidence ID
    Optional<Bloque> findByEvidenciaId(Long evidenciaId);
}
