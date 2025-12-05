package es.ubu.gii.ISOAssetManager.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

/**
 * Entidad que representa una empresa cliente del sistema.
 * <p>
 * Una empresa agrupa departamentos, activos y usuarios, y es la entidad
 * principal
 * sobre la que se realizan las evaluaciones de cumplimiento ISO 27001.
 * </p>
 */
@Entity
public class Empresa {

    /**
     * Identificador único de la empresa.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre de la empresa.
     */
    private String nombre;

    /**
     * Sector de actividad de la empresa.
     */
    private String sector;

    /**
     * Dirección física de la empresa.
     */
    private String direccion;

    /**
     * Lista de activos de información pertenecientes a la empresa.
     */
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Activo> activos = new ArrayList<>();

    /**
     * Lista de departamentos de la empresa.
     */
    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Departamento> departamentos = new ArrayList<>();

    /**
     * Constructor por defecto.
     */
    public Empresa() {
    }

    /**
     * Constructor con parámetros básicos.
     *
     * @param nombre    Nombre de la empresa.
     * @param sector    Sector de actividad.
     * @param direccion Dirección física.
     */
    public Empresa(String nombre, String sector, String direccion) {
        this.nombre = nombre;
        this.sector = sector;
        this.direccion = direccion;
    }

    // --- Getters y Setters ---

    /**
     * Obtiene el ID de la empresa.
     * 
     * @return ID de la empresa.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la empresa.
     * 
     * @param id Nuevo ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la empresa.
     * 
     * @return Nombre de la empresa.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la empresa.
     * 
     * @param nombre Nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el sector de la empresa.
     * 
     * @return Sector de actividad.
     */
    public String getSector() {
        return sector;
    }

    /**
     * Establece el sector de la empresa.
     * 
     * @param sector Nuevo sector.
     */
    public void setSector(String sector) {
        this.sector = sector;
    }

    /**
     * Obtiene la dirección de la empresa.
     * 
     * @return Dirección física.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección de la empresa.
     * 
     * @param direccion Nueva dirección.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * Obtiene la lista de activos de la empresa.
     * 
     * @return Lista de activos.
     */
    public List<Activo> getActivos() {
        return activos;
    }

    /**
     * Establece la lista de activos de la empresa.
     * 
     * @param activos Nueva lista de activos.
     */
    public void setActivos(List<Activo> activos) {
        this.activos = activos;
    }

    /**
     * Obtiene la lista de departamentos de la empresa.
     * 
     * @return Lista de departamentos.
     */
    public List<Departamento> getDepartamentos() {
        return departamentos;
    }

    /**
     * Establece la lista de departamentos de la empresa.
     * 
     * @param departamentos Nueva lista de departamentos.
     */
    public void setDepartamentos(List<Departamento> departamentos) {
        this.departamentos = departamentos;
    }
}
