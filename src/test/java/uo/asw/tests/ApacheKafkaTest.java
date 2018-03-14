package uo.asw.tests;

import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import uo.asw.InciDashboardE5bApplication;
import uo.asw.apacheKafka.producer.KafkaProducer;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InciDashboardE5bApplication.class)
@WebAppConfiguration
/**
 * Prueba el componente Filter
 */
public class ApacheKafkaTest {
	
	@Autowired
	private KafkaProducer kafkaProducer;

    @Before
    public void setUp() {
    	
    }
       
//    @Test
//    public void testSendAndReceiveAnIncidence() {
//    		JSONObject json = new JSONObject();
//    		json.
//    		
//    		kafkaProducer.send("incidences", "exampleMessage");
//    }

}