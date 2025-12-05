package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.*;

/**
 * Entidad que representa un departamento dentro de una empresa.
 * <p>
 * Permite organizar los activos y usuarios de una empresa en diferentes
 * unidades organizativas.
 * </p>
 */
@Entity
@Table(name = "departamento")
public class Departamento {

    /**
     * Identificador único del departamento.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Empresa a la que pertenece el departamento.
     */
    // FK consistente y no reservada
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    /**
     * Nombre o descripción del departamento (ej. "Recursos Humanos", "IT").
     */
    @Column(name = "descripcion", nullable = false, length = 255)
    private String descripcion;

    /**
     * Obtiene el ID del departamento.
     * 
     * @return ID del departamento.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del departamento.
     * 
     * @param id Nuevo ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene la empresa a la que pertenece el departamento.
     * 
     * @return Empresa propietaria.
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * Establece la empresa del departamento.
     * 
     * @param empresa Nueva empresa.
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * Obtiene la descripción del departamento.
     * 
     * @return Descripción.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del departamento.
     * 
     * @param descripcion Nueva descripción.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
