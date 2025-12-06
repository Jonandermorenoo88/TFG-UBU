package es.ubu.gii.ISOAssetManager.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import es.ubu.gii.ISOAssetManager.model.Departamento;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad
 * {@link Departamento}.
 * <p>
 * Permite la gestión de departamentos dentro de las empresas.
 * </p>
 */
public interface DepartamentoRepository extends JpaRepository<Departamento, Long> {

    /**
     * Busca un departamento por su descripción (nombre) dentro de una empresa
     * específica.
     *
     * @param descripcion Nombre o descripción del departamento.
     * @param empresaId   ID de la empresa a la que pertenece.
     * @return El departamento si existe.
     */
    Optional<Departamento> findByDescripcionAndEmpresaId(String descripcion, Long empresaId);
}
