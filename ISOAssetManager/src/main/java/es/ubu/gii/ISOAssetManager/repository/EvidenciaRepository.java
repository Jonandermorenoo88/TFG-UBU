package es.ubu.gii.ISOAssetManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ubu.gii.ISOAssetManager.model.Evidencia;

public interface EvidenciaRepository extends JpaRepository<Evidencia, Long> {

    // Para el map evidenciasPorPregunta[p.id] en CuestionarioController
    List<Evidencia> findByEmpresa_IdAndControl_IdOrderByFechaSubidaDesc(Long empresaId, String controlId);

}