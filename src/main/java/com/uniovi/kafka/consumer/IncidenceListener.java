package com.uniovi.kafka.consumer;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.uniovi.entities.Incidence;
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

	@KafkaListener(topics = "incidence")
	public void ReceiveIncidence(String data) {
		logger.info("New message received: \"" + data + "\"");
		Incidence incidence = Util.JsonToIncidence(data); // Pasar a JSON y poner filtro valores peligrosos
		if (incidence == null)
			logger.info("No se ha cargado la incidencia" + data);
		else
			incidenceService.addIncidence(incidence);
	}

}
