package uo.asw.inciDashboard.currentIncidences;

import java.util.ArrayList;
import java.util.List;
//
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Agent;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.model.Operator;

@Service
public class ReceiveFilteredIncidenceImpl implements ReceiveFilteredIncidence {
	
	List<Incidence> listaincidencias = new ArrayList<Incidence>();
	
	/* TODO
	 * El método ReceiveFilteredIncidence se encarga de recibir un objeto Incidence del componente Filter.
	 * La información de dicho objeto es la que será mostrada por la interfaz web de forma dinámica. 
	 * Si la incidencia está marcada como peligrosa, se deberá dar un aviso por la interfaz para que los operarios se percaten de ello.
	 *  
	 */
	@Override
	public void receiveFilteredIncidence(Incidence incidence) {
		listaincidencias.add(incidence);
	}
	
	
	public List<Incidence> getListaincidencias() {
		return listaincidencias;
	}
	

}
