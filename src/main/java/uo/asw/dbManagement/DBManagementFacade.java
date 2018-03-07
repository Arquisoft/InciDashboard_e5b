package uo.asw.dbManagement;

import java.util.List;

import uo.asw.dbManagement.model.Filter;
import uo.asw.dbManagement.model.Incidence;

public interface DBManagementFacade {

	public Filter getFilter();

	public void updateFilter(Filter Filter);	
	
	public Incidence getIncidence(long idIncidence);
	
	public List<Incidence> getOperatorIncidences(long idOperator); //TODO - Cuando este implementado y funcione, hacer que devuelva Page<Incidence> para meter paginacion

	public List<Incidence> getIncidencesOfCategory(List<String> categories); //TODO - Cuando este implementado y funcione, hacer que devuelva Page<Incidence> para meter paginacion

	public void updateIncidence(Incidence incidence);
	
}
