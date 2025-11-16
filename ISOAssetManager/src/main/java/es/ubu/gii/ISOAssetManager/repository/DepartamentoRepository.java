// DepartamentoRepository.java
package es.ubu.gii.ISOAssetManager.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import es.ubu.gii.ISOAssetManager.model.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    // Busca por descripción dentro de una empresa concreta
    Optional<Departamento> findByDescripcionAndEmpresaId(String descripcion, Long empresaId);
}

