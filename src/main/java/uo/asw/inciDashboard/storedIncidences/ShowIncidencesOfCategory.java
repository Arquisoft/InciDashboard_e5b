package uo.asw.inciDashboard.storedIncidences;

import java.util.List;

import org.springframework.ui.Model;

public interface ShowIncidencesOfCategory {

	public String showIncidencesOfCategoryGet(Model model,List<String> categorys);
	
}
