package uo.asw.inciDashboard.filter;

import org.springframework.beans.factory.annotation.Autowired;

import uo.asw.dbManagement.model.Incidence;
import uo.asw.inciDashboard.filter.services.RIncidenceP;

public class ReceiveIncidenceImpl implements ReceiveIncidence {

	@Autowired
	private RIncidenceP rIncidenceP; 
	
	@Override
	public void receiveIncidence(String jsonStringIncidence) {
		Incidence incidence = rIncidenceP.jsonStringToIncidence(jsonStringIncidence);
	}

}
