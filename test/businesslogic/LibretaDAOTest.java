package businesslogic;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pojos.Libreta;

public class LibretaDAOTest {
    
    public LibretaDAOTest() {
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
    public void testConsultarLibretasDeUsuario() throws Exception {
        System.out.println("consultarLibretasDeUsuario");
        Libreta libreta = new Libreta();
        libreta.setIdUsuario(4);
        
        LibretaDAO instance = new LibretaDAO();
        ArrayList<Libreta> result = instance.consultarLibretasDeUsuario(libreta);
        
        for(Libreta libretaRegistrada : result) {
            System.out.println(libretaRegistrada);
        }
        
        assertTrue(!result.isEmpty());
    }

    @Test
    public void testRegistrarLibreta() throws Exception {
        System.out.println("registrarLibreta");
        Libreta libreta = new Libreta();
        libreta.setNombre("Innerspeaker");
        libreta.setColorHexadecimal("09a0ab");
        libreta.setIdUsuario(4);
        
        LibretaDAO instance = new LibretaDAO();
        assertTrue(instance.registrarLibreta(libreta));
    }

    @Test
    public void testActualizarLibreta() throws Exception {
        System.out.println("actualizarLibreta");
        Libreta libreta = new Libreta();
        libreta.setIdLibreta(4);
        libreta.setNombre("Innerspeaker");
        libreta.setColorHexadecimal("09a0ab");
        libreta.setIdUsuario(4);
        
        LibretaDAO instance = new LibretaDAO();
        assertTrue(instance.actualizarLibreta(libreta));
    }

    @Test
    public void testEliminarLibreta() throws Exception {
        System.out.println("eliminarLibreta");
        Libreta libreta = new Libreta();
        libreta.setIdLibreta(6);
        LibretaDAO instance = new LibretaDAO();
        assertTrue(instance.eliminarLibreta(libreta));
    }
    
}
