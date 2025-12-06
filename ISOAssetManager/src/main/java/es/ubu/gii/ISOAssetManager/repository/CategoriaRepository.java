package es.ubu.gii.ISOAssetManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.ubu.gii.ISOAssetManager.model.Categoria;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad
 * {@link Categoria}.
 * <p>
 * Permite acceder a las categorías de controles (ej. "A.5", "A.6") almacenadas
 * en la base de datos.
 * </p>
 */
public interface CategoriaRepository extends JpaRepository<Categoria, String> {
}
