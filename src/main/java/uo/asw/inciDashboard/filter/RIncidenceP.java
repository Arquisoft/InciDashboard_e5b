package uo.asw.inciDashboard.filter;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Incidence;

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
		List<String> names = Arrays.asList(JSONObject.getNames(json));
		
		String login = getString(names, json, "login");
		String password = getString(names, json, "password");
		String kind = getString(names, json, "kind");
		String name = getString(names, json, "name");
		String description = getString(names, json, "description");
		String location = getString(names, json, "location");
		List<String> tags = getListString(names, json, "tags");
//		Map<String, Object> additional ;
		Map<String, Object> properties ;
		
		//dbManagement.getAgent(login,password,kind);
		
//		Incidence incidence = new Incidence();
//		incidence
//				.setName(name)
//				.setDescription(description).
		
		return new Incidence();
	}
	
	public String getString(List<String> names, JSONObject json, String key) {
		if(!names.contains(key))
			return null;
		return json.getString(key);
	}
	
	public List<String> getListString(List<String> names, JSONObject json, String key) {
		if(!names.contains(key))
			return null;
		
		JSONArray jsonArray = json.getJSONArray(key);
		List<String> strings = new LinkedList<>();
		
		for (int i = 0; i < jsonArray.length(); i++)
			strings.add(jsonArray.getString(i));
		
		return strings;
	}
	
}
