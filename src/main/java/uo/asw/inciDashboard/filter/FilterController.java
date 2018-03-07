package uo.asw.inciDashboard.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uo.asw.dbManagement.DBManagementFacade;
import uo.asw.dbManagement.model.Filter;

@Controller
public class FilterController implements SetFilter {

	@Autowired
	private DBManagementFacade dbManagement;
	
	@Override
	@RequestMapping("/incidences/filter/{idFilter}")
	public String setFilterGet(Model model, @PathVariable Long id) {
		model.addAttribute("filter", dbManagement.getFilter(id)); // TODO - mejorar??
		return "operator/filter";
	}

	@Override
	@RequestMapping(value ="/incidences/filter", method = RequestMethod.POST)
	public String setFilterPost(@ModelAttribute Filter filter) {
		dbManagement.updateFilter(filter); // TODO - mejorar??
		return "operator/filter";
	}

}
