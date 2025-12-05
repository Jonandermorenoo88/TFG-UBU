package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.*;

/**
 * Entidad que representa un activo de información dentro de una empresa.
 * <p>
 * Un activo es cualquier elemento que tenga valor para la organización y que
 * necesite ser protegido.
 * Puede ser hardware, software, información, personal, etc.
 * </p>
 */
@Entity
public class Activo {

    /**
     * Identificador único del activo.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre del activo.
     */
    private String nombre;

    /**
     * Descripción detallada del activo.
     */
    private String descripcion;

    /**
     * Tipo de activo (ej. Hardware, Software, Información, Servicio, Personas).
     */
    private String tipo;

    /**
     * Valoración económica o crítica del activo.
     */
    private double valor;

    /**
     * Empresa a la que pertenece el activo.
     */
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    /**
     * Usuario responsable del activo.
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "responsable_id", nullable = false)
    private Usuario responsable;

    /**
     * Constructor por defecto.
     */
    public Activo() {

    }

    // Getters / Setters

    /**
     * Obtiene el ID del activo.
     * 
     * @return ID del activo.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del activo.
     * 
     * @param id Nuevo ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del activo.
     * 
     * @return Nombre del activo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del activo.
     * 
     * @param nombre Nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del activo.
     * 
     * @return Descripción del activo.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del activo.
     * 
     * @param descripcion Nueva descripción.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el tipo de activo.
     * 
     * @return Tipo de activo.
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Establece el tipo de activo.
     * 
     * @param tipo Nuevo tipo.
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Obtiene el valor del activo.
     * 
     * @return Valor del activo.
     */
    public double getValor() {
        return valor;
    }

    /**
     * Establece el valor del activo.
     * 
     * @param valor Nuevo valor.
     */
    public void setValor(double valor) {
        this.valor = valor;
    }

    /**
     * Obtiene la empresa propietaria del activo.
     * 
     * @return Empresa propietaria.
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * Establece la empresa propietaria del activo.
     * 
     * @param empresa Nueva empresa.
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * Obtiene el usuario responsable del activo.
     * 
     * @return Usuario responsable.
     */
    public Usuario getResponsable() {
        return responsable;
    }

    /**
     * Establece el usuario responsable del activo.
     * 
     * @param responsable Nuevo responsable.
     */
    public void setResponsable(Usuario responsable) {
        this.responsable = responsable;
    }

    /**
     * Valida que el responsable asignado pertenezca a la misma empresa que el
     * activo.
     * <p>
     * Se ejecuta automáticamente antes de persistir o actualizar la entidad.
     * </p>
     * 
     * @throws IllegalStateException Si el responsable no pertenece a la misma
     *                               empresa.
     */
    @PrePersist
    @PreUpdate
    private void validarResponsableEmpresa() {
        if (empresa == null || responsable == null ||
                responsable.getEmpresa() == null ||
                !responsable.getEmpresa().getId().equals(empresa.getId())) {
            throw new IllegalStateException("El responsable debe pertenecer a la misma empresa del activo.");
        }
    }
}
