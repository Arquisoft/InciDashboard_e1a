package com.uniovi.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.uniovi.entities.Incidence;


@Repository
public interface IncidenceRepository extends CrudRepository<Incidence, Long>{
	
	
}
