package uo.asw.inciDashboard.currentIncidences;

import org.springframework.stereotype.Component;

import uo.asw.dbManagement.model.Incidence;

@Component
public class ReceiveFilteredIncidenceImpl implements ReceiveFilteredIncidence {

	@Override
	public void receiveFilteredIncidence(Incidence incidence) {
		// TODO IMPLEMENTAR
		
		/*
		 * El método ReceiveFilteredIncidence se encarga de de recibir un objeto Incidence del componente Filter.
		 * La información de dicho objeto es la que será mostrada por la interfaz web de forma dinámica. 
		 * Si la incidencia está marcada como peligrosa, se deberá dar un aviso por la interfaz para que los operarios se percaten de ello.
		 *  
		 */
		
	}

}
