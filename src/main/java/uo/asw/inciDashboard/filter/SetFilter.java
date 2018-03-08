package uo.asw.inciDashboard.filter;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import uo.asw.dbManagement.model.Filter;

public interface SetFilter {
	
	public String setFilterGet(Model model, @PathVariable Long id);
	
	public String setFilterPost(@ModelAttribute Filter filter);
	
}
