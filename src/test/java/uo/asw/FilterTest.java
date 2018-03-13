package uo.asw;

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
       
    @Test
    public void testRIncidencePValidData() {
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
			    			//+ "\"operatorIdentifier\": \"XXXXX\","
			    			+ "\"expiration\": \"14:60\""
		    			+ "}";
	    	
	    	Incidence incidence = null;
	    	try {
	    		incidence = rIncidenceP.jsonStringToIncidence(json);
	    	}catch (Exception e) {
	    		e.printStackTrace();
	    		return;
		}
	    	
	    Set<Property> propertiesTest = new HashSet<Property>();
	    propertiesTest.add(new Property("prop1", "val1"));
	    propertiesTest.add(new Property("prop2", "val2"));
	    	
	    	Incidence incidenceTest = new Incidence();
	    	
	    	incidenceTest
			.setAgent(dbManagement.getAgent("316683136", "1234", "Person"))
			//.setOperator(dbManager.getOperator(""))
			.setName("Incidencia")
			.setDescription("Descripcion")
			.setLocation("1.4,12.3")
			.setTags( new String[] {"tag1","tag2"} )
			.setProperties(propertiesTest)
			.setStatus("open")
			.setExpiration("14:60");
	    	
	    	System.out.println("\n\n\n"+ incidence+ "\n\n\n");
	    	System.out.println("\n\n\n"+ incidenceTest+ "\n\n\n");
	    	
	    	assertTrue(incidence.equals(incidenceTest));
	    	//kafkaProducer.send("incidences", json);
	    	
    }
    
    /**
     * No existe el agente con esos datos, por lo que se debe lanzar una excepcion.
     * @throws BusinessException
     */
    @Test(expected = BusinessException.class)
    public void testRIncidencePInvalidAgent() throws BusinessException {
	    	String json = "{"
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
			    			//+ "\"operatorIdentifier\": \"XXXXX\","
			    			+ "\"expiration\": \"14:60\""
		    			+ "}";
	    	
	    	Incidence incidence = rIncidenceP.jsonStringToIncidence(json);
    }

}