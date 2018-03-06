package uo.asw.inciDashboard.filter.services;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.inciDashboard.entities.Filter;
import uo.asw.inciDashboard.filter.repositories.FilterRepository;

@Service
public class FilterService {
	
	@Autowired
	private FilterRepository filterRepository;

	@PostConstruct
	public void init() {}

	// TODO . cambiar?
	public List<Filter> getFilters() {
		List<Filter> Filters = new ArrayList<Filter>();
		filterRepository.findAll().forEach(Filters::add);
		return Filters;
	}

	public Filter getFilter(Long id) {
		return filterRepository.findOne(id);
	}

	public void addFilter(Filter Filter) {
		filterRepository.save(Filter);
	}

	public void deleteFilter(Long id) {
		filterRepository.delete(id);
	}
	
	
}