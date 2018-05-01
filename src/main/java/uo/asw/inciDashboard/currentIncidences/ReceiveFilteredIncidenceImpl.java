package uo.asw.inciDashboard.currentIncidences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Incidence;

@Service
public class ReceiveFilteredIncidenceImpl implements ReceiveFilteredIncidence {
	
	@Autowired
    private SimpMessagingTemplate template;

	@Override
	public void receiveFilteredIncidence(Incidence incidence) {
		this.template.convertAndSend("/topic/incidences", incidence);
	}
	
}
