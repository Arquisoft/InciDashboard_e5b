package uo.asw.inciDashboard.filter;

import org.springframework.web.bind.annotation.ModelAttribute;

import uo.asw.inciDashboard.entities.Filter;

public interface SetFilter {
	public String setFilterGet();
	public String setFilterPost(@ModelAttribute Filter filter);
}
