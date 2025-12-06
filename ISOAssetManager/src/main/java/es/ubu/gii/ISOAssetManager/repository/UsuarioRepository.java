package es.ubu.gii.ISOAssetManager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import es.ubu.gii.ISOAssetManager.model.Usuario;

/**
 * Repositorio para gestionar las operaciones CRUD de la entidad
 * {@link Usuario}.
 * <p>
 * Permite la gestión de los usuarios del sistema, incluyendo búsquedas por
 * email y empresa.
 * </p>
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    /**
     * Busca un usuario por su dirección de correo electrónico.
     *
     * @param email Email del usuario.
     * @return El usuario si existe.
     */
    Optional<Usuario> findByEmail(String email);

    /**
     * Busca todos los usuarios pertenecientes a una empresa específica.
     *
     * @param empresaId ID de la empresa.
     * @return Lista de usuarios de la empresa.
     */
    List<Usuario> findByEmpresa_Id(Long empresaId);

}
