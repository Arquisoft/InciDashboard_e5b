package uo.asw.inciDashboard.storedIncidences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uo.asw.dbManagement.IncidencesRepository;
import uo.asw.dbManagement.model.Incidence;

public class StoredIncidencesController implements StoredIncidences {

	@Autowired
	private IncidencesRepository incidencesRepository;

	@Override
	@RequestMapping("/incidences/operator/{idOperator}")
	public String showOperatorIncidences(Model model, @PathVariable Long idOperator) {
		// TODO Auto-generated method stub
		model.addAttribute("listIncidences", 
				incidencesRepository.getOperatorIncidences(idOperator));
		return ;
	}
	
	//XXX MAL!!! --> Hay que repensar esto. Se necesita un post seguramente, o pasar por params por get
	@Override
	@RequestMapping("/incidences/categories")
	public String showIncidencesOfCategoryGet(Model model) {
		model.addAttribute("listIncidences", 
				incidencesRepository.getIncidencesOfCategory(categorias));
		return ;
	}

	@Override
	@RequestMapping("/incidences/update/{idIncidence}")
	public String updateIncidenceGet(@PathVariable Long idIncidence) {
		// TODO Auto-generated method stub
		return "incidences/update";
	}

	@Override
	@RequestMapping(value ="/incidences/update/{idIncidence}", method = RequestMethod.POST)
	public String updateIncidencePost(@ModelAttribute Incidence incidence) {
		// TODO Auto-generated method stub
		incidencesRepository.save(incidence);
		
	}

}
