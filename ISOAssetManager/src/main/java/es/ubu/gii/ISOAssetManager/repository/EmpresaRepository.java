package es.ubu.gii.ISOAssetManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ubu.gii.ISOAssetManager.model.Empresa;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad
 * {@link Empresa}.
 * <p>
 * Permite la gestión de las empresas registradas en el sistema.
 * </p>
 */
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {

}
