package es.ubu.gii.ISOAssetManager.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

/**
 * Entidad que representa un usuario del sistema.
 * <p>
 * Almacena la información personal, credenciales de acceso, claves
 * criptográficas RSA
 * y la relación con la empresa y roles asignados.
 * </p>
 */
@Entity
@Table(name = "usuario")
public class Usuario {

    /**
     * Identificador único del usuario.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre completo del usuario.
     */
    @Column(nullable = false, length = 120)
    private String nombre;

    /**
     * Correo electrónico del usuario (debe ser único).
     */
    @Column(nullable = false, unique = true, length = 180)
    private String email;

    /**
     * Contraseña encriptada del usuario.
     */
    @Column(nullable = false)
    private String password;

    // ---------------------------
    // 🔐 CAMPOS PARA RSA
    // ---------------------------

    /**
     * Clave pública RSA del usuario para la verificación de firmas.
     */
    @Lob
    @Column(name = "public_key", columnDefinition = "LONGBLOB")
    private byte[] publicKey;

    /**
     * Clave privada RSA del usuario (almacenada encriptada o protegida) para firmar
     * evidencias.
     */
    @Lob
    @Column(name = "private_key", columnDefinition = "LONGBLOB")
    private byte[] privateKey;

    /**
     * Obtiene la clave pública RSA.
     * 
     * @return Clave pública en bytes.
     */
    public byte[] getPublicKey() {
        return publicKey;
    }

    /**
     * Establece la clave pública RSA.
     * 
     * @param publicKey Nueva clave pública.
     */
    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    /**
     * Obtiene la clave privada RSA.
     * 
     * @return Clave privada en bytes.
     */
    public byte[] getPrivateKey() {
        return privateKey;
    }

    /**
     * Establece la clave privada RSA.
     * 
     * @param privateKey Nueva clave privada.
     */
    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    // ---------------------------

    /**
     * Empresa a la que pertenece el usuario.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "empresa_id", nullable = true)
    private Empresa empresa;

    /**
     * Departamento al que pertenece el usuario.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "departamento_id", nullable = true)
    private Departamento departamento;

    /**
     * Roles asignados al usuario.
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "usuario_roles", joinColumns = @JoinColumn(name = "usuario_id"), inverseJoinColumns = @JoinColumn(name = "rol_id"))
    private Set<Rol> roles = new HashSet<>();

    /**
     * Constructor por defecto.
     */
    public Usuario() {
    }

    /**
     * Constructor con datos básicos.
     *
     * @param nombre   Nombre del usuario.
     * @param email    Correo electrónico.
     * @param password Contraseña.
     */
    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    /**
     * Obtiene el ID del usuario.
     * 
     * @return ID del usuario.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del usuario.
     * 
     * @param id Nuevo ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del usuario.
     * 
     * @return Nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * 
     * @param nombre Nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el email del usuario.
     * 
     * @return Email del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del usuario.
     * 
     * @param email Nuevo email.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     * 
     * @return Contraseña encriptada.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * 
     * @param password Nueva contraseña.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Obtiene la empresa del usuario.
     * 
     * @return Empresa.
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * Establece la empresa del usuario.
     * 
     * @param empresa Nueva empresa.
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * Obtiene el departamento del usuario.
     * 
     * @return Departamento.
     */
    public Departamento getDepartamento() {
        return departamento;
    }

    /**
     * Establece el departamento del usuario.
     * 
     * @param departamento Nuevo departamento.
     */
    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    /**
     * Obtiene los roles del usuario.
     * 
     * @return Conjunto de roles.
     */
    public Set<Rol> getRoles() {
        return roles;
    }

    /**
     * Establece los roles del usuario.
     * 
     * @param roles Nuevo conjunto de roles.
     */
    public void setRoles(Set<Rol> roles) {
        this.roles = roles;
    }

    /**
     * Valida que el departamento asignado pertenezca a la misma empresa que el
     * usuario.
     * <p>
     * Se ejecuta automáticamente antes de persistir o actualizar la entidad.
     * </p>
     * 
     * @throws IllegalStateException Si el departamento no pertenece a la empresa.
     */
    @PrePersist
    @PreUpdate
    private void validarEmpresaDepartamento() {
        if (departamento != null && empresa != null) {
            var empDep = departamento.getEmpresa();
            if (empDep != null && !empDep.getId().equals(empresa.getId())) {
                throw new IllegalStateException("El departamento no pertenece a la empresa del usuario.");
            }
        }
    }
}
