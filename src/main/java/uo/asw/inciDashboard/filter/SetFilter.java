package uo.asw.inciDashboard.filter;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import uo.asw.dbManagement.model.Filter;

public interface SetFilter {
	
	public String setFilterGet(Model model);
	
	public String setFilterPost(@RequestParam String filterResponse,
			@RequestParam(required=false) String applyOn, 
			@RequestParam(required=false) String propertyType,
			@RequestParam(required=false) String filterOperation);
	
}
