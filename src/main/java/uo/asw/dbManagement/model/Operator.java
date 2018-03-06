package uo.asw.dbManagement.model;

import java.util.Set; //A collection that contains no duplicate elements

import javax.persistence.CascadeType;
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
	private String role;
	
	private String password;
	@Transient // propiedad que no se almacena en la tabla.
	private String passwordConfirm; // Este campo solo sirve para pasar el valor del campo "Repita Password"
								   // del formulario y validar que tiene el mismo valor que el password
		
	@OneToMany(mappedBy="operator", cascade=CascadeType.ALL)
	private Set<Incidence> incidences;

	public Operator() {}

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
