package uo.asw.inciDashboard.filter;

import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class RIncidenceP {
	
	/**
	 * Se encarga de parsear el String que le llega, convirtiéndolo a un objeto JSON, 
	 * y transformando dicho JSON en un objeto Incidence.
	 * 
	 * @param JSONString
	 * @return
	 */
	public Incidence jsonStringToIncidence(String JSONString) {
		JSONObject json = new JSONObject(JSONString);
		
		//TODO - Parsear el json a incidence
		
		return incidence;
	}
	
}
