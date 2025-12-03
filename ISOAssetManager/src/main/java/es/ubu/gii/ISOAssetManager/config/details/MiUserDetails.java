package es.ubu.gii.ISOAssetManager.config.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Collection;

/**
 * Implementación personalizada de {@link UserDetails} para gestionar la
 * información de autenticación y autorización del usuario.
 * <p>
 * Esta clase envuelve la información del usuario de la base de datos y añade
 * detalles necesarios para Spring Security,
 * así como claves RSA para operaciones criptográficas.
 * </p>
 */
public class MiUserDetails implements UserDetails {

    // ===== Campos principales del usuario =====
    /** Identificador único del usuario. */
    private Long idUsuario;

    /** Nombre de usuario (en este caso, el correo electrónico). */
    private String username;

    /** Contraseña del usuario (cifrada). */
    private String password;

    /** Nombre completo del usuario. */
    private String nombre;

    // ===== Estado de la cuenta =====
    /** Indica si la cuenta está activa. */
    private boolean isActive = true;

    /** Indica si la cuenta no ha expirado. */
    private boolean isAccountNonExpired = true;

    /** Indica si la cuenta no está bloqueada. */
    private boolean isAccountNonLocked = true;

    /** Indica si las credenciales no han expirado. */
    private boolean isCredentialsNonExpired = true;

    // ===== Claves RSA (preparado ya) =====
    /** Clave privada RSA del usuario. */
    private PrivateKey privateKey;

    /** Clave pública RSA del usuario. */
    private PublicKey publicKey;

    // ===== Roles / permisos =====
    /** Colección de autoridades (roles) concedidos al usuario. */
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    // ===== Constructores =====

    /**
     * Constructor por defecto.
     */
    public MiUserDetails() {
    }

    /**
     * Constructor completo para inicializar todos los campos del usuario.
     *
     * @param idUsuario               Identificador del usuario.
     * @param username                Nombre de usuario (email).
     * @param password                Contraseña.
     * @param nombre                  Nombre completo.
     * @param isActive                Si está activo.
     * @param isAccountNonExpired     Si la cuenta no ha expirado.
     * @param isAccountNonLocked      Si la cuenta no está bloqueada.
     * @param isCredentialsNonExpired Si las credenciales no han expirado.
     * @param grantedAuthorities      Roles y permisos.
     * @param privateKey              Clave privada RSA.
     * @param publicKey               Clave pública RSA.
     */
    public MiUserDetails(Long idUsuario,
            String username,
            String password,
            String nombre,
            boolean isActive,
            boolean isAccountNonExpired,
            boolean isAccountNonLocked,
            boolean isCredentialsNonExpired,
            Collection<? extends GrantedAuthority> grantedAuthorities,
            PrivateKey privateKey,
            PublicKey publicKey) {
        this.idUsuario = idUsuario;
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.isActive = isActive;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.grantedAuthorities = grantedAuthorities;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    // ===== Getters y setters extra =====

    /**
     * Obtiene el ID del usuario.
     * 
     * @return ID del usuario.
     */
    public Long getIdUsuario() {
        return idUsuario;
    }

    /**
     * Establece el ID del usuario.
     * 
     * @param idUsuario ID del usuario.
     */
    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
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
     * Establece el nombre de usuario (email).
     * 
     * @param username Email del usuario.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Establece las autoridades (roles) del usuario.
     * 
     * @param grantedAuthorities Colección de autoridades.
     */
    public void setGrantedAuthorities(Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
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
     * Establece la contraseña.
     * 
     * @param password Contraseña.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Establece si la cuenta está activa.
     * 
     * @param active true si está activa, false en caso contrario.
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

    // ===== Implementación de UserDetails =====

    /**
     * Devuelve las autoridades concedidas al usuario.
     * 
     * @return Colección de autoridades.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
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
     * En esta implementación corresponde al email.
     * 
     * @return Nombre de usuario (email).
     */
    @Override
    public String getUsername() {
        return this.username;
    }

    /**
     * Indica si la cuenta del usuario ha expirado.
     * 
     * @return true si la cuenta es válida (no expirada), false en caso contrario.
     */
    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    /**
     * Indica si el usuario está bloqueado o desbloqueado.
     * 
     * @return true si el usuario no está bloqueado, false en caso contrario.
     */
    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    /**
     * Indica si las credenciales del usuario (contraseña) han expirado.
     * 
     * @return true si las credenciales son válidas (no expiradas), false en caso
     *         contrario.
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    /**
     * Indica si el usuario está habilitado o deshabilitado.
     * 
     * @return true si el usuario está habilitado, false en caso contrario.
     */
    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
