package uo.asw.dbManagement.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import uo.asw.inciDashboard.filter.properties.ApplyOn;
import uo.asw.inciDashboard.filter.properties.FilterOperation;
import uo.asw.inciDashboard.filter.properties.FilterResponse;
import uo.asw.inciDashboard.filter.properties.PropertyType;

@Entity
public class Filter {

	@Id
	@GeneratedValue
	private long id;
	
	/**
	 * La respuesta que va a tener el filtro con cada incidencia:
	 * -ACCEPT_ALL --> Acepta todas las incidencias (no filtra nada)
	 * -ACCEPT --> Si se cumple la operación, deja pasar la incidencia, si no no
	 * -MARK_AS_DANGEROUS --> Si se cumple la operación, se marca la incidencia como peligrosa
	 */
	@Enumerated(EnumType.STRING)
	private FilterResponse filterResponse = FilterResponse.ACCEPT_ALL; // por defecto acepta todas
	
	/**
	 * Decide si la operacion se va a aplicar sobre tags o properties de la incidencia
	 */
	@Enumerated(EnumType.STRING)
	private ApplyOn applyOn = ApplyOn.TAG; // por defecto se aplica sobre tags
	
	/**
	 * En caso de que se aplique sobre propiedades de las incidencias,
	 * indica el tipo Java que va a tener el String contenido en el "value" de la propiedad
	 */
	@Enumerated(EnumType.STRING)
	private PropertyType propertyType = PropertyType.DOUBLE; //only if apply on == PROPERTY, por defecto double
	
	/**
	 * Tipo de operación a realizar: GREATER, LESS, EQUALS, NOT_EQUALS, CONTAINS, NOT_CONTAINS
	 * Si la operación se cumple y FilterResponse==ACCEPT, se deja pasar la incidencia
	 * Si la operación se cumple y FilterResponse==ACCEPT, se marca como peligrosa la incidencia
	 */
	@Enumerated(EnumType.STRING)
	private FilterOperation filterOperation = FilterOperation.CONTAINS;// por defecto si se cambiase FilterResponse a ACCEPT,
																	 // solo se aceptarian las incidencias que contengan el tag indicado
	
	/**
	 * Guarda el valor del tag con el que se va a comparar
	 */
	private String tag;
	
	/**
	 * Guarda el nombre de la propiedad con la que se va a comparar
	 */
	private String propertyName;
	
	/**
	 * Guarda el valor de la propiedad con la que se va a comparar
	 */
	private String propertyValue;
	
	
	public Filter() {}

	public Incidence applyFilter(Incidence incidence) {
		if(filterResponse.equals(FilterResponse.ACCEPT))
			return acceptResponse(incidence);
		
		else if(filterResponse.equals(FilterResponse.MARK_AS_DANGEROUS))
			return markAsDangerousResponse(incidence);
		
		else // ACCEPT_ALL
			return incidence;
	}
	
	/**
	 * Si cumple la operacion, se marca como peligrosa y se retorna,
	 * si no, se retorna sin mas
	 */
	private Incidence markAsDangerousResponse(Incidence incidence) {
		if(satisfiesOperation(incidence)) {
			incidence.setDangerous(true);
			return incidence;
		}
		else return incidence;
	}

	/**
	 * Si cumple la operacion, se retorna la incidencia
	 * si no, se retorna null
	 */
	private Incidence acceptResponse(Incidence incidence) {
		if(satisfiesOperation(incidence)) {
			return incidence;
		}
		else return null;
	}

	private boolean satisfiesOperation(Incidence incidence) {
		if(applyOn.equals(ApplyOn.TAG))
			return satisfiesTagOperation(incidence);
		else // Property
			return satisfiesPropertyOperation(incidence);
	}

	private boolean satisfiesTagOperation(Incidence incidence) {
		if(filterOperation.equals(FilterOperation.CONTAINS))
			return incidence.getTags().contains(tag);
		
		else // NOT_CONTAINS 
			return !incidence.getTags().contains(tag); 
	}

