package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.Operator;

public interface OperatorRepository extends CrudRepository<Operator, Long> {

	public Operator findByName(String operator);

//	@Query("SELECT o FROM Operator o Having MIN(o.incidences)")
//	public Operator findLikely(List<String> tags);
}
