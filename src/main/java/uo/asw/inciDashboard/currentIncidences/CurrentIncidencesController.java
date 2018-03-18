
package uo.asw.inciDashboard.currentIncidences;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import uo.asw.dbManagement.model.Incidence;

@Controller
public class CurrentIncidencesController implements GetCurrentIncidences {
	

	 @Autowired
	    private QueryInfo queryInfo;

	/* TODO
	 * Ofrece una monitorización continua de la evolución de los valores de las propiedades más representativas de los sensores, 
	 * así como de las incidencias que estén siendo generadas por las personas o entidades. 
	 * También se ofrecerá la posibilidad de visualizar las incidencias geolocalizadas en un mapa, así como los valores actuales y los estados. 
	 */
	
	@Override
	@RequestMapping("/incidences")
	public String getCurrentIncidences() {
		
        return "currentIncidences";
	}

	
	@RequestMapping("/incidences")
	public String vistaIncidences(Model model, HttpSession session) {
		Incidence incidence = (Incidence) session.getAttribute("incidence");
    	if (incidence == null || incidence.getAgent() == null )
    		return "redirect:/";
    	
    	DashboardData data = queryInfo.queryInfo();
    	
    	model.addAttribute("sugerencias", data.getIncidencias());
        return "vistaIncidencias";
	}
	
}

