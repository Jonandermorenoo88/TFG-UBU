package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que representa una categoría (o cláusula) de controles de la norma
 * ISO 27001.
 * <p>
 * Agrupa un conjunto de controles relacionados (ej. "A.5 Controles
 * Organizacionales").
 * </p>
 */
@Entity
@Table(name = "categoria")
public class Categoria {

    /**
     * Identificador de la categoría (ej. "A5", "A6").
     */
    @Id
    @Column(length = 10, nullable = false)
    private String id;

    /**
     * Nombre descriptivo de la categoría (ej. "Controles Organizacionales").
     */
    @Column(nullable = false, length = 255)
    private String nombre;

    /**
     * Lista de controles asociados a esta categoría.
     */
    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Control> controles = new ArrayList<>();

    /**
     * Constructor por defecto.
     */
    public Categoria() {
    }

    /**
     * Constructor con parámetros.
     *
     * @param id     Identificador de la categoría.
     * @param nombre Nombre de la categoría.
     */
    public Categoria(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    // Getters / setters

    /**
     * Obtiene el identificador de la categoría.
     * 
     * @return ID de la categoría.
     */
    public String getId() {
        return id;
    }

    /**
     * Establece el identificador de la categoría.
     * 
     * @param id Nuevo ID.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre de la categoría.
     * 
     * @return Nombre de la categoría.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre de la categoría.
     * 
     * @param nombre Nuevo nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la lista de controles de esta categoría.
     * 
     * @return Lista de controles.
     */
    public List<Control> getControles() {
        return controles;
    }

    /**
     * Establece la lista de controles de esta categoría.
     * 
     * @param controles Nueva lista de controles.
     */
    public void setControles(List<Control> controles) {
        this.controles = controles;
    }
}
