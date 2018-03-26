package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Incidence;
import com.uniovi.entities.Operator;
import com.uniovi.filter.Filter;
import com.uniovi.filter.FilterOperation;
import com.uniovi.kafka.producer.KafkaProducer;

@Service
public class InsertSampleDataService {

	@Autowired
	private OperatorService operatorService;

	@Autowired
	private IncidenceService incidenceService;

	@Autowired
	private FilterService filterService;

	@Autowired
	private KafkaProducer kafkaProducer;

	@PostConstruct
	public void init() {

		Operator operator1 = new Operator("uo111111", "123456");

		operatorService.addOperator(operator1);

		Incidence incidence1 = new Incidence("normal", "normal", "", "normal", "Paco", "123456", "Agent");
		Incidence incidence2 = new Incidence("incendio", "incendio en Uría", "", "normal", "Paco", "123456", "Agent");

		Filter filter = new Filter("temperatura", "elevada", FilterOperation.CONTAINS);
		filterService.addFieldFilter(filter);

		incidence2.addPropertie("temperatura : alta elevada peligro");

		kafkaProducer.send("incidence", incidence1.toJson());
		kafkaProducer.send("incidence", incidence2.toJson());

	}
}
