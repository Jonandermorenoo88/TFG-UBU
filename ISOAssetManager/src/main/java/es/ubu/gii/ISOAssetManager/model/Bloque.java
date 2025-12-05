package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.*;

/**
 * Entidad que representa un bloque dentro de la cadena de bloques (Blockchain).
 * <p>
 * Cada bloque almacena información inmutable sobre una respuesta a un control o
 * una evidencia subida,
 * garantizando la integridad y trazabilidad de las acciones realizadas en el
 * sistema.
 * </p>
 */
@Entity
public class Bloque {

    /**
     * Identificador único del bloque.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Hash del bloque anterior en la cadena.
     */
    private String previousHash;

    /**
     * Hash actual de este bloque.
     */
    private String hash;

    /**
     * Datos almacenados en el bloque (representación JSON de la respuesta o
     * evidencia).
     */
    @Column(columnDefinition = "TEXT")
    private String data; // JSON representation of the answer

    /**
     * Marca de tiempo de creación del bloque (timestamp).
     */
    private long timeStamp;

    /**
     * Firma digital RSA del bloque (opcional).
     */
    @Lob
    @Column(length = 1000)
    private byte[] firma; // RSA Signature

    /**
     * Respuesta asociada a este bloque (si aplica).
     */
    @OneToOne
    @JoinColumn(name = "respuesta_id")
    private RespuestaEmpresa respuesta;

    /**
     * Evidencia asociada a este bloque (si aplica).
     */
    @OneToOne
    @JoinColumn(name = "evidencia_id")
    private Evidencia evidencia;

    /**
     * Constructor por defecto.
     */
    public Bloque() {
    }

    /**
     * Constructor para crear un bloque asociado a una respuesta.
     *
     * @param previousHash Hash del bloque anterior.
     * @param data         Datos de la respuesta en formato JSON.
     * @param respuesta    Entidad de la respuesta asociada.
     */
    public Bloque(String previousHash, String data, RespuestaEmpresa respuesta) {
        this.previousHash = previousHash;
        this.data = data;
        this.respuesta = respuesta;
        this.timeStamp = System.currentTimeMillis();
        this.hash = calcularHash();
    }

    /**
     * Constructor para crear un bloque asociado a una evidencia.
     *
     * @param previousHash Hash del bloque anterior.
     * @param data         Datos de la evidencia en formato JSON.
     * @param evidencia    Entidad de la evidencia asociada.
     */
    public Bloque(String previousHash, String data, Evidencia evidencia) {
        this.previousHash = previousHash;
        this.data = data;
        this.evidencia = evidencia;
        this.timeStamp = System.currentTimeMillis();
        this.hash = calcularHash();
    }

    /**
     * Método placeholder para el cálculo del hash.
     * <p>
     * La lógica real de cálculo SHA-256 se delega normalmente al servicio de
     * Blockchain
     * para asegurar la consistencia.
     * </p>
     *
     * @return Cadena vacía por defecto (debe ser establecido externamente).
     */
    public String calcularHash() {
        // This is usually done by the service to ensure consistent logic
        return "";
    }

    // Getters and Setters
    // Getters and Setters

    /**
     * Obtiene el ID del bloque.
     * 
     * @return ID del bloque.
     */
    public Long getId() {
        return id;
    }

    /**
     * Establece el ID del bloque.
     * 
     * @param id Nuevo ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Obtiene el hash del bloque anterior.
     * 
     * @return Hash anterior.
     */
    public String getPreviousHash() {
        return previousHash;
    }

    /**
     * Establece el hash del bloque anterior.
     * 
     * @param previousHash Nuevo hash anterior.
     */
    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    /**
     * Obtiene el hash actual del bloque.
     * 
     * @return Hash actual.
     */
    public String getHash() {
        return hash;
    }

    /**
     * Establece el hash actual del bloque.
     * 
     * @param hash Nuevo hash.
     */
    public void setHash(String hash) {
        this.hash = hash;
    }

    /**
     * Obtiene los datos almacenados en el bloque.
     * 
     * @return Datos en formato JSON.
     */
    public String getData() {
        return data;
    }

    /**
     * Establece los datos del bloque.
     * 
     * @param data Nuevos datos.
     */
    public void setData(String data) {
        this.data = data;
    }

    /**
     * Obtiene la marca de tiempo del bloque.
     * 
     * @return Timestamp.
     */
    public long getTimeStamp() {
        return timeStamp;
    }

    /**
     * Establece la marca de tiempo del bloque.
     * 
     * @param timeStamp Nuevo timestamp.
     */
    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Obtiene la firma digital del bloque.
     * 
     * @return Firma en bytes.
     */
    public byte[] getFirma() {
        return firma;
    }

    /**
     * Establece la firma digital del bloque.
     * 
     * @param firma Nueva firma.
     */
    public void setFirma(byte[] firma) {
        this.firma = firma;
    }

    /**
     * Obtiene la respuesta asociada al bloque.
     * 
     * @return Respuesta asociada.
     */
    public RespuestaEmpresa getRespuesta() {
        return respuesta;
    }

    /**
     * Establece la respuesta asociada al bloque.
     * 
     * @param respuesta Nueva respuesta.
     */
    public void setRespuesta(RespuestaEmpresa respuesta) {
        this.respuesta = respuesta;
    }

    /**
     * Obtiene la evidencia asociada al bloque.
     * 
     * @return Evidencia asociada.
     */
    public Evidencia getEvidencia() {
        return evidencia;
    }

    /**
     * Establece la evidencia asociada al bloque.
     * 
     * @param evidencia Nueva evidencia.
     */
    public void setEvidencia(Evidencia evidencia) {
        this.evidencia = evidencia;
    }
}
