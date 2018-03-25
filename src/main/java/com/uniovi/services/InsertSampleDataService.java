package com.uniovi.services;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Incidence;
import com.uniovi.entities.Operator;
import com.uniovi.filter.Filter;
import com.uniovi.filter.FilterOperation;

@Service
public class InsertSampleDataService {

	@Autowired
	private OperatorService operatorService;

	@Autowired
	private IncidenceService incidenceService;

	@Autowired
	private FilterService filterService;

	@PostConstruct
	public void init() {

		Operator operator1 = new Operator("uo111111", "123456");

		operatorService.addOperator(operator1);

		Incidence incidence1 = new Incidence("normal", "normal", "", "normal", "Paco", "123456", "Agent");
		Incidence incidence2 = new Incidence("incendio", "incendio en Uría", "", "normal", "Paco", "123456", "Agent");

		incidence2.addPropertie("temperatura : alta elevada peligro");

		Filter filter = new Filter("temperatura", "elevada", FilterOperation.CONTAINS);
		filterService.addFieldFilter(filter);

		incidenceService.addIncidence(incidence2);
		incidenceService.addIncidence(incidence1);
	}
}
