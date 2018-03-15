package uo.asw.inciDashboard.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import uo.asw.dbManagement.DBManagementFacade;

@Controller
public class FilterController implements SetFilter {

	@Autowired
	private DBManagementFacade dbManagement;
	
	@Override
	@RequestMapping("/incidences/filter")
	public String setFilterGet(Model model) {
		model.addAttribute("filter", dbManagement.getFilter());
		return "operator/filter";
	}

	@Override
	@RequestMapping(value ="/incidences/filter", method = RequestMethod.POST)
	public String setFilterPost(@RequestParam String filterResponse,
			@RequestParam(required=false) String applyOn, 
			@RequestParam(required=false) String propertyType,
			@RequestParam(required=false) String filterOperation) {
		
		dbManagement.updateFilter(filter); // TODO - mejorar??
		return "operator/filter";
	}

}
