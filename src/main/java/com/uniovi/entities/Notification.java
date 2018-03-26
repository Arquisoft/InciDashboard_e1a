package com.uniovi.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import com.uniovi.filter.Filter;

@Entity
public class Notification {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private Incidence incidence;

	@Transient
	private List<Filter> filters = new ArrayList<>();

	public Notification() {
	}

	public Notification(Incidence incidence) {
		super();
		this.incidence = incidence;
	}

	public void addFilter(Filter filter) {
		filters.add(filter);
		calculatePriority();
	}

	private void calculatePriority() {
		incidence.setPriority(filters.stream().mapToDouble(x -> x.getPriority()).sum());
	}
}
