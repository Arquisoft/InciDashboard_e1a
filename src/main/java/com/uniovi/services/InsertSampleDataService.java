package com.uniovi.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uniovi.entities.*;


@Service
public class InsertSampleDataService {

	@Autowired
	private OperatorService operatorService;

	@Autowired
	private FilterService filterService;

//	@Autowired
//	private com.asw.Kafka.producer.KafkaProducer kafkaProducer;

	@PostConstruct
	public void init() {

		Operator operator1 = new Operator("uo111111", "123456");

		operatorService.addOperator(operator1);
/**
		Incidence incidence1 = new Incidence("normal", "normal", "", "normal", "Paco", "123456", "Agent");
		Incidence incidence2 = new Incidence("incendio", "incendio en Uría", "", "normal", "Paco", "123456", "Agent");

		Filter filter = new Filter("temperatura", "elevada", FilterOperation.CONTAINS);
		filterService.addFieldFilter(filter);

		incidence2.addPropertie("temperatura : alta elevada peligro");

		kafkaProducer.send("incidence", incidence1.toJson());
		kafkaProducer.send("incidence", incidence2.toJson());

		
		operator1 = new Operator("uo222222", "123456");

		operatorService.addOperator(operator1);
		
		Incidence incidence3 = new Incidence("Lectura36", "Lectura humedad", "2513.4562", "normal", "sensor1", "123456", "Sensor");
		Incidence incidence4 = new Incidence("Lectura37", "Lectura humedad", "2513.4562", "normal", "sensor1", "123456", "Sensor");
		
		kafkaProducer.send("incidence", incidence3.toJson());
		kafkaProducer.send("incidence", incidence4.toJson());
		
		
		incidence3 = new Incidence("normal2", "normal", "", "normal", "Paco", "123456", "Agent");
		incidence4 = new Incidence("Lectura38", "Lectura humedad", "2513.4562", "normal", "sensor1", "123456", "Sensor");
		
		kafkaProducer.send("incidence", incidence3.toJson());
		kafkaProducer.send("incidence", incidence4.toJson());
		
	*/	
	}
}
