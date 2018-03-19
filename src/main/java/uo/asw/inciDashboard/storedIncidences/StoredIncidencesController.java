package uo.asw.inciDashboard.storedIncidences;

import java.security.Principal;
import java.util.ArrayList;
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
import uo.asw.dbManagement.model.Category;
import uo.asw.dbManagement.model.Incidence;

@Controller
public class StoredIncidencesController implements ShowOperatorIncidences, ShowIncidencesOfCategory, UpdateIncidence {

	@Autowired
	private DBManagementFacade dBManagement;

	@Override
	@RequestMapping("/incidences/operator")
	public String showOperatorIncidences(Model model,Principal principal) {
				
			String identifier=principal.getName();
			List<Incidence> operatorIncidences=dBManagement.getOperatorIncidences(identifier);
			
			model.addAttribute("operatorIncidences", operatorIncidences);
			model.addAttribute("updateHabilitado", false);
			model.addAttribute("listStatus", new ArrayList<String>());
			model.addAttribute("selectIncidence", new Incidence(""));
			
		 	return "incidences/operator";
	}
	
	@RequestMapping("/incidences/categories/select_show")
	public String showIncidencesOfCategorySelect(Model model) {
		//TODO - Devuelve una vista con un formulario para indicar las categorias
		// Dicha vista hara una peticion get a la direccion del metodo de abajo
		//List<String> categorys=dBManagement.findAllCategorys();
		List<Category> categorys=dBManagement.findAllCategorys();
		model.addAttribute("categorys", categorys);
		model.addAttribute("selectCategory", new Category(""));
		return "incidences/categories/select_show";
	}
	
	@Override
	@RequestMapping("/incidences/categories/show/{category_id}")
	public String showIncidencesOfCategoryGet(Model model, @PathVariable Long category_id) {

		Category c=dBManagement.findCategoryById(category_id);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String operator_identifier = auth.getName();
		
		List<Incidence> incidencesOfCategory=dBManagement.getIncidencesOfCategory(c.getName(),operator_identifier);
		
		model.addAttribute("selectCategory", c);
		model.addAttribute("incidencesOfCategory", incidencesOfCategory);
		
		return "incidences/categories/select_show :: tableIncidences";
	}

	@Override
	@RequestMapping("/incidences/update/changeStatusOn/{idIncidence}")
	public String updateIncidenceGet(Model model, @PathVariable Long idIncidence) {
		// TODO Habra que sacar la incidencia de la BD para meterla en la plantilla
		//model.addAttribute("incidence", dBManagement.getIncidence());
		Incidence incidence=dBManagement.getIncidence(idIncidence);
		List<Incidence> operatorIncidences=dBManagement.getOperatorIncidences(incidence.getIdentifier());
		
		List<String> estados=new ArrayList<String>();
		estados.add("Abierta");
		estados.add("En proceso");
		estados.add("Cerrada");
		estados.add("Anulada");
		
		//model.addAttribute("operatorIncidences", operatorIncidences);
		model.addAttribute("updateHabilitado", true);
		model.addAttribute("listStatus", estados);
		model.addAttribute("selectIncidence", incidence);
		//model.addAttribute("currentIncidence", incidence);
		 
		return "incidences/operator :: tableUpdate";
	}

	@Override
	@RequestMapping(value ="/incidences/update/{idIncidence}", method = RequestMethod.POST)
	public String updateIncidencePost(@ModelAttribute Incidence incidence) { 
		//dBManagement.updateIncidence(incidence);
		//Retornar alguna vista
		return "";
	}

}
