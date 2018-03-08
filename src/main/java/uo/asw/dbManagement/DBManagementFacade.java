package uo.asw.dbManagement;

import java.util.List;

import uo.asw.dbManagement.model.Agent;
import uo.asw.dbManagement.model.Filter;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.model.Operator;

public interface DBManagementFacade {
	
	//@Query("SELECT f FROM Filter f WHERE f.id = ?1")
	public Filter getFilter(Long idFilter);

	public void updateFilter(Filter Filter);	
	
	//@Query("SELECT i FROM Incidence i WHERE i.id = ?1")
	public Incidence getIncidence(Long idIncidence);
	
	//@Query("SELECT o.incidences FROM Operator o WHERE o.id = ?1")
	public List<Incidence> getOperatorIncidences(Long idOperator); //TODO - Cuando este implementado y funcione, hacer que devuelva Page<Incidence> para meter paginacion

	public List<Incidence> getIncidencesOfCategory(String[] categories); //TODO - Cuando este implementado y funcione, hacer que devuelva Page<Incidence> para meter paginacion
	
	public void updateIncidence(Incidence incidence);//¿?¿?¿
	
	public Agent getAgent(String login, String password, String kind);
	
	public Operator getOperator(String identifier);//TODO - añadir a la documentacion
	
}
