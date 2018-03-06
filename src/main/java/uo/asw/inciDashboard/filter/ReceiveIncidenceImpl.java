package uo.asw.inciDashboard.filter;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import uo.asw.inciDashboard.filter.services.RIncidenceP;

public class ReceiveIncidenceImpl implements ReceiveIncidence {

	@Autowired
	private RIncidenceP rIncidenceP; 
	
	@Override
	public void receiveIncidence(String jsonStringIncidence) {
		JSONObject jsonIncidence = rIncidenceP.StringToJson(jsonStringIncidence);
	}

}
