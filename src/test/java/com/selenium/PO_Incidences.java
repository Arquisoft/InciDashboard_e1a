package com.selenium;

import org.openqa.selenium.WebDriver;

import com.uniovi.entities.Status;

public class PO_Incidences extends PO_View {

	public static void changeState(WebDriver driver, String incidencia, Status status) {
		if (status == Status.CLOSED)
			PO_NavView.clickOptionID(driver, "close" + incidencia);
		else {
			PO_NavView.clickOption(driver, "/incidences/edit/");
			switch (status) {
			case OPENED:
				PO_NavView.clickOptionID(driver, "btn-abrir");
				break;
			default:
				break;
			}
		}
	}

}
