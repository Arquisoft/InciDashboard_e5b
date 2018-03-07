package uo.asw.dbManagement.model;

import java.util.List;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	private List<String> tags;
	private Map<String, Object> additional;
	private Map<String, Object> properties;
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
	public void setAgent(Agent agent) {
		this.agent = agent;
	}
	public Operator getOperator() {
		return operator;
	}
	public void setOperator(Operator operator) {
		this.operator = operator;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public Map<String, Object> getAdditional() {
		return additional;
	}
	public void setAdditional(Map<String, Object> additional) {
		this.additional = additional;
	}
	public Map<String, Object> getProperties() {
		return properties;
	}
	public void setProperties(Map<String, Object> properties) {
		this.properties = properties;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOperatorComments() {
		return operatorComments;
	}
	public void setOperatorComments(String operatorComments) {
		this.operatorComments = operatorComments;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	public boolean isDangerous() {
		return dangerous;
	}
	public void setDangerous(boolean dangerous) {
		this.dangerous = dangerous;
	}
	
}
