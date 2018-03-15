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
import uo.asw.dbManagement.model.Incidence;
import uo.asw.inciDashboard.filter.RIncidenceP;
import uo.asw.inciDashboard.filter.properties.FilterResponse;
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
    public void testRIncidencePBasicValidData() throws BusinessException {
	   
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
        

}