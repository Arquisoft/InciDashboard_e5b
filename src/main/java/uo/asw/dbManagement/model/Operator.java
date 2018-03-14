package uo.asw.dbManagement.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

@Entity
public class Operator {
	
	@Id
	@GeneratedValue
	private long id; 
	@Column(unique=true) 
	private String identifier;
	private String name;  
	private String role = "ROLE_OPERATOR";
	
	private String password;
	@Transient
	private String passwordConfirm; //TODO - quitar?
		
	@OneToMany(mappedBy="operator")
	private Set<Incidence> incidences;

	public Operator() {}
	
	public Operator(String identifier,String name) {
		this.identifier=identifier;
		this.name=name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public Set<Incidence> getIncidences() {
		return incidences;
	}

	public void setIncidences(Set<Incidence> incidences) {
		this.incidences = incidences;
	}
	
}
