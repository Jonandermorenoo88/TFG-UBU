package es.ubu.gii.ISOAssetManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ubu.gii.ISOAssetManager.model.Activo;

public interface ActivoRepository extends JpaRepository<Activo, Long> {
    
    // Buscar activos por ID de empresa
    List<Activo> findByEmpresaId(Long empresaId);
}
