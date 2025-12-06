package es.ubu.gii.ISOAssetManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ubu.gii.ISOAssetManager.model.Activo;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad {@link Activo}.
 */
public interface ActivoRepository extends JpaRepository<Activo, Long> {

    /**
     * Busca todos los activos pertenecientes a una empresa específica.
     *
     * @param empresaId ID de la empresa.
     * @return Lista de activos de la empresa.
     */
    List<Activo> findByEmpresaId(Long empresaId);
}
