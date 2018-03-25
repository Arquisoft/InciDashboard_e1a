package com.uniovi.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "AgentType")
public class Type {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	@Column (unique = true, nullable = false)
	Integer tipo;
	String nombre_tipo;
	
	Type(){}

	public Type(Integer tipo, String nombre_tipo) {
		super();
		this.tipo = tipo;
		this.nombre_tipo = nombre_tipo;
	}
	
	public Type(Integer tipo) {
		super();
		this.tipo = tipo;
	}



	public Long getId() {
		return Id;
	}

	public Integer getTipo() {
		return tipo;
	}

	public String getNombre_tipo() {
		return nombre_tipo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
		Type other = (Type) obj;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Type [tipo=" + tipo + ", nombre_tipo=" + nombre_tipo + "]";
	}
	

}
