package uo.asw.dbManagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import uo.asw.dbManagement.model.Filter;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.repositories.FilterRepository;
import uo.asw.dbManagement.repositories.IncidencesRepository;
import uo.asw.dbManagement.repositories.OperatorsRepository;

public class DBManagementFacadeImpl implements DBManagementFacade{

	@Autowired
	private OperatorsRepository operatorsRepository;
	
	@Autowired
	private FilterRepository filterRepository;
	
	@Autowired
	private IncidencesRepository incidencesRepository;
	
	public Filter getFilter() {
		List<Filter> filters = new ArrayList<Filter>();
		filterRepository.findAll().forEach(filters::add);
		return filters.get(0);
	}

	public void updateFilter(Filter Filter) {
		filterRepository.save(Filter);
	}
	
	public Incidence getIncidence(Long idIncidence) {
		return incidencesRepository.findOne(idIncidence);
	}
	
	public List<Incidence> getOperatorIncidences(Long idOperator) {
		return incidencesRepository.getOperatorIncidences(idOperator);
	}

	public List<Incidence> getIncidencesOfCategory(List<String> categories) {
		return incidencesRepository.getIncidencesOfCategory(categories);
	}

	public void updateIncidence(Incidence incidence) {
		incidencesRepository.save(incidence);
	}

}