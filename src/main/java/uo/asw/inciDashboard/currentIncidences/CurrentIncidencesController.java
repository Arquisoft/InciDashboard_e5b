package uo.asw.inciDashboard.currentIncidences;

import java.util.List;

import org.hsqldb.auth.AuthBeanMultiplexer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.repositories.IncidencesRepository;
import uo.asw.dbManagement.services.OperatorsService;

@Controller
public class CurrentIncidencesController implements GetCurrentIncidences {

	/* TODO
	 * Ofrece una monitorización continua de la evolución de los valores de las propiedades más representativas de los sensores, 
	 * así como de las incidencias que estén siendo generadas por las personas o entidades. 
	 * También se ofrecerá la posibilidad de visualizar las incidencias geolocalizadas en un mapa, así como los valores actuales y los estados. 
	 */
	
	@Autowired
	public OperatorsService operatorsService;
	
	@Override
	@RequestMapping("/incidences/currentIncidences")
	public String getCurrentIncidences() {
		// TODO - implementar??
		return "incidences/currentIncidences";
	}
	

}
