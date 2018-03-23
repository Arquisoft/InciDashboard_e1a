package entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Users")
public class Agent implements Serializable {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	@Column (nullable = true)
	private String localizacion;
	private String email;
	private String password;
	
	@Column (unique = true, nullable = false)
	String identificador;
	
	@ManyToOne
	Type tipo;

	Agent() {
	}
	
	

	public Agent(String nombre, String localizacion, String email, String identificador, Type tipo) {
		super();
		this.nombre = nombre;
		this.localizacion = localizacion;
		this.email = email;
		this.identificador = identificador;
		this.tipo = tipo;
		generarPassword();
	}

	public Agent(String nombre, String email, String identificador, Type tipo) {
		super();
		this.nombre = nombre;
		this.email = email;
		this.identificador = identificador;
		this.tipo = tipo;
		generarPassword();
	}

	public Agent(String nombre, String localizacion, String email, String identificador, String tipo) {
		setNombre(nombre);
		setLocalizacion(localizacion);
		setEmail(email);
		this.identificador = identificador;
		this.tipo = setTipo(tipo);
		generarPassword();
	}
	
	public Agent(String nombre, String email, String identificador, String tipo) {
		setNombre(nombre);
		setEmail(email);
		this.identificador = identificador;
		this.tipo = setTipo(tipo);
		generarPassword();
	}
	
	public String getIdentificador() {
		return identificador;
	}
	
	public Type getTipo() {
		return this.tipo;
	}
	
	private Type setTipo(String tipo) {
		String[] value= tipo.split(",");
		this.tipo = new Type(Integer.valueOf(value[0]), value[1]);
		return this.tipo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identificador == null) ? 0 : identificador.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Agent other = (Agent) obj;
		if (identificador == null) {
			if (other.identificador != null)
				return false;
		} else if (!identificador.equals(other.identificador))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [nombre=" + nombre + ", localizacion=" + localizacion + ", email=" + email + ", password="
				+ password + ", identificador=" + identificador + ", tipo=" + tipo + "]";
	}

	private void generarPassword() {
		StringBuffer pass = new StringBuffer();
		int low = 65;
		int top = 90;
		for (int i = 0; i < 9; i++) {
			int numAleatorio = (int) Math.floor(Math.random() * (top - low) + low);
			pass.append((char) numAleatorio);
		}
		for (int i = 0; i < 3; i++) {
			int numAleatorio = (int) Math.floor(Math.random() * (9 - 0) + 0);
			pass.append(numAleatorio);
		}
		setPassword(pass.toString());
	}

	private void setPassword(String password) {
		this.password = password;
		
	}

	public String getPassword() {
		return password;
	}

}