	private boolean satisfiesPropertyOperation(Incidence incidence) {
		String incidencePropertyValue = getPropertyValueByName(incidence, propertyName);
		if(incidencePropertyValue == null) return false;
		
		if(filterOperation.equals(FilterOperation.GREATER))
			return satisfiesPropertyGreaterOperation(incidence);

		else if(filterOperation.equals(FilterOperation.LESS))
			return satisfiesPropertyLessOperation(incidence);
		
		else if(filterOperation.equals(FilterOperation.EQUALS))
			return satisfiesPropertyEqualsOperation(incidence);
		
		else //NOT_EQUALS
			return !satisfiesPropertyEqualsOperation(incidence);
	}
	
	private boolean satisfiesPropertyGreaterOperation(Incidence incidence) {
		String incidencePropertyValue = getPropertyValueByName(incidence, propertyName);
		
		if(Double.parseDouble(incidencePropertyValue) > Double.parseDouble(propertyValue))
			return true;
		else return false;
	}
	
	private boolean satisfiesPropertyLessOperation(Incidence incidence) {
		String incidencePropertyValue = getPropertyValueByName(incidence, propertyName);
		
		if(Double.parseDouble(incidencePropertyValue) < Double.parseDouble(propertyValue))
			return true;
		else return false;
	}
	
	private boolean satisfiesPropertyEqualsOperation(Incidence incidence) {
		if(propertyType.equals(PropertyType.STRING))
			return satisfiesPropertyEqualsStringOperation(incidence);
		
		else // BOOLEAN
			return satisfiesPropertyEqualsBooleanOperation(incidence);
	}
	
	private boolean satisfiesPropertyEqualsStringOperation(Incidence incidence) {
		String incidencePropertyValue = getPropertyValueByName(incidence, propertyName);
		
		if(incidencePropertyValue.equalsIgnoreCase(propertyValue))
			return true;
		else return false;
	}

	private boolean satisfiesPropertyEqualsBooleanOperation(Incidence incidence) {
		String incidencePropertyValue = getPropertyValueByName(incidence, propertyName);
		
		if(Boolean.parseBoolean(incidencePropertyValue) == Boolean.parseBoolean(propertyValue))
			return true;
		else return false;
	}
	
	private String getPropertyValueByName(Incidence incidence, String propertyName) {
		Set<Property> properties = incidence.getProperties();
		for (Property property : properties) {
			if(property.getName().equals(propertyName))
				return property.getValue();
		}
		return null;
	}

	public long getId() {
		return id;
	}
	
	public Filter setId(long id) {
		this.id = id;
		return this;
	}

	public FilterResponse getFilterResponse() {
		return filterResponse;
	}


	public Filter setFilterResponse(FilterResponse filterResponse) {
		this.filterResponse = filterResponse;
		return this;
	}


	public ApplyOn getApplyOn() {
		return applyOn;
	}


	public Filter setApplyOn(ApplyOn applyOn) {
		this.applyOn = applyOn;
		return this;
	}


	public PropertyType getPropertyType() {
		return propertyType;
	}


	public Filter setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
		return this;
	}


	public FilterOperation getFilterOperation() {
		return filterOperation;
	}


	public Filter setFilterOperation(FilterOperation filterOperation) {
		this.filterOperation = filterOperation;
		return this;
	}


	public String getTag() {
		return tag;
	}


	public Filter setTag(String tag) {
		this.tag = tag;
		return this;
	}


	public String getPropertyName() {
		return propertyName;
	}


	public Filter setPropertyName(String propertyName) {
		this.propertyName = propertyName;
		return this;
	}


	public String getPropertyValue() {
		return propertyValue;
	}


	public Filter setPropertyValue(String propertyValue) {
		this.propertyValue = propertyValue;
		return this;
	}

	public void setValues(Filter filter) {
		this.filterResponse = filter.filterResponse;
		this.applyOn = filter.applyOn;
		this.propertyType = filter.propertyType;
		this.filterOperation = filter.filterOperation;
		this.tag = filter.tag;
		this.propertyName = filter.propertyName;
		this.propertyValue = filter.propertyValue;
	}
	
}
