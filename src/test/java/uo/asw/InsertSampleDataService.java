package uo.asw;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Agent;
import uo.asw.dbManagement.repositories.AgentsRepository;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private AgentsRepository agentsRepository;

	@PostConstruct
	public void init() {
		Agent agent1 = new Agent("316683136", "1234", "Person");
		agent1.setName("Juan");
		agent1.setEmail("email@email.com");
		
		agentsRepository.save(agent1);
	
	}
	
}