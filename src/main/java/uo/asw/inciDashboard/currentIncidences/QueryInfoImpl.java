package uo.asw.inciDashboard.currentIncidences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class QueryInfoImpl implements QueryInfo {

	@Autowired
	private IncidenceRepository incidenceRepository;
	

	@Override
	public DashboardData queryInfo() {
		return new DashboardData(incidenceRepository.findAll());
	}
}