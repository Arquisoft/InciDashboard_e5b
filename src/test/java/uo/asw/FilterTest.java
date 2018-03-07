package uo.asw;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InciDashboardE5bApplication.class)
@WebAppConfiguration
/**
 * Prueba la API REST
 * Plantillas para los tests extraidos de los tutoriales de Spring (https://spring.io/guides/tutorials/bookmarks/)
 * @since 0.0.1
 */
public class FilterTest {


    @Before
    public void setUp() {
    	
    }
       
    @Test
    public void test() {
    		assertTrue(true);
    }

    

}