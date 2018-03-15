package uo.asw.inciDashboard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uo.asw.dbManagement.model.Operator;
import uo.asw.dbManagement.services.OperatorsService;
import uo.asw.dbManagement.services.RolesService;
import uo.asw.dbManagement.services.SecurityService;
import uo.asw.validators.LoginFormValidator;
import uo.asw.validators.SignUpFormValidator;

@Controller
public class OperatorsController {
	
	@Autowired
	private OperatorsService usersService;
	
	@Autowired
	private SecurityService securityService;
	
	@Autowired
	private SignUpFormValidator signUpFormValidator;
	
	@Autowired
	private LoginFormValidator loginFormValidator;
	
	@Autowired
	private RolesService rolesService;
	
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup(Model model) {
		model.addAttribute("operator", new Operator());
		return "signup";
	}
	
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String setUser(@Validated Operator operator, BindingResult result, Model model) {
		signUpFormValidator.validate(operator, result);
		if (result.hasErrors()) {
			return "signup";
		}
		
		operator.setRole(rolesService.getRoles()[0]);
		usersService.addOperator(operator);
		securityService.autoLogin(operator.getIdentifier(), operator.getPasswordConfirm());
		return "redirect:home";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("operator", new Operator());
		return "login";
	}
	
	@RequestMapping(value = "/goToHomeAfterLogin", method = RequestMethod.POST)
	public String login(@Validated Operator operator, BindingResult result, Model model) {
		loginFormValidator.validate(operator, result);
		if (result.hasErrors()) {
			return "login";
		}
		securityService.autoLogin(operator.getIdentifier(), operator.getPassword());
		return "redirect:home";
	}
	
	@RequestMapping(value = "/goToHomeAfterLogin", method = RequestMethod.GET)
	public String goToHomeAfterLogin(Model model) {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth.isAuthenticated()) {
			return "redirect:home";
		}
		else {
			return "redirect:login";
		}
	}

}
