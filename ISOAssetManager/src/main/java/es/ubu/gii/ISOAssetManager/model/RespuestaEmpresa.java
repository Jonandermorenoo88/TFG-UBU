package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RespuestaEmpresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne(optional = false)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pregunta_id")
    private Pregunta pregunta;

    @ManyToOne(optional = false)
    @JoinColumn(name = "respuesta_id")
    private RespuestaPosible respuesta;

    private LocalDateTime fechaRespuesta = LocalDateTime.now();

    // --- Getters & Setters ---
    public Long getId() { 
        return id; 
    }

    public Usuario getUsuario() { 
        return usuario; 
    }

    public void setUsuario(Usuario usuario) { 
        this.usuario = usuario; 
    }

    public Empresa getEmpresa() {
        return empresa; 
    }

    public void setEmpresa(Empresa empresa) { 
        this.empresa = empresa; 
    }

    public Pregunta getPregunta() { 
        return pregunta; 
    }

    public void setPregunta(Pregunta pregunta) { 
        this.pregunta = pregunta; 
    }

    public RespuestaPosible getRespuesta() { 
        return respuesta; 
    }

    public void setRespuesta(RespuestaPosible respuesta) { 
        this.respuesta = respuesta; 
    }

    public LocalDateTime getFechaRespuesta() { 
        return fechaRespuesta; 
    }

    public void setFechaRespuesta(LocalDateTime fechaRespuesta) { 
        this.fechaRespuesta = fechaRespuesta; 
    }
}