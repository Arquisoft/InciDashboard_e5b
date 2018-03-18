package uo.asw.inciDashboard.storedIncidences;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

public interface ShowIncidencesOfCategory {

	public String showIncidencesOfCategoryGet(Model model, @PathVariable Long category_id);
	
}
