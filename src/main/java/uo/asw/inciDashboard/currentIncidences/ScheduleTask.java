package uo.asw.inciDashboard.currentIncidences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Agent;
import uo.asw.dbManagement.model.Incidence;

import java.util.Date;

@Service
public class ScheduleTask {

    @Autowired
    private SimpMessagingTemplate template;
    
    @Autowired
    private ReceiveFilteredIncidenceImpl receiveFilteredIncidenceImpl;

    // this will send a message to an endpoint on which a client can subscribe
    @Scheduled(fixedRate = 5000)
    public void trigger() {
        this.template.convertAndSend("/topic/incidences", new Date());
    }
    
    @Scheduled(fixedRate = 5000)
    public void pasarIncidencias() {
    	Agent a = new Agent("XXX","1234","12");
    	Incidence i = new Incidence(1234, "XXX", a, null, "Fuego", "Coche ardiendo", "43.35,-5.85", null, null, "Bad", "Mucho humo", "12/02/2018", true);
    	receiveFilteredIncidenceImpl.receiveFilteredIncidence(i);
    }

}
