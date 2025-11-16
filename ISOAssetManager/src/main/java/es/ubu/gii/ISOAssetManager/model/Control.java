// Control.java
package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "control")
public class Control {

    @Id
    @Column(length = 20, nullable = false)
    private String id; // Ej: "A5.1"

    @Column(nullable = false, length = 255)
    private String nombre;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)
    private Categoria categoria;

    @Column(name = "orden", insertable = false, updatable = false)
    private Integer orden;
    
    @OneToMany(mappedBy = "control", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Pregunta> preguntas = new ArrayList<>();

    public Control() {}

    public Control(String id, String nombre, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
    }

    // NUEVO: constructor con orden
    public Control(String id, String nombre, Categoria categoria, Integer orden) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.orden = orden;
    }

    // Getters / setters
    public String getId() { 
        return id; 
    }

    public void setId(String id) { 
        this.id = id; 
    }

    public String getNombre() { 
        return nombre; 
    }

    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public Categoria getCategoria() { 
        return categoria; 
    }

    public void setCategoria(Categoria categoria) { 
        this.categoria = categoria; 
    }

    public Integer getOrden() { 
        return orden; 
    }

    public void setOrden(Integer orden) { 
        this.orden = orden; 
    }

    public List<Pregunta> getPreguntas() { 
        return preguntas; 
    }

    public void setPreguntas(List<Pregunta> preguntas) { 
        this.preguntas = preguntas; 
    }
}
