package com.uniovi.entities;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Incidence {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Operator operator;

	private String name;
	private String description;
		
	@Transient
	private Map<String, Object> fields;

	@Enumerated(EnumType.STRING)
	private Status status = Status.OPEN;
	
	private Date expirationDate;


	
	public Incidence() {}



	public Incidence(Operator operator, String incidenceName, String description, Date expirationDate) {
		super();
		this.operator = operator;
		this.name = incidenceName;
		this.description = description;
		this.expirationDate = expirationDate;
	}



	@Override
	public String toString() {
		return "Incidence [operator=" + operator + ", incidenceName=" + name + ", description=" + description
				+ ", fields=" + fields + ", status=" + status + ", expirationDate=" + expirationDate + "]";
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
	
	

}
