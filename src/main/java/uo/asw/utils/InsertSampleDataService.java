package uo.asw.utils;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Agent;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.model.Operator;
import uo.asw.dbManagement.repositories.AgentsRepository;
import uo.asw.dbManagement.repositories.IncidencesRepository;
import uo.asw.dbManagement.services.OperatorsService;
import uo.asw.utils.UuidGenerator;

@Service
public class InsertSampleDataService {
	
		@Autowired
		private AgentsRepository agentsRepository;
	
		@Autowired
		private OperatorsService operatorsService;
		
		@Autowired
		private IncidencesRepository incidencesRepository;
	
		@SuppressWarnings("serial")
		@PostConstruct
		public void init() {
			// Creamos agentes
			Agent agent1 = new Agent("316683136", "1234", "Person");
			agent1.setName("Juan");
			agent1.setEmail("email@email.com");
			
			agentsRepository.save(agent1);
		
			
			// Creamos operarios e incidencias
			Operator operator1 = new Operator("99999999A", "NombreOperador1");
			operator1.setPassword("123456");
			//operator1.setRole(rolesService.getRoles()[1]); //TODO - cambiar??
			operator1.setRole("ROLE_OPERATOR");
			
			Set<Incidence> operator1Incidences = new HashSet<Incidence>() {
				{
					Incidence i1 = new Incidence(UuidGenerator.getUuid());
					Incidence i2 = new Incidence(UuidGenerator.getUuid());
					
					i1.setAgent(agent1).setOperator(operator1).setName("NombreInc1").setDescription("DescripcionInc1");
					i2.setAgent(agent1).setOperator(operator1).setName("NombreInc2").setDescription("DescripcionInc2");
					
					add(i1);
					add(i2);
				}
			};
			
			operator1.setIncidences(operator1Incidences);
			
			
			operatorsService.addOperator(operator1);
			incidencesRepository.save(operator1Incidences);
		}
}
