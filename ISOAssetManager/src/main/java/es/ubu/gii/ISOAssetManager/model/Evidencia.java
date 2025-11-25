package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "evidencia")
public class Evidencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Nombre visible (ej: "politica_seguridad.pdf")
    @Column(nullable = false, length = 255)
    private String nombreArchivo;

    // Nombre interno en disco (UUID + extensión)
    @Column(nullable = false, length = 500)
    private String rutaFichero;

    // MIME Type (ej: application/pdf)
    @Column(length = 100)
    private String contentType;

    // Tamaño del fichero
    @Column
    private Long tamanoBytes;

    // Fecha de subida
    @Column(nullable = false)
    private LocalDateTime fechaSubida;

    // Usuario que subió la evidencia
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = true)
    private Usuario usuario;

    // Empresa a la que pertenece
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    private Empresa empresa;

    // Evidencia asociada a un control del anexo
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "control_id", nullable = false)
    private Control control;

    // ================== NIVEL 2: INTEGRIDAD / FIRMA DIGITAL ==================

    // Hash del fichero (por ejemplo SHA-256)
    @Lob
    @Column(name = "hash_evidencia")
    private byte[] hash;

    // Firma digital del hash, generada con la clave privada RSA del usuario
    @Lob
    @Column(name = "firma_evidencia", columnDefinition = "LONGBLOB")
    private byte[] firma;

    public Evidencia() {}

    // ================== GETTERS & SETTERS ==================

    public Long getId() { return id; }

    public String getNombreArchivo() { return nombreArchivo; }
    public void setNombreArchivo(String nombreArchivo) { this.nombreArchivo = nombreArchivo; }

    public String getRutaFichero() { return rutaFichero; }
    public void setRutaFichero(String rutaFichero) { this.rutaFichero = rutaFichero; }

    public String getContentType() { return contentType; }
    public void setContentType(String contentType) { this.contentType = contentType; }

    public Long getTamanoBytes() { return tamanoBytes; }
    public void setTamanoBytes(Long tamanoBytes) { this.tamanoBytes = tamanoBytes; }

    public LocalDateTime getFechaSubida() { return fechaSubida; }
    public void setFechaSubida(LocalDateTime fechaSubida) { this.fechaSubida = fechaSubida; }

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }

    public Control getControl() { return control; }
    public void setControl(Control control) { this.control = control; }

    public byte[] getHash() { return hash; }
    public void setHash(byte[] hash) { this.hash = hash; }

    public byte[] getFirma() { return firma; }
    public void setFirma(byte[] firma) { this.firma = firma; }
}
