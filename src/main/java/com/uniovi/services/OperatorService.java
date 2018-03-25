package com.uniovi.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Operator;
import com.uniovi.repositories.OperatorRepository;

@Service
public class OperatorService {

	@Autowired
	OperatorRepository operatorRepository;

	public Operator findByName(String operator) {
		return operatorRepository.findByName(operator);
	}

	public void addOperator(Operator operator) {
		operatorRepository.save(operator);
	}

}
