package com.uniovi.kafka.consumer;

import java.util.Date;

import javax.annotation.ManagedBean;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;

import com.uniovi.entities.Incidence;
import com.uniovi.entities.Operator;
import com.uniovi.services.IncidenceService;
import com.uniovi.services.OperatorService;
import com.uniovi.utils.Util;

/**
 * Created by herminio on 28/12/16.
 */
@ManagedBean
public class MessageListener {

	private static final Logger logger = Logger.getLogger(MessageListener.class);

	@Autowired
	private IncidenceService incidenceService;

	@Autowired
	private OperatorService operatorService;

	@KafkaListener(topics = "exampleTopic")
	public void listen(String data) {
		logger.info("New message received: \"" + data + "\"");
		Incidence incidence = Util.JsonToIncidence(data); // Pasar a JSON y poner filtro valores peligrosos
		incidenceService.addIncidence(incidence);
	}

}
