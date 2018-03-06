package uo.asw.inciDashboard.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uo.asw.dbManagement.model.Filter;
import uo.asw.inciDashboard.filter.services.FilterService;

@Controller
public class FilterController implements SetFilter {

	@Autowired
	private FilterService filterService;
	
	@Override
	@RequestMapping("/operator/filter")
	public String setFilterGet(Model model) {
		model.addAttribute("filter", filterService.getFilter()); // TODO - mejorar??
		return "operator/filter";
	}

	@Override
	@RequestMapping(value ="/operator/filter", method = RequestMethod.POST)
	public String setFilterPost(@ModelAttribute Filter filter) {
		filterService.addFilter(filter); // TODO - mejorar??
		return "operator/filter";
	}

}
