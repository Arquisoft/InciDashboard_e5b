package uo.asw.dbManagement.model;

import javax.persistence.*;
import java.util.Set; //A collection that contains no duplicate elements

@Entity
public class Operator {// XXX Cambiar cosas!!!
	
	@Id
	@GeneratedValue
	private long id; 
	@Column(unique=true) 
	private String dni; 
	private String name; 
	private String lastName; 
	private String role;
	
	private String password;
	@Transient // propiedad que no se almacena en la tabla.
	private String passwordConfirm; // Este campo solo sirve para pasar el valor del campo "Repita Password"
								   // del formulario y validar que tiene el mismo valor que el password

	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private Set<Incidence> incidences;

	public Operator(String dni, String name, String lastName) {
		super();
		this.dni = dni;
		this.name = name;
		this.lastName = lastName;
	}

	public Operator() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public Set<Incidence> getIncidences() {
		return incidences;
	}

	public void setIncidences(Set<Incidence> incidences) {
		this.incidences = incidences;
	}

	public String getFullName() {
		return this.name + " " + this.lastName;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
