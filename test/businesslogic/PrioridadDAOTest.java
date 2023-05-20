package businesslogic;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pojos.Prioridad;

public class PrioridadDAOTest {
    
    public PrioridadDAOTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testConsultarPrioridades() throws Exception {
        System.out.println("consultarPrioridades");
        PrioridadDAO instance = new PrioridadDAO();
        ArrayList<Prioridad> result = instance.consultarPrioridades();
        assertTrue(!result.isEmpty());
    }
    
}
