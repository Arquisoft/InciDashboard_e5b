package uo.asw.inciDashboard.storedIncidences;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uo.asw.dbManagement.DBManagementFacade;
import uo.asw.dbManagement.DBManagementFacadeImpl;
import uo.asw.dbManagement.model.Category;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.services.OperatorsService;

@Controller
public class StoredIncidencesController implements ShowOperatorIncidences, ShowIncidencesOfCategory, UpdateIncidence {

	@Autowired
	private DBManagementFacade dBManagement;
	
	@Autowired
	private OperatorsService operatorsService;

	@Override
	@RequestMapping("/incidences/operator")
	public String showOperatorIncidences(Model model,Principal principal) {
				
			String identifier=principal.getName();
			List<Incidence> operatorIncidences=operatorsService.getOperatorIncidences(identifier);
			model.addAttribute("operatorIncidences", operatorIncidences);
		 	return "incidences/operator";
	}
	
	@RequestMapping("/incidences/categories/select")
	public String showIncidencesOfCategorySelect(Model model) {
		//TODO - Devuelve una vista con un formulario para indicar las categorias
		// Dicha vista hara una peticion get a la direccion del metodo de abajo
		//List<String> categorys=dBManagement.findAllCategorys();
		List<Category> categorys=dBManagement.findCategorys();
		model.addAttribute("categorys", categorys);
		model.addAttribute("selectCategory", new Category(""));
		return "incidences/categories/select";
	}
	
	@Override
	@RequestMapping("/incidences/categories/show/{category_id}")
	public String showIncidencesOfCategoryGet(Model model, @PathVariable Long category_id) {

		Category c=dBManagement.findCategoryById(category_id);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String operator_identifier = auth.getName();
		
		List<Incidence> incidencesOfCategory=dBManagement.getIncidencesOfCategoryForOperator(c.getName(),operator_identifier);
		
		model.addAttribute("selectCategory", c);
		model.addAttribute("incidencesOfCategory", incidencesOfCategory);
		
		return "incidences/categories/select :: tableIncidences";
	}

	@Override
	@RequestMapping("/incidences/update/{idIncidence}")
	public String updateIncidenceGet(Model model, @PathVariable Long idIncidence) {
		// TODO Habra que sacar la incidencia de la BD para meterla en la plantilla
		//model.addAttribute("incidence", dBManagement.getIncidence());
		model.addAttribute("incidence", dBManagement.getIncidence(idIncidence));
		return "incidences/update";
	}

	@Override
	@RequestMapping(value ="/incidences/update/{idIncidence}", method = RequestMethod.POST)
	public String updateIncidencePost(@ModelAttribute Incidence incidence) { 
		//dBManagement.updateIncidence(incidence);
		//Retornar alguna vista
		return "";
	}

}
