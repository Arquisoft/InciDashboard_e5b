package uo.asw.inciDashboard.filter.services;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RIncidenceP {
	
	public JSONObject StringToJson(String JSONString) {
		JSONObject json = new JSONObject(JSONString);
		return json;
	}
	
}
