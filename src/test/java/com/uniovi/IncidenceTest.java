package com.uniovi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.uniovi.entities.Incidence;
import com.uniovi.services.IncidenceService;

public class IncidenceTest {
	/**
	@Autowired
	private IncidenceService incidenceService;
	
	@Autowired
	private KafkaProducer kafkaProducer;
	
	private static boolean setup;

	@Before
	public void setUp() throws Exception {
		
		if(!setup) {
		Incidence incidence1 = new Incidence("normal1", "normal1", "1.1", "normal1", "Paco", "123456", "Agent");
		Incidence incidence2 = new Incidence("normal2", "normal2", "1.1", "normal2", "Paco", "123456", "Agent");
		Incidence incidence3 = new Incidence("normal3", "normal3", "1.1", "normal3", "Paco", "123456", "Agent");
		Incidence incidence4 = new Incidence("sensor1", "sensor1", "22.22", "sensor1", "sensor1", "123456", "Sensor");
		Incidence incidence5 = new Incidence("sensor2", "sensor2", "22.22", "sensor2", "sensor1", "123456", "Sensor");
		Incidence incidence6 = new Incidence("sensor3", "sensor3", "22.22", "sensor3", "sensor1", "123456", "Sensor");
		Incidence incidence7 = new Incidence("peligro", "peligro", "0.0", "peligro", "Fernando", "123456", "Agent");
		
		kafkaProducer.send("incidence", incidence1.toJson());
		kafkaProducer.send("incidence", incidence2.toJson());
		kafkaProducer.send("incidence", incidence3.toJson());
		kafkaProducer.send("incidence", incidence4.toJson());
		kafkaProducer.send("incidence", incidence5.toJson());
		kafkaProducer.send("incidence", incidence6.toJson());
		kafkaProducer.send("incidence", incidence7.toJson());
		
		Thread.sleep(2000);
		setup = true;
		}
	}

	@Test
	public void testFind() {
		//Buscamos que esten todas las incidencias
		List<Incidence> list = incidenceService.findAll();
		
		assertEquals(13, list.size());
		
	}
*/
}
