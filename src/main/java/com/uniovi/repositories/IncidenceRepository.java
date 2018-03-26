package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uniovi.entities.Incidence;


@Repository
public interface IncidenceRepository extends CrudRepository<Incidence, Long>{
	
	@Query("SELECT r FROM Incidence r WHERE (LOWER(r.description) LIKE LOWER(?1) OR LOWER(r.name) LIKE LOWER(?1) OR LOWER(r.etiquetas) LIKE LOWER(?1))")
			List<Incidence> searchByDescriptionAndName(String seachtext);
	
}
