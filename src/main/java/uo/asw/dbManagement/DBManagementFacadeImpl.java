package uo.asw.dbManagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Filter;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.repositories.FilterRepository;
import uo.asw.dbManagement.repositories.IncidencesRepository;
import uo.asw.dbManagement.repositories.OperatorsRepository;

@Service
public class DBManagementFacadeImpl implements DBManagementFacade{

	@Autowired
	private OperatorsRepository operatorsRepository;
	
	@Autowired
	private FilterRepository filterRepository;
	
	@Autowired
	private IncidencesRepository incidencesRepository;
	
	public Filter getFilter(Long idFilter) {
		
		/*List<Filter> filters = new ArrayList<Filter>();
		filterRepository.findAll().forEach(filters::add);
		return filters.get(0);*/
		return filterRepository.findById(idFilter);
		
	}

	public void updateFilter(Filter Filter) {
		filterRepository.save(Filter);
	}
	
	public Incidence getIncidence(Long idIncidence) {
		return incidencesRepository.findOne(idIncidence);
	}
	
	public List<Incidence> getOperatorIncidences(String identifier) {
		return incidencesRepository.getOperatorIncidences(identifier);
	}

	public List<Incidence> getIncidencesOfCategory(String[] categories) {
		
		List<Incidence> incidenciasConAlMenosUnaDeEsasCategorias=new ArrayList<Incidence>();
		for (String category : categories) {
			List<Incidence> incidenciasConEsaCategoria=incidencesRepository.getIncidencesOfCategory(category);
			for (Incidence incidence : incidenciasConEsaCategoria) {
				if(!incidenciasConAlMenosUnaDeEsasCategorias.contains(incidence)) {
					incidenciasConAlMenosUnaDeEsasCategorias.add(incidence);
				}
			}
		}
		return incidenciasConAlMenosUnaDeEsasCategorias;
		
	}

	public void updateIncidence(Incidence incidence) {
		incidencesRepository.save(incidence);
	}

}
