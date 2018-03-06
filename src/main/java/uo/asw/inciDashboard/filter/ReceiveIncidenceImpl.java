package uo.asw.inciDashboard.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import uo.asw.dbManagement.model.Incidence;
import uo.asw.inciDashboard.currentIncidences.ReceiveFilteredIncidence;
import uo.asw.inciDashboard.filter.services.RIncidenceP;

@Component
public class ReceiveIncidenceImpl implements ReceiveIncidence {

	@Autowired
	private RIncidenceP rIncidenceP; 
	
	@Autowired
	private ReceiveFilteredIncidence receiveFilteredIncidence;
	
	@Override
	public void receiveIncidence(String jsonStringIncidence) {
		Incidence incidence = rIncidenceP.jsonStringToIncidence(jsonStringIncidence);
		
		//Aplicamos el filtro y comprobamos si pasa o no y si tiene valores peligrosos o no
		
		if(pasaFiltro)
			receiveFilteredIncidence.receiveFilteredIncidence(incidence);
			
	}

}
