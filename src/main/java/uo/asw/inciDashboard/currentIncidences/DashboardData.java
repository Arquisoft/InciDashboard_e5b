package uo.asw.inciDashboard.currentIncidences;

import java.util.List;

import uo.asw.dbManagement.model.Incidence;

public class DashboardData {
	private List<Incidence> incidencias;

	public DashboardData(List<Incidence> incidencias) {
		this.incidencias = incidencias;
	}

	public List<Incidence> getIncidencias() {
		return incidencias;
	}
	
	
}
