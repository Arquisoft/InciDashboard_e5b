package uo.asw.tests;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import uo.asw.InciDashboardE5bApplication;
import uo.asw.dbManagement.DBManagementFacade;
import uo.asw.dbManagement.model.Filter;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.inciDashboard.filter.RIncidenceP;
import uo.asw.inciDashboard.filter.properties.ApplyOn;
import uo.asw.inciDashboard.filter.properties.FilterOperation;
import uo.asw.inciDashboard.filter.properties.FilterResponse;
import uo.asw.inciDashboard.filter.properties.PropertyType;
import uo.asw.util.exception.BusinessException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InciDashboardE5bApplication.class)
@WebAppConfiguration
/**
 * Prueba el componente Filter
 */
public class FilterTest {

	@Autowired
	private RIncidenceP rIncidenceP;
	
	@Autowired
	private DBManagementFacade dbManagement;
	
	@BeforeClass
	public static void beforeClass() {
	}
	
    @Before
    public void setUp() {
    	
    }
    
    String jsonBasicIncidence = "{"
			+ "\"identifier\": \"uuid\","
			+ "\"login\": \"316683136\","
			+ "\"password\": \"1234\","
			+ "\"kind\": \"Person\","
			+ "\"name\": \"Incidencia\","
			+ "\"description\": \"Descripcion\""
		+ "}";

    String jsonIncidenceWithTagFuego = "{"
    		+ "\"identifier\": \"uuid\","
    		+ "\"login\": \"316683136\","
    		+ "\"password\": \"1234\","
    		+ "\"kind\": \"Person\","
    		+ "\"name\": \"Incidencia\","
    		+ "\"description\": \"Descripcion\","
    		+ "\"tags\": [\"fuego\",\"calor\"]"
    		+ "}";
    
    String jsonIncidenceWithOutTagFuego = "{"
    		+ "\"identifier\": \"uuid\","
    		+ "\"login\": \"316683136\","
    		+ "\"password\": \"1234\","
    		+ "\"kind\": \"Person\","
    		+ "\"name\": \"Incidencia\","
    		+ "\"description\": \"Descripcion\","
    		+ "\"tags\": [\"calor\"]"
    		+ "}";
    
    String jsonIncidenceWithProperties1 = "{"
    		+ "\"identifier\": \"uuid\","
    		+ "\"login\": \"316683136\","
    		+ "\"password\": \"1234\","
    		+ "\"kind\": \"Person\","
    		+ "\"name\": \"Incidencia\","
    		+ "\"description\": \"Descripcion\","
    		+ "\"properties\": ["
			+ "{\"temperatura\": \"10\"},"
			+ "{\"peligro\": \"true\"},"
			+ "{\"aire\": \"mucho\"}"
			+ "]"
    		+ "}";
        	
    String jsonIncidenceWithProperties2 = "{"
    		+ "\"identifier\": \"uuid\","
    		+ "\"login\": \"316683136\","
    		+ "\"password\": \"1234\","
    		+ "\"kind\": \"Person\","
    		+ "\"name\": \"Incidencia\","
    		+ "\"description\": \"Descripcion\","
    		+ "\"properties\": ["
			+ "{\"temperatura\": \"20\"},"
			+ "{\"peligro\": \"false\"},"
			+ "{\"aire\": \"poco\"}"
			+ "]"
    		+ "}";
    
    String jsonIncidenceWithOnlyOneProperty = "{"
    		+ "\"identifier\": \"uuid\","
    		+ "\"login\": \"316683136\","
    		+ "\"password\": \"1234\","
    		+ "\"kind\": \"Person\","
    		+ "\"name\": \"Incidencia\","
    		+ "\"description\": \"Descripcion\","
    		+ "\"properties\": ["
			+ "{\"prop\": \"val\"}"
			+ "]"
    		+ "}";
    
    String tag = "fuego";
    
    /**
     * Para comprobar que la incidencia pasa el filtro (sin marcarla como peligrosa),
     * comprobamos que la incidencia despues de aplicar el filtro no es null 
     * y que todos sus campos coinciden con los de la incidencia antes de pasar el filtro
     */
    public void assertIncidencePassTheFilter(Incidence incidenceBeforeFilter, Incidence incidenceAfterFilter) {
    		assertTrue(incidenceAfterFilter != null && incidenceBeforeFilter.equalFields(incidenceAfterFilter));
    }

    /**
     * Para comprobar que la incidencia NO pasa el filtro, comprobamos que 
     * la incidencia despues de aplicar el filtro es null
     */
    public void assertIncidenceDontPassTheFilter(Incidence incidenceAfterFilter) {
    		assertTrue(incidenceAfterFilter == null);
    }
    
    /**
     * Para comprobar que la incidencia pasa el filtro y es marcada como peligrosa
     * comprobamos que la incidencia despues de aplicar el filtro no es null,
     * que su campo Dangerous es true, y que todos sus campos (salvo Dangerous) 
     * coinciden con los de la incidencia antes de pasar el filtro
     */
    public void assertIncidenceWasMarkedAsDangerousByTheFilter(
    		Incidence incidenceBeforeFilter, Incidence incidenceAfterFilter) {
    	
    		incidenceBeforeFilter.setDangerous(true); // lo ponemos a true para comparar debajo
    	
    		assertTrue(incidenceAfterFilter != null && 
    				incidenceAfterFilter.isDangerous() &&
    				incidenceBeforeFilter.equalFields(incidenceAfterFilter));
    }
    
