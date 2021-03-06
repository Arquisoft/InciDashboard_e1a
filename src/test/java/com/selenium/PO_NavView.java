package com.selenium;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


import static org.junit.Assert.assertTrue;

public class PO_NavView extends PO_View {

	/**
	 * CLicka una de las opciones principales (a href) y comprueba que se vaya a la
	 * vista con el elemento de tipo type con el texto Destino
	 * 
	 * @param driver:
	 *            apuntando al navegador abierto actualmente.
	 * @param textOption:
	 *            Texto de la opci�n principal.
	 * @param criterio:
	 *            "id" or "class" or "text" or "@attribute" or "free". Si el valor
	 *            de criterio es free es una expresion xpath completa.
	 * @param textoDestino:
	 *            texto correspondiente a la b�squeda de la p�gina destino.
	 */
	public static void clickOption(WebDriver driver, String textOption, String criterio, String textoDestino) {
		// CLickamos en la opci�n de registro y esperamos a que se cargue el enlace de
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "@href", textOption,
				getTimeout());
		// Tiene que haber un s�lo elemento.
		assertTrue(elementos.size() == 1);
		// Ahora lo clickamos
		elementos.get(0).click();
		// Esperamos a que sea visible un elemento concreto
		elementos = SeleniumUtils.EsperaCargaPagina(driver, criterio, textoDestino, getTimeout());
		// Tiene que haber un s�lo elemento.
		assertTrue(elementos.size() == 1);
	}
	
	public static void clickOption(WebDriver driver, String textOption) {
		// CLickamos en la opci�n de registro y esperamos a que se cargue el enlace de
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "@href", textOption,
				getTimeout());
		// Tiene que haber un s�lo elemento.
		assertTrue(elementos.size() == 1);
		elementos.get(0).click();
	}
	
	public static void clickOptionID(WebDriver driver, String textOption) {
		// CLickamos en la opci�n de registro y esperamos a que se cargue el enlace de
		List<WebElement> elementos = SeleniumUtils.EsperaCargaPagina(driver, "id", textOption,
				getTimeout());
		// Tiene que haber un s�lo elemento.
		elementos.get(0).click();
	}



}
