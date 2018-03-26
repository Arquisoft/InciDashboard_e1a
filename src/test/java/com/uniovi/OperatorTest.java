package com.uniovi;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;

import com.uniovi.entities.Operator;
import com.uniovi.services.OperatorService;
import com.uniovi.services.SecurityService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OperatorTest {

	@Autowired
	private OperatorService operatorService;

	@Autowired
	private SecurityService securityService;

	private Operator operator1;
	private Operator operator2;
	private Operator operator3;
	private Operator operator4;
	private Operator operator5;

	@Before
	public void init() {
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

		try {
			securityService.autoLogin("op12", "123456");
		} catch (Exception e) {
			assertEquals("Bad credentials", e.getMessage());
		}

		// Probamos a logearnos con un usuario que no existe
		try {
			securityService.autoLogin("op112", "0");
		} catch (Exception e) {
			assertEquals("Bad credentials", e.getMessage());
		}

	}

	@Test
	public void test() {
		fail("Not yet implemented");
	}

}
