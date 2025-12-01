package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.*;

@Entity
public class Bloque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String previousHash;

    private String hash;

    @Column(columnDefinition = "TEXT")
    private String data; // JSON representation of the answer

    private long timeStamp;

    @Lob
    @Column(length = 1000)
    private byte[] firma; // RSA Signature

    @OneToOne
    @JoinColumn(name = "respuesta_id")
    private RespuestaEmpresa respuesta;

    public Bloque() {
    }

    public Bloque(String previousHash, String data, RespuestaEmpresa respuesta) {
        this.previousHash = previousHash;
        this.data = data;
        this.respuesta = respuesta;
        this.timeStamp = System.currentTimeMillis();
        this.hash = calcularHash();
    }

    // Simple hash calculation (SHA-256 logic will be in Service or Helper,
    // but for simplicity we can have a placeholder or setter here)
    public String calcularHash() {
        // This is usually done by the service to ensure consistent logic
        return "";
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public byte[] getFirma() {
        return firma;
    }

    public void setFirma(byte[] firma) {
        this.firma = firma;
    }

    public RespuestaEmpresa getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(RespuestaEmpresa respuesta) {
        this.respuesta = respuesta;
    }
}
