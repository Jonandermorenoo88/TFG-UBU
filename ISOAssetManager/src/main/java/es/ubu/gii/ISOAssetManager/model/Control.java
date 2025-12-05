// Control.java
package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa un control de seguridad de la norma ISO 27001.
 * <p>
 * Cada control pertenece a una categoría y contiene una lista de preguntas
 * que permiten evaluar su cumplimiento.
 * </p>
 */
@Entity
@Table(name = "control")
public class Control {

    /**
     * Identificador del control (ej. "A5.1").
     */
    @Id
    @Column(length = 20, nullable = false)
    private String id; // Ej: "A5.1"

    /**
     * Nombre descriptivo del control.
     */
    @Column(nullable = false, length = 255)
    private String nombre;

    /**
     * Categoría a la que pertenece el control.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)
    private Categoria categoria;

    /**
     * Orden de visualización del control dentro de su categoría.
     */
    @Column(name = "orden")
    private Integer orden;

    /**
     * Lista de preguntas asociadas a este control.
     */
    @OneToMany(mappedBy = "control", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pregunta> preguntas = new ArrayList<>();

    /**
     * Constructor por defecto.
     */
    public Control() {
    }

    /**
     * Constructor básico.
     *
     * @param id        Identificador del control.
     * @param nombre    Nombre del control.
     * @param categoria Categoría a la que pertenece.
     */
    public Control(String id, String nombre, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    /**
     * Constructor completo con orden.
     *
     * @param id        Identificador del control.
     * @param nombre    Nombre del control.
     * @param categoria Categoría a la que pertenece.
     * @param orden     Orden de visualización.
     */
    public Control(String id, String nombre, Categoria categoria, Integer orden) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.orden = orden;
    }

    // Getters / setters

    /**
     * Obtiene el identificador del control.
     * 
     * @return ID del control.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador del control.
     * 
     * @param id Nuevo ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre del control.
     * 
     * @return Nombre del control.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del control.
     * 
     * @param nombre Nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la categoría del control.
     * 
     * @return Categoría.
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del control.
     * 
     * @param categoria Nueva categoría.
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    /**
     * Obtiene el orden de visualización.
     * 
     * @return Orden.
     */
    public Integer getOrden() {
        return orden;
    }

    /**
     * Establece el orden de visualización.
     * 
     * @param orden Nuevo orden.
     */
    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    /**
     * Obtiene la lista de preguntas del control.
     * 
     * @return Lista de preguntas.
     */
    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    /**
     * Establece la lista de preguntas del control.
     * 
     * @param preguntas Nueva lista de preguntas.
     */
    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }
}