    @Test
    public void testAssertIncidencePassTheFilter() throws BusinessException {
     	Incidence incidence = rIncidenceP.jsonStringToIncidence(jsonBasicIncidence);
     	assertIncidencePassTheFilter(incidence, incidence);
    }
	
    @Test
    public void testAssertIncidenceWasMarkedAsDangerousByTheFilter() throws BusinessException {
     	Incidence incidence = rIncidenceP.jsonStringToIncidence(jsonBasicIncidence);
     	assertIncidencePassTheFilter(incidence, incidence.setDangerous(true));
    }
    
    /**
     * Establecemos la respuesta ACCEPT_ALL para dejar pasar todas las incidencias
     * 
     * @throws BusinessException
     */
    @Test
    public void testFilterAcceptAll() throws BusinessException {
	   
    		dbManagement.updateFilter(dbManagement.getFilter().setFilterResponse(FilterResponse.ACCEPT_ALL));
    	
	    	Incidence incidence1 = rIncidenceP.jsonStringToIncidence(jsonBasicIncidence);
	    	Incidence incidence2 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithTagFuego);
	    	Incidence incidence3 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithOutTagFuego);
	    	Incidence incidence4 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithProperties1);
	    	Incidence incidence5 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithProperties2);
	    	Incidence incidence6 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithOnlyOneProperty);
	    	
	    	Incidence filteredIncidence1 = dbManagement.getFilter().applyFilter(incidence1);
	    	Incidence filteredIncidence2 = dbManagement.getFilter().applyFilter(incidence2);
	    	Incidence filteredIncidence3 = dbManagement.getFilter().applyFilter(incidence3);
	    	Incidence filteredIncidence4 = dbManagement.getFilter().applyFilter(incidence4);
	    	Incidence filteredIncidence5 = dbManagement.getFilter().applyFilter(incidence5);
	    	Incidence filteredIncidence6 = dbManagement.getFilter().applyFilter(incidence6);
	    	
	    	assertIncidencePassTheFilter(incidence1, filteredIncidence1);
	    	assertIncidencePassTheFilter(incidence2, filteredIncidence2);
	    	assertIncidencePassTheFilter(incidence3, filteredIncidence3);
	    	assertIncidencePassTheFilter(incidence4, filteredIncidence4);
	    	assertIncidencePassTheFilter(incidence5, filteredIncidence5);
	    	assertIncidencePassTheFilter(incidence6, filteredIncidence6);
	    	
    }
    
    /**
     * Comprobamos que se filtran o no (según corresponda) 
     * correctamente las incidencias con los siguientes parámetros del filtro:
     * -FilterResponse = Accept
     * -ApplyOn = Tag
     * -FilterOperation = Contains
     * 
     * @throws BusinessException
     */
    @Test
    public void testFilterAcceptTagContains() throws BusinessException {
    	
	    	Filter filter = dbManagement.getFilter();
	    	
	    	filter.setFilterResponse(FilterResponse.ACCEPT).
	    		setApplyOn(ApplyOn.TAG).
	    		setFilterOperation(FilterOperation.CONTAINS).
	    		setTag(tag);
	    	
	    	dbManagement.updateFilter(filter);
	    	
	    	Incidence incidence1 = rIncidenceP.jsonStringToIncidence(jsonBasicIncidence);
	    	Incidence incidence2 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithTagFuego);
	    	Incidence incidence3 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithOutTagFuego);
	    	Incidence incidence4 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithProperties1);
	    	Incidence incidence5 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithProperties2);
	    	Incidence incidence6 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithOnlyOneProperty);
	    	
	    	Incidence filteredIncidence1 = dbManagement.getFilter().applyFilter(incidence1);
	    	Incidence filteredIncidence2 = dbManagement.getFilter().applyFilter(incidence2);
	    	Incidence filteredIncidence3 = dbManagement.getFilter().applyFilter(incidence3);
	    	Incidence filteredIncidence4 = dbManagement.getFilter().applyFilter(incidence4);
	    	Incidence filteredIncidence5 = dbManagement.getFilter().applyFilter(incidence5);
	    	Incidence filteredIncidence6 = dbManagement.getFilter().applyFilter(incidence6);
	    	
	    	assertIncidenceDontPassTheFilter(filteredIncidence1); 		// no contiene el tag
	    	assertIncidencePassTheFilter(incidence2, filteredIncidence2);	// contiene el tag
	    	assertIncidenceDontPassTheFilter(filteredIncidence3); 		// no contiene el tag
	    	assertIncidenceDontPassTheFilter(filteredIncidence4); 		// no contiene el tag
	    	assertIncidenceDontPassTheFilter(filteredIncidence5); 		// no contiene el tag
	    	assertIncidenceDontPassTheFilter(filteredIncidence6); 		// no contiene el tag
	    	
    }
        
    /**
     * Comprobamos que se filtran o no (según corresponda) 
     * correctamente las incidencias con los siguientes parámetros del filtro:
     * -FilterResponse = Accept
     * -ApplyOn = Tag
     * -FilterOperation = Not Contains
     * 
     * @throws BusinessException
     */
    @Test
    public void testFilterAcceptTagNotContains() throws BusinessException {
    	
	    	Filter filter = dbManagement.getFilter();
	    	
	    	filter.setFilterResponse(FilterResponse.ACCEPT).
		    	setApplyOn(ApplyOn.TAG).
		    	setFilterOperation(FilterOperation.NOT_CONTAINS).
		    	setTag(tag);
	    	
	    	dbManagement.updateFilter(filter);
	    	
	    	Incidence incidence1 = rIncidenceP.jsonStringToIncidence(jsonBasicIncidence);
	    	Incidence incidence2 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithTagFuego);
	    	Incidence incidence3 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithOutTagFuego);
	    	Incidence incidence4 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithProperties1);
	    	Incidence incidence5 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithProperties2);
	    	Incidence incidence6 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithOnlyOneProperty);
	    	
	    	Incidence filteredIncidence1 = dbManagement.getFilter().applyFilter(incidence1);
	    	Incidence filteredIncidence2 = dbManagement.getFilter().applyFilter(incidence2);
	    	Incidence filteredIncidence3 = dbManagement.getFilter().applyFilter(incidence3);
	    	Incidence filteredIncidence4 = dbManagement.getFilter().applyFilter(incidence4);
	    	Incidence filteredIncidence5 = dbManagement.getFilter().applyFilter(incidence5);
	    	Incidence filteredIncidence6 = dbManagement.getFilter().applyFilter(incidence6);
	    	
	    	assertIncidencePassTheFilter(incidence1, filteredIncidence1); 		// no contiene el tag
	    	assertIncidenceDontPassTheFilter(filteredIncidence2);					// contiene el tag
	    	assertIncidencePassTheFilter(incidence3, filteredIncidence3); 		// no contiene el tag
	    	assertIncidencePassTheFilter(incidence4, filteredIncidence4); 		// no contiene el tag
	    	assertIncidencePassTheFilter(incidence5, filteredIncidence5); 		// no contiene el tag
	    	assertIncidencePassTheFilter(incidence6, filteredIncidence6); 		// no contiene el tag
    	
    }
    
    /**
     * Comprobamos que se filtran o no (según corresponda) 
     * correctamente las incidencias con los siguientes parámetros del filtro:
     * -FilterResponse = Accept
     * -ApplyOn = Tag
     * -FilterOperation = Not Contains
     * 
     * @throws BusinessException
     */
    @Test
    public void testFilterAcceptPropertyString() throws BusinessException {
	    	
	    	Filter filter = dbManagement.getFilter();
	    	
	    	filter.setFilterResponse(FilterResponse.ACCEPT).
		    	setApplyOn(ApplyOn.PROPERTY).
		    	setPropertyType(PropertyType.STRING).
		    	setFilterOperation(FilterOperation.EQUALS).
		    	setPropertyName("aire").
		    	setPropertyValue("mucho");
	    	
	    	dbManagement.updateFilter(filter);
	    	
	    	Incidence incidence1 = rIncidenceP.jsonStringToIncidence(jsonBasicIncidence);
	    	Incidence incidence2 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithTagFuego);
	    	Incidence incidence3 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithOutTagFuego);
	    	Incidence incidence4 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithProperties1);
	    	Incidence incidence5 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithProperties2);
	    	Incidence incidence6 = rIncidenceP.jsonStringToIncidence(jsonIncidenceWithOnlyOneProperty);
	    	
	    	Incidence filteredIncidence1 = dbManagement.getFilter().applyFilter(incidence1);
	    	Incidence filteredIncidence2 = dbManagement.getFilter().applyFilter(incidence2);
	    	Incidence filteredIncidence3 = dbManagement.getFilter().applyFilter(incidence3);
	    	Incidence filteredIncidence4 = dbManagement.getFilter().applyFilter(incidence4);
	    	Incidence filteredIncidence5 = dbManagement.getFilter().applyFilter(incidence5);
	    	Incidence filteredIncidence6 = dbManagement.getFilter().applyFilter(incidence6);
	    	
	    	assertIncidenceDontPassTheFilter(filteredIncidence1); 			// no tiene propiedades
	    	assertIncidenceDontPassTheFilter(filteredIncidence2);				// no tiene propiedades
	    	assertIncidenceDontPassTheFilter(filteredIncidence3); 			// no tiene propiedades
	    	assertIncidencePassTheFilter(incidence4, filteredIncidence4); 	// contiene la propiedad "aire" con valor "mucho"
	    	assertIncidenceDontPassTheFilter(filteredIncidence5); 			// contiene la propiedad "aire" con valor "poco"
	    	assertIncidenceDontPassTheFilter(filteredIncidence6); 			// no contiene la propiedad "aire"
	    	
    }
    

}