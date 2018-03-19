package uo.asw.inciDashboard.storedIncidences;

import java.security.Principal;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import uo.asw.dbManagement.model.Incidence;

public interface UpdateIncidence {

	public String updateIncidenceGet(Model model, @PathVariable Long idIncidence);
	
	public String saveIncidenceGet(Model model, Principal principal, @PathVariable Long idIncidence, @PathVariable String statusIncidence);
	
}
