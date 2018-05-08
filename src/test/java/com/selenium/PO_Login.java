package com.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PO_Login extends PO_View {

	public static void fillForm(WebDriver driver, String user, String passwd) {
		SeleniumUtils.esperarSegundos(driver, 1);
		WebElement login = driver.findElement(By.name("username"));
		login.click();
		login.clear();
		login.sendKeys(user);
		WebElement name = driver.findElement(By.name("password"));
		name.click();
		name.clear();
		name.sendKeys(passwd);
		// Pulsar el boton de Alta.
		By boton = By.className("btn"); // Por qu� toma la clase del elemento y no el atributo clase.
		driver.findElement(boton).click();
	}

}
