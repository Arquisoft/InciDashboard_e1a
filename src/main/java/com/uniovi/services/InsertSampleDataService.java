package com.uniovi.services;


import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Incidence;
import com.uniovi.entities.Operator;
import com.uniovi.repositories.OperatorRepository;


@Service
public class InsertSampleDataService {

	@Autowired
	private OperatorService operatorService;
	
	@Autowired
	private IncidenceService incidenceService;

	/*@Autowired
	private InvitationsService invitationsService;*/

	@PostConstruct
	public void init() {
		Operator operator1 = new Operator("Paco");
		
		operatorService.addOperator(operator1);
		
		Incidence incidence1 = new Incidence(operator1, "temperatura", "La temperatura es 19ºC", new Date());
		Incidence incidence2 = new Incidence(operator1, "incendio", "Incendio en uría", new Date());
		
		incidenceService.addIncidence(incidence2);
		incidenceService.addIncidence(incidence1);
	}
}
