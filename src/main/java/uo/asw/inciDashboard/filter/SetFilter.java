package uo.asw.inciDashboard.filter;

import org.springframework.web.bind.annotation.ModelAttribute;

import uo.asw.dbManagement.model.Filter;

public interface SetFilter {
	public String setFilterGet();
	public String setFilterPost(@ModelAttribute Filter filter);
}
