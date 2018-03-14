package uo.asw.dbManagement.services;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Operator;

@Service
public class InsertSampleDataService {
	
	@Autowired
	private OperatorsService operatorsService;
	@PostConstruct
	public void init() {
		
		Operator user1 = new Operator("AAAAAAA1", "Adrian");
		user1.setPassword("123456");
		Operator user2 = new Operator("AAAAAAA2", "Juan");
		user2.setPassword("123456");
		Operator user3 = new Operator("AAAAAAA3", "Francisco");
		user3.setPassword("123456");
		Operator user4 = new Operator("AAAAAAA4", "Rodrigo");
		user4.setPassword("123456");
		Operator user5 = new Operator("AAAAAAA5", "Pepe");
		user5.setPassword("123456");
		Operator user6 = new Operator("AAAAAAA6", "Alberto");
		user6.setPassword("123456");
	
		operatorsService.addOperator(user1);
		operatorsService.addOperator(user2);
		operatorsService.addOperator(user3);
		operatorsService.addOperator(user4);
		operatorsService.addOperator(user5);
		operatorsService.addOperator(user6);
	}

}
