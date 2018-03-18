package uo.asw.dbManagement.repositories;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import uo.asw.dbManagement.model.Incidence;

public interface IncidencesRepository extends CrudRepository<Incidence, Long>{
		
	@Query("SELECT o.incidences FROM Operator o WHERE o.identifier = ?1") //TODO - revisar
	public List<Incidence> getOperatorIncidences(String identifier);
	
	@Query("SELECT i FROM Incidence i where ?1 IN i.tags")//¿?¿?¿?¿
	public List<Incidence> getIncidencesOfCategory(String category);

}
