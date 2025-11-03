package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.*;

@Entity
@Table(
    name = "respuesta_posible",
    uniqueConstraints = @UniqueConstraint(columnNames = {"textoOpcion", "baremo"})
)
public class RespuestaPosible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String textoOpcion;

    @Column(nullable = false)
    private int baremo;

    @Column
    private Integer orden;

    // --- Getters/Setters ---
    public Long getId() { 
        return id; 
    }

    public String getTextoOpcion() { 
        return textoOpcion; 
    }

    public void setTextoOpcion(String textoOpcion) { 
        this.textoOpcion = textoOpcion; 
    }

    public int getBaremo() { 
        return baremo; 
    }

    public void setBaremo(int baremo) { 
        this.baremo = baremo; 
    }

    public Integer getOrden() { 
        return orden; 
    }

    public void setOrden(Integer orden) { 
        this.orden = orden; 
    }
}
