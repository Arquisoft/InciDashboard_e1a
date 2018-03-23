package repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import entities.Incidence;

@Repository
public interface IncidenceRepository extends CrudRepository<Incidence, Long>{
	
	
}
