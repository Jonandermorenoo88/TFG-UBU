package es.ubu.gii.ISOAssetManager.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ubu.gii.ISOAssetManager.model.Rol;

public interface RolRepository extends JpaRepository<Rol, Long> {
	Optional<Rol> findByNombre(String nombre);

}
