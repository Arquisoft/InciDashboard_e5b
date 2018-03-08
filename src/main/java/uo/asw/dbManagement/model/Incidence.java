package uo.asw.dbManagement.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Incidence {
	
	/*	TODO - Quitar
	 *	Cada incidencia puede contener los siguientes campos: 
	 *	nombre de usuario y password, 
	 *	nombre de la incidencia, descripción, localización (se obtendrá automáticamente del dispositivo si es posible), 
	 *	etiquetas (lista de palabras separadas por comas que permitirán categorizar las incidencias), 
	 *	información adicional (fotos, vídeos, etc.). 
	 *	Algunas incidencias podrán también contener una lista de campos con la forma "propiedad/valor", 
	 *	donde el campo propiedad indica un nombre de propiedad, y el campo valor, indica el valor de dicha propiedad.
	 *
	 *	Las incidencias adquirirán un estado (abierta, en proceso, cerrada, anulada) 
	 *	así como otra información generada por el sistema (persona/entidad asignada para resolver la incidencia), 
	 *	comentarios sobre la incidencia realizados por los operarios, etc. 
	 *	Las incidencias también pueden tener una caducidad, por ejemplo, en el caso de las incidencias 
	 *	que pueda enviar un sensor de temperatura, si se envían cada hora, tendrán una hora de caducidad).
	 *
	 * 	Si hay valores de alguna propiedad que los filtros consideran peligroso, se marcará dicha incidencia como peligrosa.
	 */
	
	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JoinColumn(name="agent_id")
	private Agent agent;
	
	@ManyToOne
	@JoinColumn(name="operator_id")
	private Operator operator;
	
	private String name;
	private String description;
	private String location;
	private String[] tags;
	//private Map<String, Object> additional;
	@OneToMany(mappedBy="incidence")
	private Set<Property> properties;
	
	private String status;
	private String operatorComments;
	private String expiration;
	private boolean dangerous;
	
	public Incidence() {}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Agent getAgent() {
		return agent;
	}
	public Incidence setAgent(Agent agent) {
		this.agent = agent;
		return this;
	}
	public Operator getOperator() {
		return operator;
	}
	public Incidence setOperator(Operator operator) {
		this.operator = operator;
		return this;
	}
	public String getName() {
		return name;
	}
	public Incidence setName(String name) {
		this.name = name;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public Incidence setDescription(String description) {
		this.description = description;
		return this;
	}
	public String getLocation() {
		return location;
	}
	public Incidence setLocation(String location) {
		this.location = location;
		return this;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}
	
	public String getStatus() {
		return status;
	}
	public Incidence setStatus(String status) {
		this.status = status;
		return this;
	}
	public String getOperatorComments() {
		return operatorComments;
	}
	public Incidence setOperatorComments(String operatorComments) {
		this.operatorComments = operatorComments;
		return this;
	}
	public String getExpiration() {
		return expiration;
	}
	public Incidence setExpiration(String expiration) {
		this.expiration = expiration;
		return this;
	}
	public boolean isDangerous() {
		return dangerous;
	}
	public Incidence setDangerous(boolean dangerous) {
		this.dangerous = dangerous;
		return this;
	}

	public Set<Property> getProperties() {
		return properties;
	}

	public void setProperties(Set<Property> properties) {
		this.properties = properties;
	}

}
