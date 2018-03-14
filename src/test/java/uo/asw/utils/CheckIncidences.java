package uo.asw.utils;

import uo.asw.dbManagement.model.Incidence;

public class CheckIncidences {

	public static boolean incidencesAreEquals(Incidence i1, Incidence i2) {
		
		i1.getAgent().getIdentifier() == i2.getAgent().getIdentifier();
		// TODO - comprobar todos los campos
		
		return true;
	}
	
}
