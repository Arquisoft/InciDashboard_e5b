package uo.asw.inciDashboard.storedIncidences;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uo.asw.dbManagement.DBManagementFacadeImpl;
import uo.asw.dbManagement.model.Incidence;

public class StoredIncidencesController implements ShowOperatorIncidences, ShowIncidencesOfCategory, UpdateIncidence {

	@Autowired
	private DBManagementFacadeImpl dBManagement;

	@Override
	@RequestMapping("/incidences/operator")
	public String showOperatorIncidences(Model model, Principal principal) {
		// TODO Auto-generated method stub
		//TODO - Obtener el operario en sesión, sacar su id y pasarlo al metodo de abajo 
		model.addAttribute("listIncidences", 
				dBManagement.getOperatorIncidences());
		return "incidences/operator";
	}
	
	@RequestMapping("/incidences/categories/select")
	public String showIncidencesOfCategorySelect(Model model) {
		//TODO - Devuelve una vista con un formulario para indicar las categorias
		// Dicha vista hara una peticion get a la direccion del metodo de abajo
		return "incidences/categories/select";
	}
	
	@Override
	@RequestMapping("/incidences/categories/show")
	public String showIncidencesOfCategoryGet(Model model) {
		/*
		 * TODO
		 * Recibe una lista de categorias en la petición, y hay que pasar dicha lista a dbManagement
		 */
		model.addAttribute("listIncidences", 
				dBManagement.getIncidencesOfCategory());
		// Deberia devolver las incidencias paginadas Page<Incidence>
		return "incidences/categories/show";
	}

	@Override
	@RequestMapping("/incidences/update/{idIncidence}")
	public String updateIncidenceGet(Model model, @PathVariable Long idIncidence) {
		// TODO Habra que sacar la incidencia de la BD para meterla en la plantilla
		model.addAttribute("incidence", dBManagement.getIncidence());
		return "incidences/update";
	}

	@Override
	@RequestMapping(value ="/incidences/update/{idIncidence}", method = RequestMethod.POST)
	public String updateIncidencePost(@ModelAttribute Incidence incidence) { 
		dBManagement.updateIncidence(incidence);
		//Retornar alguna vista
	}

}
