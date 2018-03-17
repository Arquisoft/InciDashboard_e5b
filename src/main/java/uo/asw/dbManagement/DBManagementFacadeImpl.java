package uo.asw.dbManagement;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.record.chart.CategorySeriesAxisRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Agent;
import uo.asw.dbManagement.model.Category;
import uo.asw.dbManagement.model.Filter;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.model.Operator;
import uo.asw.dbManagement.repositories.AgentsRepository;
import uo.asw.dbManagement.repositories.CategoriesRepository;
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
	
	@Autowired
	private CategoriesRepository categoriesRepository;
	
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
	
	/*
	 * Este no funciona por la consulta SQL
	 */
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
	
	public List<String> findAllCategorys(){
		
		List<String> categorys=new ArrayList<String>();
		
		for (Incidence incidence : incidencesRepository.findAll()) {
			String[] categorysForOperatorSinSeparar=incidencesRepository.findCategorysForIncidence(incidence);
			String[] categorysForOperator=separarCategorysEnArray(categorysForOperatorSinSeparar);
			for (String category : categorysForOperator) {
				if(!categorys.contains(category)) {
					categorys.add(category);
				}
			}
		}
		return categorys;
		
	}
	
	public List<Incidence> getIncidencesOfCategory(String category){
		
		List<Incidence> incidencesForCategory=new ArrayList<Incidence>();
		
		for (Incidence incidence : incidencesRepository.findAll()) {
			if(conatinsInArray(incidence.getTags(), category)) {
				incidencesForCategory.add(incidence);
			}
		}
		return incidencesForCategory;
		
	}
	
	public boolean conatinsInArray(String[] array, String element) {
		
		for (String e : array) {
			
			if(e.equals(element)) {
				return true;
			}
		}
		return false;
	}
	
	private String[] separarCategorysEnArray(String[] array) {
		
		return array[0].split(",");
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

	@Override
	public List<Category> findCategorys() {
		
		List<Category> categories = new ArrayList<Category>();
		categoriesRepository.findAll().forEach(categories::add);
		
		return categories;
	}

	@Override
	public Category findCategoryById(Long id) {
		return categoriesRepository.findOne(id);
	}

}
