package uo.asw.dbManagement;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.model.Operator;

public interface IncidencesRepository extends CrudRepository<Incidence, Long>{
	
	public void updateIncidence(Incidence incidence);
	
	@Query("")// TODO - Implementar
	public List<Incidence> getOperatorIncidences(Operator operator);
	
	@Query("")// TODO - Implementar
	public List<Incidence> getIncidencesOfCategory(List<String> categorias);
}
