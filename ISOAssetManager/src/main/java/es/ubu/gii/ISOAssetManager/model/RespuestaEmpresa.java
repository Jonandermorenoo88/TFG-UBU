package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa la respuesta de una empresa a una pregunta de control.
 * <p>
 * Almacena qué usuario respondió, para qué empresa, a qué pregunta y cuál fue
 * la respuesta seleccionada.
 * También registra la fecha y hora de la respuesta.
 * </p>
 */
@Entity
public class RespuestaEmpresa {

    /**
     * Identificador único de la respuesta.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Usuario que proporcionó la respuesta.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    /**
     * Empresa a la que pertenece la respuesta.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    /**
     * Pregunta que se está respondiendo.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "pregunta_id")
    private Pregunta pregunta;

    /**
     * Opción seleccionada como respuesta.
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "respuesta_id")
    private RespuestaPosible respuesta;

    /**
     * Fecha y hora en que se registró la respuesta.
     */
    private LocalDateTime fechaRespuesta = LocalDateTime.now();

    // --- Getters & Setters ---

    /**
     * Obtiene el ID de la respuesta.
     * 
     * @return ID de la respuesta.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el usuario que respondió.
     * 
     * @return Usuario.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario que respondió.
     * 
     * @param usuario Nuevo usuario.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la empresa asociada.
     * 
     * @return Empresa.
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * Establece la empresa asociada.
     * 
     * @param empresa Nueva empresa.
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * Obtiene la pregunta respondida.
     * 
     * @return Pregunta.
     */
    public Pregunta getPregunta() {
        return pregunta;
    }

    /**
     * Establece la pregunta respondida.
     * 
     * @param pregunta Nueva pregunta.
     */
    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    /**
     * Obtiene la opción seleccionada.
     * 
     * @return Respuesta seleccionada.
     */
    public RespuestaPosible getRespuesta() {
        return respuesta;
    }

    /**
     * Establece la opción seleccionada.
     * 
     * @param respuesta Nueva respuesta seleccionada.
     */
    public void setRespuesta(RespuestaPosible respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Obtiene la fecha de la respuesta.
     * 
     * @return Fecha y hora.
     */
    public LocalDateTime getFechaRespuesta() {
        return fechaRespuesta;
    }

    /**
     * Establece la fecha de la respuesta.
     * 
     * @param fechaRespuesta Nueva fecha y hora.
     */
    public void setFechaRespuesta(LocalDateTime fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }
}
