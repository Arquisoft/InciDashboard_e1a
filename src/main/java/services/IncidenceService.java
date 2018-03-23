package services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import entities.Incidence;
import repositories.IncidenceRepository;

@Service
public class IncidenceService {

	@Autowired
	IncidenceRepository incidenceRepository;

	public void save(Incidence incidence) {
		incidenceRepository.save(incidence);
	}

	public List<Incidence> findAll() {
		List<Incidence> incidences = new ArrayList<>();
		incidenceRepository.findAll().forEach(incidences::add);
		return incidences;
	}
}
