package uo.asw.inciDashboard.storedIncidences;

import org.springframework.beans.factory.annotation.Autowired;

import uo.asw.dbManagement.IncidencesRepository;

public class StoredIncidencesController implements StoredIncidences {

	@Autowired
	private IncidencesRepository incidencesRepository;
	
	@Override
	public String updateIncidenceGet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateIncidencePost() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showOperatorIncidences() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String showIncidencesOfCategoryGet() {
		// TODO Auto-generated method stub
		return null;
	}

}
