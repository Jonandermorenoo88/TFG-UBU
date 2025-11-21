package es.ubu.gii.ISOAssetManager.config.details;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Collection;

public class MiUserDetails implements UserDetails {

    // ===== Campos principales del usuario =====
    private Long idUsuario;
    private String username;   // en tu caso será el email
    private String password;
    private String nombre;

    // ===== Estado de la cuenta =====
    private boolean isActive = true;
    private boolean isAccountNonExpired = true;
    private boolean isAccountNonLocked = true;
    private boolean isCredentialsNonExpired = true;

    // ===== Claves RSA (preparado ya) =====
    private PrivateKey privateKey;
    private PublicKey publicKey;

    // ===== Roles / permisos =====
    private Collection<? extends GrantedAuthority> grantedAuthorities;

    // ===== Constructores =====
    public MiUserDetails() {
    }

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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGrantedAuthorities(Collection<? extends GrantedAuthority> grantedAuthorities) {
        this.grantedAuthorities = grantedAuthorities;
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(PrivateKey privateKey) {
        this.privateKey = privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(PublicKey publicKey) {
        this.publicKey = publicKey;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    // ===== Implementación de UserDetails =====

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    // Spring Security usará este como "username" (email)
    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }
}
