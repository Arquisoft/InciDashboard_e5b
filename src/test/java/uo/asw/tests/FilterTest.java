package uo.asw.tests;

import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import uo.asw.InciDashboardE5bApplication;
import uo.asw.apacheKafka.producer.KafkaProducer;
import uo.asw.dbManagement.DBManagementFacade;
import uo.asw.dbManagement.model.Incidence;
import uo.asw.dbManagement.model.Property;
import uo.asw.inciDashboard.filter.RIncidenceP;
import uo.asw.util.exception.BusinessException;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InciDashboardE5bApplication.class)
@WebAppConfiguration
/**
 * Prueba el componente Filter
 */
public class FilterTest {

	@Autowired
	private KafkaProducer kafkaProducer;
	
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

    /**
     * Comprueba que RIncidenceP parsea correctamente los datos basicos
     * de una incidencia en JSON a un objeto Incidence
     * @throws BusinessException
     */
    @Test
    public void testRIncidencePBasicValidData() throws BusinessException {
	    	String json = "{"
	    					+ "\"identifier\": \"uuid\","
			    			+ "\"login\": \"316683136\","
			    			+ "\"password\": \"1234\","
			    			+ "\"kind\": \"Person\","
			    			+ "\"name\": \"Incidencia\","
			    			+ "\"description\": \"Descripcion\""
		    			+ "}";
	    	
	    	Incidence incidence = rIncidenceP.jsonStringToIncidence(json);
	    	
	    	Incidence incidenceTest = new Incidence("uuid"); 
	    	incidenceTest
			.setAgent(dbManagement.getAgent("316683136", "1234", "Person"))
			.setName("Incidencia")
			.setDescription("Descripcion");
	    	
	    	assertTrue(incidence.equalFields(incidenceTest));
    }
    
    /**
     * Comprueba que RIncidenceP parsea correctamente todos los datos
     * de una incidencia (salvo el operador) en JSON a un objeto Incidence 
     * @throws BusinessException
     */
    @Test
    public void testRIncidencePValidData() throws BusinessException {
	    	String json = "{"
		    			+ "\"identifier\": \"uuid\","
		    			+ "\"login\": \"316683136\","
		    			+ "\"password\": \"1234\","
		    			+ "\"kind\": \"Person\","
		    			+ "\"name\": \"Incidencia\","
		    			+ "\"description\": \"Descripcion\","
		    			+ "\"location\": \"1.4,12.3\","
		    			+ "\"tags\": [\"tag1\",\"tag2\"],"
		    			+ "\"properties\": ["
		    			+ "{\"prop1\": \"val1\"},"
		    			+ "{\"prop2\": \"val2\"}"
		    			+ "],"
		    			+ "\"status\": \"open\","
		    			+ "\"expiration\": \"14:60\""
	    			+ "}";
	    	
	    	Incidence incidence = rIncidenceP.jsonStringToIncidence(json);
	    	
	    	Set<Property> propertiesTest = new HashSet<Property>();
	    	propertiesTest.add(new Property("prop1", "val1"));
	    	propertiesTest.add(new Property("prop2", "val2"));
	    	
	    	Incidence incidenceTest = new Incidence("uuid");
	    	
	    	incidenceTest
	    	.setAgent(dbManagement.getAgent("316683136", "1234", "Person"))
	    	.setName("Incidencia")
	    	.setDescription("Descripcion")
	    	.setLocation("1.4,12.3")
	    	.setTags( new String[] {"tag1","tag2"} )
	    	.setProperties(propertiesTest)
	    	.setStatus("open")
	    	.setExpiration("14:60");
	    	
	    	assertTrue(incidence.equalFields(incidenceTest));
    }
   
    /**
     * Comprueba que RIncidenceP parsea correctamente todos los datos
     * de una incidencia (operador incluido) en JSON a un objeto Incidence 
     * @throws BusinessException
     */
    @Test
    public void testRIncidencePValidDataWithOperator() throws BusinessException {
	    	String json = "{"
	    					+ "\"identifier\": \"uuid\","
			    			+ "\"login\": \"316683136\","
			    			+ "\"password\": \"1234\","
			    			+ "\"kind\": \"Person\","
			    			+ "\"name\": \"Incidencia\","
			    			+ "\"description\": \"Descripcion\","
			    			+ "\"location\": \"1.4,12.3\","
			    			+ "\"tags\": [\"tag1\",\"tag2\"],"
			    			+ "\"properties\": ["
			    							+ "{\"prop1\": \"val1\"},"
			    							+ "{\"prop2\": \"val2\"}"
			    							+ "],"
			    			+ "\"status\": \"open\","
			    			+ "\"operatorIdentifier\": \"99999999A\","
			    			+ "\"expiration\": \"14:60\""
		    			+ "}";
	    	
	    	Incidence incidence = rIncidenceP.jsonStringToIncidence(json);
	    	
	    Set<Property> propertiesTest = new HashSet<Property>();
	    propertiesTest.add(new Property("prop1", "val1"));
	    propertiesTest.add(new Property("prop2", "val2"));
	    	
	    Incidence incidenceTest = new Incidence("uuid");
	    	
	    	incidenceTest
			.setAgent(dbManagement.getAgent("316683136", "1234", "Person"))
			.setOperator(dbManagement.getOperator("99999999A"))
			.setName("Incidencia")
			.setDescription("Descripcion")
			.setLocation("1.4,12.3")
			.setTags( new String[] {"tag1","tag2"} )
			.setProperties(propertiesTest)
			.setStatus("open")
			.setExpiration("14:60");
	    	
	    	assertTrue(incidence.equalFields(incidenceTest));
    }
    
    /**
     * La incidencia no tiene identificador, por lo que se debe lanzar una excepcion.
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void testRIncidencePInvalidHasntGotIdentifier() throws BusinessException {
	    	String json = "{"
		    			+ "\"login\": \"316683136\","
		    			+ "\"password\": \"1234\","
		    			+ "\"kind\": \"Person\","
		    			+ "\"name\": \"Incidencia\","
		    			+ "\"description\": \"Descripcion\","
		    			+ "\"location\": \"1.4,12.3\","
		    			+ "\"tags\": [\"tag1\",\"tag2\"],"
		    			+ "\"properties\": ["
		    			+ "{\"prop1\": \"val1\"},"
		    			+ "{\"prop2\": \"val2\"}"
		    			+ "],"
		    			+ "\"status\": \"open\","
		    			+ "\"expiration\": \"14:60\""
	    			+ "}";
	    	
	    	Incidence incidence = rIncidenceP.jsonStringToIncidence(json);
    }
    
    /**
     * No existe el agente con esos datos, por lo que se debe lanzar una excepcion.
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void testRIncidencePInvalidAgent() throws BusinessException {
	    	String json = "{"
	    					+ "\"identifier\": \"uuid\","
			    			+ "\"login\": \"AAAAAAAAA\","
			    			+ "\"password\": \"1234\","
			    			+ "\"kind\": \"Person\","
			    			+ "\"name\": \"Incidencia\","
			    			+ "\"description\": \"Descripcion\","
			    			+ "\"location\": \"1.4,12.3\","
			    			+ "\"tags\": [\"tag1\",\"tag2\"],"
			    			+ "\"properties\": ["
			    							+ "{\"prop1\": \"val1\"},"
			    							+ "{\"prop2\": \"val2\"}"
			    							+ "],"
			    			+ "\"status\": \"open\","
			    			+ "\"expiration\": \"14:60\""
		    			+ "}";
	    	
	    	Incidence incidence = rIncidenceP.jsonStringToIncidence(json);
    }
    
    

}