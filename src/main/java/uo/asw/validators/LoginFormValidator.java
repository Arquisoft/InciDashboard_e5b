package uo.asw.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.*;

import uo.asw.dbManagement.model.Operator;
import uo.asw.dbManagement.services.OperatorsService;

@Component
public class LoginFormValidator implements Validator{
	
	@Autowired
	private OperatorsService operatorsService;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return Operator.class.equals(aClass);
	}
	
	@Override
	public void validate(Object target, Errors errors) {
		Operator operator = (Operator) target;
		
		Operator operatorEnBD=operatorsService.getUserByIdentifier(operator.getIdentifier());
		
		if (operatorEnBD != null) {//si existe
			
			boolean sonPasswordsIguales=operatorsService.passwordsIguales(operator.getPassword(), operatorEnBD.getPassword());
			
			if(!sonPasswordsIguales) {//si no tienen la misma contraseña
				
				errors.rejectValue("password", "Error.login.password.coincidence");
			}
		}
		else {
			errors.rejectValue("identifier", "Error.login.identifier.notExist");
		}
	}
}
