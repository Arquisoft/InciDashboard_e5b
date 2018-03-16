package uo.asw.inciDashboard.filter;

import org.springframework.stereotype.Service;

import uo.asw.dbManagement.model.Filter;
import uo.asw.inciDashboard.filter.properties.ApplyOn;
import uo.asw.inciDashboard.filter.properties.FilterOperation;
import uo.asw.inciDashboard.filter.properties.FilterResponse;
import uo.asw.inciDashboard.filter.properties.PropertyType;
import uo.asw.util.exception.BusinessException;

@Service
public class FilterService {
	
	/**
	 * Crea un filtro dados los valores del controlador.
	 * Transforma los strings en enumerados, y guarda tag y propiedad
	 */
	public Filter createFilter(String filterResponse, String applyOn, 
			String propertyType, String filterOperation,
			String tag, String propertyName, String propertyValue) {
		
		Filter filter = new Filter();
		
//		if(filterResponse.)
//		"tag" "string" "contains"
//		
		try {
			filter.setFilterResponse(getFilterResponseAsEnum(filterResponse)).
			setApplyOn(getApplyOnAsEnum(applyOn)).
			setPropertyType(getPropertyTypeAsEnum(propertyType)).
			setFilterOperation(getFilterOperationAsEnum(filterOperation));			
		}catch (Exception e) {
			System.err.println(e);
			return new Filter();
		}
		
		filter.setTag(tag).
			setPropertyName(propertyName).
			setPropertyValue(propertyValue);
		
		return filter;
	}

	public FilterResponse getFilterResponseAsEnum(String filterResponse) throws BusinessException {
		if(filterResponse.equals("acceptAll")) return FilterResponse.ACCEPT_ALL;
		else if(filterResponse.equals("accept")) return FilterResponse.ACCEPT;
		else if(filterResponse.equals("markAsDangerous")) return FilterResponse.MARK_AS_DANGEROUS;
		else throw new BusinessException("Wrong Filter Response param: " + filterResponse);
	}
	
	public ApplyOn getApplyOnAsEnum(String applyOn) throws BusinessException {
		if(applyOn.equals("tag")) return ApplyOn.TAG;
		else if(applyOn.equals("property")) return ApplyOn.PROPERTY;
		else throw new BusinessException("Wrong Apply On param: " + applyOn);
	}
	
	public PropertyType getPropertyTypeAsEnum(String propertyType) throws BusinessException {
		if(propertyType.equals("string")) return PropertyType.STRING;
		else if(propertyType.equals("double")) return PropertyType.DOUBLE;
		else if(propertyType.equals("boolean")) return PropertyType.BOOLEAN;
		else throw new BusinessException("Wrong Property Type param: " + propertyType);
	}

	public FilterOperation getFilterOperationAsEnum(String filterOperation) throws BusinessException {
		if(filterOperation.equals("greater")) return FilterOperation.GREATER;
		else if(filterOperation.equals("less")) return FilterOperation.LESS;
		else if(filterOperation.equals("equals")) return FilterOperation.EQUALS;
		else if(filterOperation.equals("notEquals")) return FilterOperation.NOT_EQUALS;
		else if(filterOperation.equals("contains")) return FilterOperation.CONTAINS;
		else if(filterOperation.equals("notContains")) return FilterOperation.NOT_CONTAINS;
		else throw new BusinessException("Wrong Filter Operation param: " + filterOperation);
	}
	
}
