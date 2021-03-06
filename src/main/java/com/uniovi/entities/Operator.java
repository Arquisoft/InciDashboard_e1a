package com.uniovi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Operator {

	@Id
	@GeneratedValue
	private long id;

	@Column(unique = true)
	private String name;

	@OneToMany(mappedBy = "operator", cascade = CascadeType.ALL, fetch=FetchType.EAGER )
	@JsonIgnoreProperties(value = "operator")
	private List<Incidence> incidences = new ArrayList<>();

	private String password;
	@Transient
	private String passwordConfirm;

	private Operator() {
	}

	public Operator(String name) {
		super();
		this.name = name;
	}

	public Operator(String name, String password) {
		super();
		this.name = name;
		this.setPassword(password);
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Incidence> getIncidences() {
		return incidences;
	}
	
	public int getNumberOfIncidences() {
		return incidences.size();
	}

	public void assignIncidence(Incidence incidence) {
		incidences.add(incidence);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Operator other = (Operator) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
