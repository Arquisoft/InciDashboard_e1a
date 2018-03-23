package util;

import java.util.Calendar;
import java.util.Date;

import entities.Incidence;
import entities.Operator;

public class Util {

	/**
	 * Solo prueba
	 * 
	 * @param data
	 * @return 
	 */
	public static Incidence createIncidence(String data) {
		Operator op = new Operator("Paco");
		return new Incidence(op, data, "prueba", new Date());
	}

}
