package com.uniovi.entities;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uniovi.utils.Util;

public class IncidenceReceived {
	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private String description;
	private String location;
	private String nombreAgente;
	private String passwordAgente;
	private String tipoAgente;
	private String etiquetas;
	private String properties = "";
	@Enumerated(EnumType.STRING)
	private IncidenceState state = IncidenceState.OPENED;
	private String expiration;
	private String comments;
	private String additionalInfo;

	public IncidenceReceived() {
		super();
	}

	public IncidenceReceived(String nombreAgente, String passwordAgente, String tipoAgente, String name, String description,
			String etiquetas, String properties) {
		this.name = name;
		this.description = description;
		this.etiquetas = etiquetas.toLowerCase();
		this.properties = properties.toLowerCase();
		this.nombreAgente = nombreAgente;
		this.passwordAgente = passwordAgente;
		this.tipoAgente = tipoAgente;
	}

	public void addComments(String newComment) {
		comments = Util.addString(newComment.toLowerCase(), comments);
	}

	public void addProperties(String newPropertie) {
		properties = Util.addString(newPropertie.toLowerCase(), properties);
	}

	public void addTags(String newTag) {
		etiquetas = Util.addString(newTag.toLowerCase(), etiquetas);
	}

	public void cancel() {
		state = IncidenceState.CANCELED;
	}

	public void close() {
		state = IncidenceState.CLOSED;
	}

	public void deleteComment(String comment) {
		comments = Util.deleteString(comment.toLowerCase(), comments);
	}

	public void deleteTag(String tag) {
		etiquetas = Util.deleteString(tag.toLowerCase(), etiquetas);
	}

	public void deleteProperty(String key) {
		properties = Util.deleteProperty(key.toLowerCase(), properties);
	}

	public String getAdditionalInfo() {
		return additionalInfo;
	}

	@JsonIgnore
	public List<String> getCommentList() {
		return Util.toList(comments);
	}

	public String getComments() {
		return comments;
	}

	public String getDescripcion() {
		return description;
	}

	public String getDescription() {
		return description;
	}

	public String getEtiquetas() {
		return etiquetas;
	}

	public String getExpiration() {
		return expiration;
	}

	public Long getId() {
		return id;
	}

	@JsonIgnore
	public List<String> getInfoList() {
		return Util.toList(additionalInfo);
	}

	public String getLocation() {
		return location;
	}

	public String getName() {
		return name;
	}

	public String getNombreAgente() {
		return nombreAgente;
	}

	public String getPasswordAgente() {
		return passwordAgente;
	}

	public String getProperties() {
		return properties;
	}

	@JsonIgnore
	public Map<String, String> getPropertyMap() {
		return Util.toMap(properties);
	}

	public IncidenceState getState() {
		return state;
	}

	@JsonIgnore
	public List<String> getTagList() {
		return Util.toList(etiquetas);
	}

	public String getTipoAgente() {
		return tipoAgente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nombreAgente == null) ? 0 : nombreAgente.hashCode());
		return result;
	}

	public void open() {
		state = IncidenceState.OPENED;
	}

	public void process() {
		state = IncidenceState.IN_PROCESS;
	}

	public void setDescripcion(String descripcion) {
		this.description = descripcion;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setEtiquetas(String etiquetas) {
		this.etiquetas = etiquetas;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setNombreAgente(String nombreAgente) {
		this.nombreAgente = nombreAgente;
	}

	public void setPasswordAgente(String passwordAgente) {
		this.passwordAgente = passwordAgente;
	}

	public void setProperties(String properties) {
		this.properties = properties;
	}

	public void setTipoAgente(String tipoAgente) {
		this.tipoAgente = tipoAgente;
	}

	private enum IncidenceState {
		OPENED, IN_PROCESS, CLOSED, CANCELED
	}

}