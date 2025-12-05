package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa una evidencia documental subida al sistema.
 * <p>
 * Una evidencia es un archivo que demuestra el cumplimiento de un control
 * específico.
 * Incluye metadatos del archivo, hash para integridad y firma digital opcional.
 * </p>
 */
@Entity
@Table(name = "evidencia")
public class Evidencia {

    /**
     * Identificador único de la evidencia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Nombre original del archivo subido (ej: "politica_seguridad.pdf").
     */
    @Column(nullable = false, length = 255)
    private String nombreArchivo;

    /**
     * Ruta interna o nombre del archivo en el sistema de ficheros (UUID +
     * extensión).
     */
    @Column(nullable = false, length = 500)
    private String rutaFichero;

    /**
     * Tipo MIME del archivo (ej: application/pdf).
     */
    @Column(length = 100)
    private String contentType;

    /**
     * Tamaño del archivo en bytes.
     */
    @Column
    private Long tamanoBytes;

    /**
     * Fecha y hora de subida de la evidencia.
     */
    @Column(nullable = false)
    private LocalDateTime fechaSubida;

    /**
     * Usuario que subió la evidencia.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = true)
    private Usuario usuario;

    /**
     * Empresa a la que pertenece la evidencia.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    /**
     * Control de seguridad al que está asociada la evidencia.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "control_id", nullable = false)
    private Control control;

    // ================== NIVEL 2: INTEGRIDAD / FIRMA DIGITAL ==================

    /**
     * Hash SHA-256 del contenido del archivo para verificar su integridad.
     */
    @Lob
    @Column(name = "hash_evidencia")
    private byte[] hash;

    /**
     * Firma digital del hash del archivo, generada con la clave privada RSA del
     * usuario.
     */
    @Lob
    @Column(name = "firma_evidencia", columnDefinition = "LONGBLOB")
    private byte[] firma;

    /**
     * Constructor por defecto.
     */
    public Evidencia() {
    }

    // ================== GETTERS & SETTERS ==================

    /**
     * Obtiene el ID de la evidencia.
     * 
     * @return ID de la evidencia.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID de la evidencia.
     * 
     * @param id Nuevo ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el nombre original del archivo.
     * 
     * @return Nombre del archivo.
     */
    public String getNombreArchivo() {
        return nombreArchivo;
    }

    /**
     * Establece el nombre original del archivo.
     * 
     * @param nombreArchivo Nuevo nombre.
     */
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    /**
     * Obtiene la ruta interna del archivo.
     * 
     * @return Ruta del archivo.
     */
    public String getRutaFichero() {
        return rutaFichero;
    }

    /**
     * Establece la ruta interna del archivo.
     * 
     * @param rutaFichero Nueva ruta.
     */
    public void setRutaFichero(String rutaFichero) {
        this.rutaFichero = rutaFichero;
    }

    /**
     * Obtiene el tipo MIME del archivo.
     * 
     * @return Tipo MIME.
     */
    public String getContentType() {
        return contentType;
    }

    /**
     * Establece el tipo MIME del archivo.
     * 
     * @param contentType Nuevo tipo MIME.
     */
    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    /**
     * Obtiene el tamaño del archivo en bytes.
     * 
     * @return Tamaño en bytes.
     */
    public Long getTamanoBytes() {
        return tamanoBytes;
    }

    /**
     * Establece el tamaño del archivo en bytes.
     * 
     * @param tamanoBytes Nuevo tamaño.
     */
    public void setTamanoBytes(Long tamanoBytes) {
        this.tamanoBytes = tamanoBytes;
    }

    /**
     * Obtiene la fecha de subida.
     * 
     * @return Fecha de subida.
     */
    public LocalDateTime getFechaSubida() {
        return fechaSubida;
    }

    /**
     * Establece la fecha de subida.
     * 
     * @param fechaSubida Nueva fecha.
     */
    public void setFechaSubida(LocalDateTime fechaSubida) {
        this.fechaSubida = fechaSubida;
    }

    /**
     * Obtiene el usuario que subió la evidencia.
     * 
     * @return Usuario.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario que subió la evidencia.
     * 
     * @param usuario Nuevo usuario.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la empresa propietaria.
     * 
     * @return Empresa.
     */
    public Empresa getEmpresa() {
        return empresa;
    }

    /**
     * Establece la empresa propietaria.
     * 
     * @param empresa Nueva empresa.
     */
    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    /**
     * Obtiene el control asociado.
     * 
     * @return Control.
     */
    public Control getControl() {
        return control;
    }

    /**
     * Establece el control asociado.
     * 
     * @param control Nuevo control.
     */
    public void setControl(Control control) {
        this.control = control;
    }

    /**
     * Obtiene el hash SHA-256 del archivo.
     * 
     * @return Hash en bytes.
     */
    public byte[] getHash() {
        return hash;
    }

    /**
     * Establece el hash SHA-256 del archivo.
     * 
     * @param hash Nuevo hash.
     */
    public void setHash(byte[] hash) {
        this.hash = hash;
    }

    /**
     * Obtiene la firma digital del archivo.
     * 
     * @return Firma en bytes.
     */
    public byte[] getFirma() {
        return firma;
    }

    /**
     * Establece la firma digital del archivo.
     * 
     * @param firma Nueva firma.
     */
    public void setFirma(byte[] firma) {
        this.firma = firma;
    }
}
