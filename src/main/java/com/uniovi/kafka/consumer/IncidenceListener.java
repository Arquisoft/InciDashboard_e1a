package com.uniovi.kafka.consumer;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.uniovi.entities.Incidence;
import com.uniovi.entities.IncidenceReceived;
import com.uniovi.services.IncidenceService;
import com.uniovi.services.OperatorService;
import com.uniovi.utils.Util;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class IncidenceListener {

	private static final Logger logger = Logger.getLogger(IncidenceListener.class);

	@Autowired
	private IncidenceService incidenceService;

	@Autowired
	private OperatorService operatorService;

	@KafkaListener(topics = "477f1kw4-Incidences")
	public void receiveIncidence(String data) {
		logger.info("New message received: \"" + data + "\"");
		IncidenceReceived rincidence = Util.JsonToIncidence(data); // Pasar a JSON y poner filtro valores peligrosos
		Incidence incidence = new Incidence(rincidence);
		if (incidence == null)
			logger.info("No se ha cargado la incidencia" + data);
		else
			incidenceService.addIncidence(incidence);
	}

}
