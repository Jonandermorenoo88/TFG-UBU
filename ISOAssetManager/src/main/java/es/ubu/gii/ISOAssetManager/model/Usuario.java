package es.ubu.gii.ISOAssetManager.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nombre;

    @Column(nullable = false, unique = true, length = 180)
    private String email;

    @Column(nullable = false)
    private String password;

    // ---------------------------
    // 🔐 CAMPOS PARA RSA
    // ---------------------------

    @Lob
    @Column(name = "public_key", columnDefinition = "LONGBLOB")
    private byte[] publicKey;

    @Lob
    @Column(name = "private_key", columnDefinition = "LONGBLOB")
    private byte[] privateKey;

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    // ---------------------------

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "empresa_id", nullable = true)
    private Empresa empresa;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "departamento_id", nullable = true)
    private Departamento departamento;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
        name = "usuario_roles",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "rol_id")
    )
    private Set<Rol> roles = new HashSet<>();

    public Usuario() {}

    public Usuario(String nombre, String email, String password) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }

    public Long getId() { return id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }

    public Departamento getDepartamento() { return departamento; }
    public void setDepartamento(Departamento departamento) { this.departamento = departamento; }

    public Set<Rol> getRoles() { return roles; }
    public void setRoles(Set<Rol> roles) { this.roles = roles; }

    @PrePersist @PreUpdate
    private void validarEmpresaDepartamento() {
        if (departamento != null && empresa != null) {
            var empDep = departamento.getEmpresa();
            if (empDep != null && !empDep.getId().equals(empresa.getId())) {
                throw new IllegalStateException("El departamento no pertenece a la empresa del usuario.");
            }
        }
    }
}
