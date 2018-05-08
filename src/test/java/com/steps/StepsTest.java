package com.steps;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.selenium.PO_Incidences;
import com.selenium.PO_Login;
import com.selenium.PO_NavView;
import com.selenium.PO_View;
import com.uniovi.InciDashboardE1aApplication;
import com.uniovi.entities.Incidence;
import com.uniovi.entities.Operator;
import com.uniovi.entities.Status;
import com.uniovi.services.IncidenceService;
import com.uniovi.services.OperatorService;

import cucumber.api.PendingException;
import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;


@ContextConfiguration(classes=InciDashboardE1aApplication.class, loader=SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class StepsTest {

	@Autowired
	private OperatorService operatorService;

	@Autowired
	private IncidenceService incidenceService;
	
	private static WebDriver driver;
	private String url;

	@BeforeClass
	public void setUp() throws Exception {
		String baseUrl;
		driver = new HtmlUnitDriver();
		baseUrl = "http://localhost:8090/";
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}
	
	@Before
	public void setingUp() {
		driver.navigate().to(url);
	}

	@After
	public void tearDown() {
		driver.manage().deleteAllCookies();
	} // Antes de la primera prueba

	@AfterClass
	static public void end() { // Cerramos el navegador al finalizar las pruebas
		driver.quit();
	}

	@Dado("^un agente con nombre \"([^\"]*)\", contraseña \"([^\"]*)\" se loguea$")
	public void un_agente_con_nombre_contraseña_se_loguea(String arg1, String arg2) throws Throwable {
		PO_Login.fillForm(driver, arg1, arg2);
	}

	@Dado("^autorizado en el sistema como \"([^\"]*)\"$")
	public void autorizado_en_el_sistema_como(String arg1) throws Throwable {
		assertTrue(operatorService.findByName(arg1) != null);
	}

	@Cuando("^el agente se loguea en el sistema$")
	public void el_agente_se_loguea_en_el_sistema() throws Throwable {
		PO_View.checkElement(driver, "text", "Incidencias");
	}

	@Dado("^no autorizado en el sistema como \"([^\"]*)\"$")
	public void no_autorizado_en_el_sistema_como(String arg1) throws Throwable {
		assertFalse(operatorService.findByName(arg1) != null);
	}

	@Entonces("^sigue en la misma de loging$")
	public void sigue_en_la_misma_de_loging() throws Throwable {
		PO_View.checkElement(driver, "text", "Identifíquese");
	}
	
	@Cuando("^se le asigna una incidencia \"([^\"]*)\" a \"([^\"]*)\"$")
	public void se_le_asigna_una_incidencia_a(String arg1, String arg2) throws Throwable {
	   Operator o = operatorService.findByName(arg2);
	   Incidence incidence1 = new Incidence(arg1, "descripcion normal1", "1.1", "normal1", "Paco", "123456",
				"Agent");
	   operatorService.assignIncidence(o, incidence1);
	}
	@Entonces("^aparece en su vista la incidencia \"([^\"]*)\"$")
	public void aparece_en_su_vista_la_incidencia(String arg1) throws Throwable {
		PO_NavView.clickOption(driver, "/incidences/list");
		PO_View.checkElement(driver, "text", arg1);
	}

	@Entonces("^modifica \"([^\"]*)\" a cerrada$")
	public void modifica_a_cerrada(String arg1) throws Throwable {
	    PO_Incidences.changeState(driver, arg1, Status.CANCELLED);
	}
	
	@Entonces("^ve las incidencias asignadas$")
	public void ve_las_incidencias_asignadas() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	    throw new PendingException();
	}
	
}
