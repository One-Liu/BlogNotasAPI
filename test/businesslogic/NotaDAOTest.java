package businesslogic;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pojos.Nota;

public class NotaDAOTest {
    
    public NotaDAOTest() {
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
    public void testConsultarNotasDeUsuario() throws Exception {
        System.out.println("consultarNotasDeUsuario");
        Nota nota = new Nota();
        nota.setIdUsuarioCreacion(4);
        nota.setIdLibreta(1);
        nota.setIdPrioridad(1);
        NotaDAO instance = new NotaDAO();
        ArrayList<Nota> result = instance.consultarNotasDeUsuario(nota);
        assertTrue(!result.isEmpty());
    }
    
    @Test
    public void testRegistrarNota() throws Exception {
        System.out.println("registrarNota");
        Nota nota = new Nota();
        nota.setTitulo("Leer");
        nota.setContenido("Leer actividad de V&V");
        nota.setIdUsuarioCreacion(4);
        nota.setIdLibreta(4);
        nota.setIdPrioridad(1);
        NotaDAO instance = new NotaDAO();
        boolean result = instance.registrarNota(nota);
        assertTrue(result);
    }
    
    @Test
    public void testActualizarNota() throws Exception {
        System.out.println("actualizarNota");
        Nota nota = new Nota();
        nota.setIdNota(2);
        nota.setTitulo("TODO");
        nota.setContenido("Investigar como hacer un api rest con java");
        nota.setIdUsuarioCreacion(4);
        nota.setIdLibreta(4);
        nota.setIdPrioridad(0);
        NotaDAO instance = new NotaDAO();
        boolean result = instance.actualizarNota(nota);
        assertTrue(result);
    }
    
    @Test
    public void testEliminarNota() throws Exception {
        System.out.println("eliminarNota");
        Nota nota = new Nota();
        nota.setIdNota(2);
        NotaDAO instance = new NotaDAO();
        boolean result = instance.eliminarNota(nota);
        assertTrue(result);
    }
}
