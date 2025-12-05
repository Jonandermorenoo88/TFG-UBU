package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entidad que representa un rol de usuario en el sistema.
 * <p>
 * Define los permisos y niveles de acceso (ej. ROLE_ADMIN, ROLE_AUDITOR,
 * ROLE_USER).
 * </p>
 */
@Entity
public class Rol {
	/**
	 * Identificador único del rol.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	/**
	 * Nombre del rol (ej. "ROLE_ADMIN").
	 */
	private String nombre;

	/**
	 * Constructor por defecto.
	 */
	public Rol() {
	}

	/**
	 * Constructor con nombre de rol.
	 * 
	 * @param nombre Nombre del rol.
	 */
	public Rol(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene el ID del rol.
	 * 
	 * @return ID del rol.
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Establece el ID del rol.
	 * 
	 * @param id Nuevo ID.
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Obtiene el nombre del rol.
	 * 
	 * @return Nombre del rol.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del rol.
	 * 
	 * @param nombre Nuevo nombre.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Método no implementado para establecer usuario.
	 * 
	 * @param usuario Usuario.
	 * @return Objeto (no utilizado).
	 * @throws UnsupportedOperationException Siempre lanza esta excepción.
	 */
	public Object setUsuario(Usuario usuario) {
		throw new UnsupportedOperationException("Unimplemented method 'setUsuario'");
	}
}
