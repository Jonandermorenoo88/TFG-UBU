package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @Column(length = 10, nullable = false)
    private String id; 

    @Column(nullable = false, length = 255)
    private String nombre;

    @OneToMany(mappedBy = "categoria", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Control> controles = new ArrayList<>();

    public Categoria() {}

    public Categoria(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    public List<Control> getControles() { 
        return controles; 
    }

    public void setControles(List<Control> controles) { 
        this.controles = controles; 
    }
}
