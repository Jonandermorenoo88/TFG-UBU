package es.ubu.gii.ISOAssetManager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ubu.gii.ISOAssetManager.model.Rol;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad {@link Rol}.
 * <p>
 * Permite la gestión de los roles de usuario (ej. ROLE_ADMIN, ROLE_AUDITOR).
 * </p>
 */
public interface RolRepository extends JpaRepository<Rol, Long> {
	/**
	 * Busca un rol por su nombre.
	 *
	 * @param nombre Nombre del rol (ej. "ROLE_ADMIN").
	 * @return El rol si existe.
	 */
	Optional<Rol> findByNombre(String nombre);

}
