package uo.asw.inciDashboard.storedIncidences;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import uo.asw.dbManagement.model.Incidence;

public interface StoredIncidences {
	
	public String showOperatorIncidences(Model model, @PathVariable Long idOperator);
	
	public String showIncidencesOfCategoryGet(Model model);
	
	public String updateIncidenceGet(@PathVariable Long idIncidence);
	
	public String updateIncidencePost(@ModelAttribute Incidence incidence);
	
}
