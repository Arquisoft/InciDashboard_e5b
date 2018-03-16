package uo.asw.dbManagement;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Agent;
import uo.asw.dbManagement.model.Filter;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.model.Operator;
import uo.asw.dbManagement.repositories.AgentsRepository;
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
	
	@Autowired
	private AgentsRepository agentsRepository;
	
	/**
	 * Permite la solicitud del filtro guardado en la BD (sólo hay un filtro). 
	 * Si no hay ningún filtro en la BD, lo crea, lo guarda en BD y lo devuelve.
	 */
	public Filter getFilter() {
		List<Filter> filters = new ArrayList<Filter>();
		filterRepository.findAll().forEach(filters::add);
		
		//Si la lista de filtros esta vacia, tenemos que crear un filtro
		if (filters.isEmpty()) {
			Filter filter = new Filter();
			filterRepository.save(filter); // lo guardamos
			
			// Lo volvemos a recuperar para que tenga el id actualizado
			filterRepository.findAll().forEach(filters::add);
		}
		
		return filters.get(0);		
	}

	public void updateFilter(Filter filter) {		
		filterRepository.save(filter.setId(1));
	}
	
	public Incidence getIncidence(Long idIncidence) {
		return incidencesRepository.findOne(idIncidence);
	}
	
	public List<Incidence> getOperatorIncidences(Long idOperator) {
		return incidencesRepository.getOperatorIncidences(idOperator);
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

	@Override
	public Agent getAgent(String login, String password, String kind) {
		return agentsRepository.findByLoginPasswordAndKind(login, password, kind);
	}

	@Override
	public Operator getOperator(String identifier) {
		return operatorsRepository.findByIdentifier(identifier);
	}

}
