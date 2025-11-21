package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.*;

@Entity
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 500)
    private String texto;

    @Column(length = 1000)
    private String explicacion;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "control_id")
    private Control control;

    public Long getId() { 
        return id; 
    }

    public String getTexto() { 
        return texto; 
    }

    public void setTexto(String texto) { 
        this.texto = texto; 
    }

    public String getExplicacion() { 
        return explicacion; 
    }

    public void setExplicacion(String explicacion) { 
        this.explicacion = explicacion; 
    }

    public Control getControl() { 
        return control; 
    }

    public void setControl(Control control) { 
        this.control = control; 
    }
}