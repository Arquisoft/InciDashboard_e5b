package uo.asw.inciDashboard.currentIncidences;

import java.util.ArrayList;
import java.util.List;
//
//import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Agent;
import uo.asw.dbManagement.model.Incidence;

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
		Agent a = new Agent("XXX","1234","12");
		Incidence i = new Incidence(1234, "XXX", a, null, "Fuego", "Coche ardiendo", "12345,1234", null, null, "Bad", "Mucho humo", "12/02/2018", true);
		Incidence i2 = new Incidence(1234, "ZZZ", a, null, "Inundacion", "Calle inundada lluvia", "12345,1234", null, null, "Bad", "Mucha agua", "12/02/2018", true);
		Incidence i3 = new Incidence(1234, "YYY", a, null, "Accidente", "Colision ente dos coches", "12345,1234", null, null, "Bad", "Ya esta solucionado", "12/02/2018", true);
		
		listaincidencias.add(i);
		listaincidencias.add(i2);
		listaincidencias.add(i3);
		return listaincidencias;
	}
	
	
	
	

}
