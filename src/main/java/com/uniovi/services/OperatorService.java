package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Incidence;
import com.uniovi.entities.Operator;
import com.uniovi.repositories.IncidenceRepository;
import com.uniovi.repositories.OperatorRepository;

@Service
public class OperatorService {

	@Autowired
	private OperatorRepository operatorRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private IncidenceRepository incidenceRepository;

	public Operator findByName(String operator) {
		return operatorRepository.findByName(operator);
	}

	public List<Operator> findAll() {
		List<Operator> operators = new ArrayList<>();
		operatorRepository.findAll().forEach(operators::add);
		return operators;
	}

	public void addOperator(Operator operator) {
		operator.setPassword(bCryptPasswordEncoder.encode(operator.getPassword())); // Encripto la contraseña
		operatorRepository.save(operator);
	}

	public void assignIncidence(Incidence incidence) {
		Operator op = findOperatorWithLessIncidences();
		op.assignIncidence(incidence);
		incidence.setOperator(op);
		incidence.assigned();
		incidenceRepository.save(incidence); // BUG
	}

	public Operator getOperator() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication(); // Obtengo el operador
		String username = auth.getName();
		Operator operator = findByName(username);
		return operator;
	}

	public Operator findOperatorWithMoreIncidences() {
		List<Operator> operadores = findAll();
		Operator elegido = operadores.get(0);
		for (Operator op : operadores) {
			if (op.getNumberOfIncidences() > elegido.getNumberOfIncidences())
				elegido = op;
		}
		return elegido;
	}

	public Operator findOperatorWithLessIncidences() {
		List<Operator> operadores = findAll();
		Operator elegido = operadores.get(0);
		for (Operator op : operadores) {
			if (op.getNumberOfIncidences() < elegido.getNumberOfIncidences())
				elegido = op;
		}
		return elegido;
	}

}
