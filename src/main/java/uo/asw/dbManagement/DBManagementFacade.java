package uo.asw.dbManagement;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import uo.asw.dbManagement.model.Filter;
import uo.asw.dbManagement.model.Incidence;

public interface DBManagementFacade {
	
	@Query("SELECT f FROM Filter f WHERE f.id = ?1")
	public Filter getFilter(long idFilter);

	public void updateFilter(Filter Filter);	
	
	@Query("SELECT i FROM Incidence i WHERE i.id = ?1")
	public Incidence getIncidence(long idIncidence);
	
	@Query("SELECT o.incidences FROM Operator o WHERE o.id = ?1")
	public List<Incidence> getOperatorIncidences(long idOperator); //TODO - Cuando este implementado y funcione, hacer que devuelva Page<Incidence> para meter paginacion

	public List<Incidence> getIncidencesOfCategory(List<String> categories); //TODO - Cuando este implementado y funcione, hacer que devuelva Page<Incidence> para meter paginacion
	
	//@Query("UPDATE Incidence SET incidence = ?2 WHERE id = ?1")
	public void updateIncidence(long idIncidence, Incidence incidence);//¿?¿?¿
	
}
