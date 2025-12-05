package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.*;

/**
 * Entidad que representa una opción de respuesta posible para las preguntas de
 * control.
 * <p>
 * Define las opciones estandarizadas (ej. "Sí", "No", "Parcialmente") y su
 * valor asociado (baremo)
 * para el cálculo de cumplimiento.
 * </p>
 */
@Entity
@Table(name = "respuesta_posible", uniqueConstraints = @UniqueConstraint(columnNames = { "textoOpcion", "baremo" }))
public class RespuestaPosible {

    /**
     * Identificador único de la opción de respuesta.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Texto visible de la opción (ej. "Completamente implementado").
     */
    @Column(nullable = false, length = 120)
    private String textoOpcion;

    /**
     * Valor numérico o puntuación asociada a esta opción (0-100 o similar).
     */
    @Column(nullable = false)
    private int baremo;

    /**
     * Orden de visualización de la opción en la interfaz.
     */
    @Column
    private Integer orden;

    // --- Getters/Setters ---

    /**
     * Obtiene el ID de la opción.
     * 
     * @return ID de la opción.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el texto de la opción.
     * 
     * @return Texto de la opción.
     */
    public String getTextoOpcion() {
        return textoOpcion;
    }

    /**
     * Establece el texto de la opción.
     * 
     * @param textoOpcion Nuevo texto.
     */
    public void setTextoOpcion(String textoOpcion) {
        this.textoOpcion = textoOpcion;
    }

    /**
     * Obtiene el baremo o puntuación.
     * 
     * @return Baremo.
     */
    public int getBaremo() {
        return baremo;
    }

    /**
     * Establece el baremo o puntuación.
     * 
     * @param baremo Nuevo baremo.
     */
    public void setBaremo(int baremo) {
        this.baremo = baremo;
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
}
