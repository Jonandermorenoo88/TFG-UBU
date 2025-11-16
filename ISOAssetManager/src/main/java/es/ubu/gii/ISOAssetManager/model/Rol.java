package es.ubu.gii.ISOAssetManager.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    
    public Rol() {
    }
    
    public Rol(String nombre) {
    	this.nombre = nombre;
    }

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

	public Object setUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'setUsuario'");
	}
}

