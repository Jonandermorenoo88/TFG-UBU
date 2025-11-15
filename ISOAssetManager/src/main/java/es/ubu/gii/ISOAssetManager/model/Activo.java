package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.*;

@Entity
public class Activo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String descripcion;

    private String tipo; 

    private double valor;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "responsable_id", nullable = false)
    private Usuario responsable;

    public Activo() {

    }

    // Getters / Setters
    public Long getId() {
         return id;
    }

    public void setId(Long id) { 
        this.id = id; 
    }

    public String getNombre() { 
        return nombre; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public String getDescripcion() { 
        return descripcion; 
    }
    public void setDescripcion(String descripcion) { 
        this.descripcion = descripcion; 
    }

    public String getTipo() { 
        return tipo; 
    }
    public void setTipo(String tipo) { 
        this.tipo = tipo; 
    }

    public double getValor() { 
        return valor; 
    }

    public void setValor(double valor) { 
        this.valor = valor; 
    }

    public Empresa getEmpresa() { 
        return empresa; 
    }

    public void setEmpresa(Empresa empresa) { 
        this.empresa = empresa; 
    }

    public Usuario getResponsable() { 
        return responsable; 
    }

    public void setResponsable(Usuario responsable) { 
        this.responsable = responsable; 
    }
}
