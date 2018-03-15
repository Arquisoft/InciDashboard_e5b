package uo.asw.inciDashboard.currentIncidences;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CurrentIncidencesController implements GetCurrentIncidences {

	/* TODO
	 * Ofrece una monitorización continua de la evolución de los valores de las propiedades más representativas de los sensores, 
	 * así como de las incidencias que estén siendo generadas por las personas o entidades. 
	 * También se ofrecerá la posibilidad de visualizar las incidencias geolocalizadas en un mapa, así como los valores actuales y los estados. 
	 */
	
	@Override
	@RequestMapping("/incidences/currentIncidences")
	public String getCurrentIncidences() {
		// TODO - implementar??
		return "incidences/currentIncidences";
	}

}
