package com.uniovi;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Incidence;
import com.uniovi.entities.Operator;
import com.uniovi.kafka.producer.KafkaProducer;
import com.uniovi.services.IncidenceService;
import com.uniovi.services.OperatorService;
import com.uniovi.services.SecurityService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperatorTest {

	@Autowired
	private OperatorService operatorService;

	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private KafkaProducer kafkaProducer;

	private static Operator operator1;
	private static Operator operator2;
	private static Operator operator3;
	private static Operator operator4;
	private static Operator operator5;
	
	private static boolean setup;

	@Before
	public void setUp() throws Exception {
		if(!setup) {
		
		operator1 = new Operator("op1", "123456");
		operator2 = new Operator("op2", "123456");
		operator3 = new Operator("op3", "123456");
		operator4 = new Operator("op4", "123456");
		operator5 = new Operator("op5", "123456");
			
		operatorService.addOperator(operator1);
		operatorService.addOperator(operator2);
		operatorService.addOperator(operator3);
		operatorService.addOperator(operator4);
		operatorService.addOperator(operator5);
		
		
		setup = true;
		}
	}

	@Test
	public void testLogin() {
		// Probamos a logearnos con un usuario correcto
		securityService.autoLogin("op1", "123456");
		assertEquals(operator1.getName(), SecurityContextHolder.getContext().getAuthentication().getName());

		securityService.autoLogin("op2", "123456");
		assertEquals(operator2.getName(), SecurityContextHolder.getContext().getAuthentication().getName());

		// Probamos a logearnos con un usuario incorrecto
		try {
			securityService.autoLogin("op1", "0");
		} catch (Exception e) {
			assertEquals("Bad credentials", e.getMessage());
		}

		// Probamos a logearnos con un usuario que no existe
		try {
			securityService.autoLogin("op112", "0");
		} catch (Exception e) {
			assertEquals(NullPointerException.class, e.getClass());
		}

	}

	@Test
	public void testFind() {
		// Probamos a buscar a todos los usuarios de la aplicacion
		List<Operator> list = operatorService.findAll();
		assertEquals(7, list.size());

		// Probamos a buscar usuarios concretos
		assertEquals(operator1.getName(), operatorService.findByName("op1").getName());

		assertEquals(operator3.getName(), operatorService.findByName("op3").getName());
		
		assertEquals(operator5.getName(), operatorService.findByName("op5").getName());

		// Probamos a buscar usuarios que no existen
		assertNull(operatorService.findByName("op12"));
		
		assertNull(operatorService.findByName(null));
	}
	
	@Test
	public void testAssingIncidences() throws InterruptedException {
		
		Incidence incidence1 = new Incidence("prueba1", "prueba1", "", "prueba1", "Paco", "123456", "Agent");
		Incidence incidence2 = new Incidence("prueba2", "prueba2", "", "prueba2", "Paco", "123456", "Agent");
		Incidence incidence3 = new Incidence("prueba3", "prueba3", "", "prueba3", "Paco", "123456", "Agent");
		Incidence incidence4 = new Incidence("prueba4", "prueba4", "", "prueba4", "Paco", "123456", "Agent");
		Incidence incidence5 = new Incidence("prueba5", "prueba5", "", "prueba5", "Paco", "123456", "Agent");
		Incidence incidence6 = new Incidence("prueba6", "prueba6", "", "prueba6", "Paco", "123456", "Agent");
		Incidence incidence7 = new Incidence("prueba7", "prueba7", "", "prueba7", "Paco", "123456", "Agent");
		
		kafkaProducer.send("incidence", incidence1.toJson());
		Thread.sleep(1000);
		
		kafkaProducer.send("incidence", incidence2.toJson());
		Thread.sleep(1000);
		
		kafkaProducer.send("incidence", incidence3.toJson());
		Thread.sleep(1000);
		
		kafkaProducer.send("incidence", incidence4.toJson());
		Thread.sleep(1000);
		
		kafkaProducer.send("incidence", incidence5.toJson());
		Thread.sleep(1000);
		
//		assertEquals(1 ,operator1.getNumberOfIncidences());
//		assertEquals(1 ,operator2.getNumberOfIncidences());
//		assertEquals(1 ,operator3.getNumberOfIncidences());
//		assertEquals(1 ,operator4.getNumberOfIncidences());
		
		// Buscamos el operador con mas incidencias
		operator1.assignIncidence(incidence1);
		operator1.assignIncidence(incidence2);
		operator1.assignIncidence(incidence3);
		operator1.assignIncidence(incidence4);
		operator1.assignIncidence(incidence5);
		operator1.assignIncidence(incidence6);
		
		assertEquals("uo111111", operatorService.findOperatorWithMoreIncidences().getName());

	}

}
