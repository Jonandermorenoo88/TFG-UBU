package es.ubu.gii.ISOAssetManager.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import es.ubu.gii.ISOAssetManager.model.Control;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad
 * {@link Control}.
 * <p>
 * Permite acceder a los controles de seguridad (ej. "A.5.1", "A.8.2") y
 * realizar búsquedas
 * filtradas por categoría.
 * </p>
 */
public interface ControlRepository extends JpaRepository<Control, String> {
    /**
     * Busca todos los controles asociados a una categoría específica, ordenados por
     * su orden de visualización.
     *
     * @param categoriaId ID de la categoría (ej. "A.5").
     * @return Lista de controles de la categoría, ordenados.
     */
    List<Control> findByCategoria_IdOrderByOrdenAscIdAsc(String categoriaId);
}
