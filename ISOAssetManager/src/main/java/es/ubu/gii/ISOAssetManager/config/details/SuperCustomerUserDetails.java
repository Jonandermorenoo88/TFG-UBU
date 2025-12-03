package es.ubu.gii.ISOAssetManager.config.details;

import es.ubu.gii.ISOAssetManager.model.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Collection;

/**
 * Implementación extendida de {@link UserDetails} que incluye información
 * adicional del usuario
 * específica de la aplicación, como el objeto {@link Usuario} completo y claves
 * RSA.
 */
public class SuperCustomerUserDetails implements UserDetails {

    // Atributos principales
    /** Nombre de usuario (utilizado como email en esta aplicación). */
    private String username;

    /** Contraseña del usuario. */
    private String password;

    /** Identificador único del usuario. */
    private Long userID;

    /** Nombre completo del usuario. */
    private String nombre;

    // Estado de la cuenta
    /** Indica si la cuenta está activa. */
    private boolean isActive = true;

    /** Indica si la cuenta no ha expirado. */
    private boolean isAccountNonExpired = true;

    /** Indica si la cuenta no está bloqueada. */
    private boolean isAccountNonLocked = true;

    /** Indica si las credenciales no han expirado. */
    private boolean isCredentialsNonExpired = true;

    // Claves RSA
    /** Clave privada RSA del usuario. */
    private PrivateKey privateKey;

    /** Clave pública RSA del usuario. */
    private PublicKey publicKey;

    // Entidad Usuario (tu modelo)
    /** Objeto de dominio {@link Usuario} asociado. */
    private Usuario usuario;

    // Permisos / roles
    /** Colección de autoridades (roles) concedidos al usuario. */
    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructor por defecto.
     */
    public SuperCustomerUserDetails() {
    }

    // ========= Getters / setters =========

    /**
     * Obtiene el ID del usuario.
     * 
     * @return ID del usuario.
     */
    public Long getUserID() {
        return userID;
    }

    /**
     * Establece el ID del usuario.
     * 
     * @param userID ID del usuario.
     */
    public void setUserID(Long userID) {
        this.userID = userID;
    }

    /**
     * Obtiene el nombre completo del usuario.
     * 
     * @return Nombre completo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre completo del usuario.
     * 
     * @param nombre Nombre completo.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la entidad {@link Usuario} asociada.
     * 
     * @return Objeto Usuario.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece la entidad {@link Usuario} asociada.
     * 
     * @param usuario Objeto Usuario.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la clave privada RSA.
     * 
     * @return Clave privada.
     */
    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    /**
     * Establece la clave privada RSA.
     * 
     * @param privateKey Clave privada.
     */
    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    /**
     * Obtiene la clave pública RSA.
     * 
     * @return Clave pública.
     */
    public PublicKey getPublicKey() {
        return publicKey;
    }

    /**
     * Establece la clave pública RSA.
     * 
     * @param publicKey Clave pública.
     */
    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    /**
     * Establece si la cuenta está activa.
     * 
     * @param active true si está activa.
     */
    public void setActive(boolean active) {
        isActive = active;
    }

    /**
     * Establece si la cuenta no ha expirado.
     * 
     * @param accountNonExpired true si no ha expirado.
     */
    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    /**
     * Establece si la cuenta no está bloqueada.
     * 
     * @param accountNonLocked true si no está bloqueada.
     */
    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    /**
     * Establece si las credenciales no han expirado.
     * 
     * @param credentialsNonExpired true si no han expirado.
     */
    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    /**
     * Establece las autoridades (roles) del usuario.
     * 
     * @param authorities Colección de autoridades.
     */
    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    /**
     * Establece el nombre de usuario.
     * 
     * @param username Nombre de usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Establece la contraseña.
     * 
     * @param password Contraseña.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    // ========= Métodos de UserDetails =========

    /**
     * Devuelve las autoridades concedidas al usuario.
     * 
     * @return Colección de autoridades.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    /**
     * Devuelve la contraseña utilizada para autenticar al usuario.
     * 
     * @return Contraseña.
     */
    @Override
    public String getPassword() {
        return this.password;
    }

    /**
     * Devuelve el nombre de usuario utilizado para autenticar al usuario.
     * 
     * @return Nombre de usuario.
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Indica si la cuenta del usuario ha expirado.
     * 
     * @return true si la cuenta es válida (no expirada).
     */
    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    /**
     * Indica si el usuario está bloqueado o desbloqueado.
     * 
     * @return true si el usuario no está bloqueado.
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    /**
     * Indica si las credenciales del usuario (contraseña) han expirado.
     * 
     * @return true si las credenciales son válidas (no expiradas).
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    /**
     * Indica si el usuario está habilitado o deshabilitado.
     * 
     * @return true si el usuario está habilitado.
     */
    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}