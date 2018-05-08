package com.steps;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationContextLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import com.selenium.PO_Incidences;
import com.selenium.PO_Login;
import com.selenium.PO_NavView;
import com.selenium.PO_View;
import com.uniovi.InciDashboardE1aApplication;
import com.uniovi.entities.Status;

import cucumber.api.java.es.Cuando;
import cucumber.api.java.es.Dado;
import cucumber.api.java.es.Entonces;

@ContextConfiguration(classes = InciDashboardE1aApplication.class, loader = SpringApplicationContextLoader.class)
@IntegrationTest
@WebAppConfiguration
public class StepsTest {

	private static WebDriver driver;
	private String baseUrl;
	
	
	//METODOS COMPARTIDOS
	@Dado("^que nos conectamos al dashboard$")
	public void que_nos_conectamos_al_dashboard() throws Throwable {
		
		driver = new HtmlUnitDriver();
		baseUrl = "http://localhost:9090/";
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
		driver.get(baseUrl + "/");
		
		PO_View.checkElement(driver, "text", "Identifíquese");
	}
	
	@Cuando("^el agente se loguea en el sistema$")
	public void el_agente_se_loguea_en_el_sistema() throws Throwable {
		PO_View.checkElement(driver, "text", "Incidencias");
	}
	
	//LOGIN CORRECTO
	@Dado("^se logea sesion con un agente con nombre \"([^\"]*)\" y contraseña \"([^\"]*)\"$")
	public void se_logea_sesion_con_un_agente_con_nombre_y_contraseña(String arg1, String arg2) throws Throwable {
		
		PO_Login.fillForm(driver, arg1, arg2);
	}
	
	@Entonces("^ve las incidencias asignadas$")
	public void ve_las_incidencias_asignadas() throws Throwable {
		PO_View.checkElement(driver, "text", "Incidencias");
		driver.close();
	}
	
	//LOGIN INCORRECTO
	@Dado("^se logea un agente con nombre \"([^\"]*)\" y contraseña \"([^\"]*)\" que no existe$")
	public void se_logea_un_agente_con_nombre_y_contraseña_que_no_existe(String arg1, String arg2) throws Throwable {
		
		PO_Login.fillForm(driver, arg1, arg2);
	}
	
	@Entonces("^sigue en la misma de loging$")
	public void sigue_en_la_misma_de_loging() throws Throwable {
		PO_View.checkElement(driver, "text", "Identifíquese");
		driver.close();
	}



	@Entonces("^aparece en su vista la incidencia \"([^\"]*)\"$")
	public void aparece_en_su_vista_la_incidencia(String arg1) throws Throwable {
		driver.get(baseUrl + "incidences/list");
		PO_View.checkElement(driver, "text", "Incidencias");
	}




}
