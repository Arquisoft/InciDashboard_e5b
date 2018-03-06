package uo.asw.inciDashboard.storedIncidences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import uo.asw.dbManagement.IncidencesRepository;
import uo.asw.dbManagement.model.Incidence;

public class StoredIncidencesController implements StoredIncidences {

	@Autowired
	private IncidencesRepository incidencesRepository;

	@Override
	@RequestMapping("/operator/{idOperator}/incidences")
	public String showOperatorIncidences(Model model, @PathVariable Long idOperator) {
		// TODO Auto-generated method stub
		model.addAttribute("listIncidences", 
				incidencesRepository.getOperatorIncidences(idOperator));
		return "operator/incidences";
	}
	
	//XXX MAL!!! --> Hay que repensar esto. Se necesita un post seguramente, o pasar por params por get
	@Override
	@RequestMapping("/incidences/categories")
	public String showIncidencesOfCategoryGet(Model model) {
		model.addAttribute("listIncidences", 
				incidencesRepository.getIncidencesOfCategory(categorias));
		return "operator/incidences";
	}

	@Override
	@RequestMapping("/incidences/update/{idIncidence}")
	public String updateIncidenceGet(@PathVariable Long idIncidence) {
		// TODO Auto-generated method stub
		return "incidence/update";
	}

	@Override
	public String updateIncidencePost(@ModelAttribute Incidence incidence) {
		// TODO Auto-generated method stub
		incidencesRepository.save(incidence);
		
	}

}
