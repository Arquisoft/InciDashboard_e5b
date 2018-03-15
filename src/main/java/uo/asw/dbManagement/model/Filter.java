package uo.asw.dbManagement.model;

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
	private PropertyType propertyType = PropertyType.INTEGER; //only if apply on == PROPERTY, por defecto ints
	
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
	private Object propertyValue;
	
	
	public Filter() {}


//	public Incidence applyFilter(Incidence incidence) {
//		
//	}
	
	public long getId() {
		return id;
	}

	public FilterResponse getFilterResponse() {
		return filterResponse;
	}


	public void setFilterResponse(FilterResponse filterResponse) {
		this.filterResponse = filterResponse;
	}


	public ApplyOn getApplyOn() {
		return applyOn;
	}


	public void setApplyOn(ApplyOn applyOn) {
		this.applyOn = applyOn;
	}


	public PropertyType getPropertyType() {
		return propertyType;
	}


	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}


	public FilterOperation getFilterOperation() {
		return filterOperation;
	}


	public void setFilterOperation(FilterOperation filterOperation) {
		this.filterOperation = filterOperation;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public String getPropertyName() {
		return propertyName;
	}


	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}


	public Object getPropertyValue() {
		return propertyValue;
	}


	public void setPropertyValue(Object propertyValue) {
		this.propertyValue = propertyValue;
	}

}
