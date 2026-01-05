package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.*;

/**
 * Entidad que representa una pregunta de evaluación asociada a un control.
 * <p>
 * Las preguntas ayudan a determinar el grado de cumplimiento de un control
 * específico
 * por parte de la empresa.
 * </p>
 */
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "texto", "control_id" }) })
public class Pregunta {

    /**
     * Identificador único de la pregunta.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Texto de la pregunta.
     */
    @Column(nullable = false, length = 500)
    private String texto;

    /**
     * Explicación adicional o contexto para ayudar a responder la pregunta.
     */
    @Column(length = 1000)
    private String explicacion;

    /**
     * Control al que pertenece esta pregunta.
     */
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "control_id")
    private Control control;

    /**
     * Obtiene el ID de la pregunta.
     * 
     * @return ID de la pregunta.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el texto de la pregunta.
     * 
     * @return Texto de la pregunta.
     */
    public String getTexto() {
        return texto;
    }

    /**
     * Establece el texto de la pregunta.
     * 
     * @param texto Nuevo texto.
     */
    public void setTexto(String texto) {
        this.texto = texto;
    }

    /**
     * Obtiene la explicación de la pregunta.
     * 
     * @return Explicación.
     */
    public String getExplicacion() {
        return explicacion;
    }

    /**
     * Establece la explicación de la pregunta.
     * 
     * @param explicacion Nueva explicación.
     */
    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }

    /**
     * Obtiene el control asociado a la pregunta.
     * 
     * @return Control asociado.
     */
    public Control getControl() {
        return control;
    }

    /**
     * Establece el control asociado a la pregunta.
     * 
     * @param control Nuevo control.
     */
    public void setControl(Control control) {
        this.control = control;
    }
}
