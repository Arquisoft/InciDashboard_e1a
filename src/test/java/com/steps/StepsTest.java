package com.steps;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.Assert;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;

import com.InciDashboardE1aApplicationTests;
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

@ContextConfiguration(classes = InciDashboardE1aApplication.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class StepsTest {

	static String PathFirefox = "E:\\cosas\\Universidad\\3Curso\\SDI\\Firefox46.0.win\\Firefox46.win\\FirefoxPortable.exe";
	
	@Autowired
	protected WebApplicationContext context;

	protected MockMvc mvc;
	protected MvcResult result;

	@Value("${local.server.port}")
	protected int port;

	@Autowired
	private OperatorService operatorService;

	@Autowired
	private IncidenceService incidenceService;

	private static WebDriver driver;
	
	
	@Dado("^un agente con nombre \"([^\"]*)\", contraseña \"([^\"]*)\" se loguea$")
	public void un_agente_con_nombre_contraseña_se_loguea(String arg1, String arg2) throws Throwable {
		driver = new HtmlUnitDriver();
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.navigate().to("http://localhost:8090/");
		PO_Login.fillForm(driver, arg1, arg2);
	}

	@Cuando("^el agente se loguea en el sistema$")
	public void el_agente_se_loguea_en_el_sistema() throws Throwable {
		PO_View.checkElement(driver, "text", "Incidencias");
	}


	@Entonces("^sigue en la misma de loging$")
	public void sigue_en_la_misma_de_loging() throws Throwable {
		PO_View.checkElement(driver, "text", "Identifíquese");
		driver.close();
	}

	@Cuando("^se le asigna una incidencia \"([^\"]*)\" a \"([^\"]*)\"$")
	public void se_le_asigna_una_incidencia_a(String arg1, String arg2) throws Throwable {
		System.out.println("Cosas");
	}

	@Entonces("^aparece en su vista la incidencia \"([^\"]*)\"$")
	public void aparece_en_su_vista_la_incidencia(String arg1) throws Throwable {
		PO_NavView.clickOption(driver, "/incidences/list");
		assertEquals("Incidencias", driver.findElement(By.cssSelector("h3")).getText());
	}

	@Entonces("^modifica \"([^\"]*)\" a cerrada$")
	public void modifica_a_cerrada(String arg1) throws Throwable {
		PO_Incidences.changeState(driver, arg1, Status.CANCELLED);
		driver.close();
	}

	@Entonces("^ve las incidencias asignadas$")
	public void ve_las_incidencias_asignadas() throws Throwable {
		driver.close();
	}

}
