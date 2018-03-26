package com.uniovi.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uniovi.entities.Incidence;
import com.uniovi.entities.Operator;
import com.uniovi.entities.Status;
import com.uniovi.repositories.IncidenceRepository;

;

@Service
public class IncidenceService {

	@Autowired
	IncidenceRepository incidenceRepository;

	@Autowired
	FilterService filterService;

	@Autowired
	OperatorService operatorService;

	public void addIncidence(Incidence incidence) {
		incidenceRepository.save(incidence);
		filterService.filterFields(incidence);
		operatorService.assignIncidence(incidence);
	}

	public List<Incidence> findAll() {
		List<Incidence> incidences = new ArrayList<>();
		incidenceRepository.findAll().forEach(incidences::add);
		return incidences;
	}

	public Incidence verIncidencia(Long id) {
		return incidenceRepository.findOne(id);
	}

	public List<Incidence> searchIncidencesByNameAndDescription(String searchText, Operator operator){
		List<Incidence> incidences = new ArrayList<Incidence>();
		List<Incidence> incidencesTotal = incidenceRepository.searchByDescriptionAndName(searchText);
		for (Incidence incidence : incidencesTotal) {
			if (incidence.getOperator().equals(operator)) {
				incidences.add(incidence);
			}
		}
		
		return incidences;
	}
	
	public int countIncidencesType(Status status) {
		List<Incidence> incidences = findAll();
		int cont=0;
		for (Incidence incidence : incidences) {
			if(incidence.getState().equals(status))
				cont++;
		}
		return cont;
	}
}
