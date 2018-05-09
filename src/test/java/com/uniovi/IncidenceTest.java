package com.uniovi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Incidence;
import com.uniovi.entities.Operator;
import com.uniovi.entities.Status;
import com.uniovi.services.IncidenceService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IncidenceTest {

	@Autowired
	private IncidenceService incidenceService;

	private static boolean setup = false;
	private static Incidence incidence1;
	private static Incidence incidence2;
	private static Incidence incidence3;
	private static Incidence incidence4;
	private static Incidence incidence5;
	private static Incidence incidence6;
	private static Incidence incidence7;

	@Before
	public void setUp() throws Exception {

		if (!setup) {
			incidence1 = new Incidence("InciNormal1", "descripcion normal1", "1.1", "normal1", "Paco", "123456",
					"Agent");
			incidence2 = new Incidence("InciNormal2", "descripcion normal2", "1.1", "normal2", "Paco", "123456",
					"Agent");
			incidence3 = new Incidence("InciNormal3", "descripcion normal3", "1.1", "normal3", "Paco", "123456",
					"Agent");
			incidence4 = new Incidence("sensor1", "sensor1", "22.22", "sensor1", "sensor1", "123456", "Sensor");
			incidence5 = new Incidence("sensor2", "sensor2", "22.22", "sensor2", "sensor1", "123456", "Sensor");
			incidence6 = new Incidence("sensor3", "sensor3", "22.22", "sensor3", "sensor1", "123456", "Sensor");
			incidence7 = new Incidence("peligro", "peligro", "0.0", "peligro", "Fernando", "123456", "Agent");

			incidenceService.addIncidence(incidence1);
			incidenceService.addIncidence(incidence2);
			incidenceService.addIncidence(incidence3);
			incidenceService.addIncidence(incidence4);
			incidenceService.addIncidence(incidence5);
			incidenceService.addIncidence(incidence6);
			incidenceService.addIncidence(incidence7);

			Thread.sleep(2000);

			// Comprobamos que han sido asignadas
			assertEquals(Status.ASSIGNED, incidence1.getState());
			System.out.println("Incidencia 1 asignada a " + incidence1.getOperator());

			assertEquals(Status.ASSIGNED, incidence2.getState());
			System.out.println("Incidencia 2 asignada a " + incidence1.getOperator());

			assertEquals(Status.ASSIGNED, incidence3.getState());
			System.out.println("Incidencia 3 asignada a " + incidence1.getOperator());

			assertEquals(Status.ASSIGNED, incidence4.getState());
			System.out.println("Incidencia 4 asignada a " + incidence1.getOperator());

			assertEquals(Status.ASSIGNED, incidence5.getState());
			System.out.println("Incidencia 5 asignada a " + incidence1.getOperator());

			assertEquals(Status.ASSIGNED, incidence6.getState());
			System.out.println("Incidencia 6 asignada a " + incidence1.getOperator());

			assertEquals(Status.ASSIGNED, incidence7.getState());
			System.out.println("Incidencia 7 asignada a " + incidence1.getOperator());

			setup = true;

		}

	}

	@Test
	public void testFind() {

		// Buscamos todas las incidencias
		List<Incidence> list = incidenceService.findAll();

		// assertEquals(13, list.size());
		String zu = "asdf";
		zu = "asdf";

		// TODO Aqui no pilla operador
		Operator op = incidence1.getOperator();

		// Buscamos la incidencia InciNormal1 por el nombre
		assertEquals(incidence1,
				incidenceService.searchIncidencesByNameAndDescription("InciNormal1", incidence1.getOperator()).get(0));

		// Buscamos la incidencia InciNormal1 por la descripcion
		assertEquals(incidence1, incidenceService
				.searchIncidencesByNameAndDescription("descripcion normal1", incidence1.getOperator()).get(0));

		// Buscamos la incidencia InciNormal1 por id
		assertEquals(incidence1, incidenceService.verIncidencia(incidence1.getId()));

		// Buscamos incidencias que no existen
		assertEquals(0, incidenceService
				.searchIncidencesByNameAndDescription("Incidencia que no existe", incidence1.getOperator()).size());
		assertEquals(0, incidenceService
				.searchIncidencesByNameAndDescription("Descripcion que no existe", incidence1.getOperator()).size());
		assertEquals(0, incidenceService.searchIncidencesByNameAndDescription(null, incidence1.getOperator()).size());
		assertEquals(0, incidenceService.searchIncidencesByNameAndDescription("InciNormal1", null).size());

	}

	@Test
	public void testClose() {

		// Ceramos un incidencia
		incidenceService.close(incidence1);

		assertEquals(Status.CLOSED, incidence1.getState());
		//assertEquals(1, incidenceService.countIncidencesType(Status.CLOSED));

		// Ceramos varias incidencias
		incidenceService.close(incidence2);
		incidenceService.close(incidence3);
		incidenceService.close(incidence4);

		assertEquals(Status.CLOSED, incidence2.getState());
		assertEquals(Status.CLOSED, incidence3.getState());
		assertEquals(Status.CLOSED, incidence4.getState());

		//assertEquals(4, incidenceService.countIncidencesType(Status.CLOSED));

		// Probamos a cerrar una incidencia ya cerrada
		incidenceService.close(incidence1);

		assertEquals(Status.CLOSED, incidence1.getState());
		assertEquals(4, incidenceService.countIncidencesType(Status.CLOSED));

	}

}
