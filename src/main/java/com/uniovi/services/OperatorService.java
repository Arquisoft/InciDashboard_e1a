package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Operator;
import com.uniovi.repositories.OperatorRepository;

@Service
public class OperatorService {

	@Autowired
	private OperatorRepository operatorRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Operator findByName(String operator) {
		return operatorRepository.findByName(operator);
	}

	public void addOperator(Operator operator) {
		operator.setPassword(bCryptPasswordEncoder.encode(operator.getPassword())); //Encripto la contraseña
		operatorRepository.save(operator);
	}
	
	public Operator getOperator() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); //Obtengo el operador
		String username = auth.getName();
		Operator operator = findByName(username);
		return operator;
	}

}
