package uo.asw.inciDashboard.currentIncidences;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CurrentIncidencesController implements GetCurrentIncidences {

	@Override
	@RequestMapping("/operator/currentIncidences")
	public String getCurrentIncidences() {
		// TODO - implementar??
		return "operator/currentIncidences";
	}

}
