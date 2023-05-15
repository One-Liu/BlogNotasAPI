package businesslogic;

import seguridad.OTP;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import pojos.Usuario;

public class UsuarioDAOTest {
    
    public UsuarioDAOTest() {
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
    public void testRegistroUsuario() throws Exception {
        System.out.println("registroUsuario");
        Usuario usuario = new Usuario();
        usuario.setNombres("King");
        usuario.setApellidos("Gizzard");
        usuario.setCelular("2286758484");
        usuario.setContrasena("lordoflightning");
        usuario.setOtp(OTP.generarOTP());
        
        UsuarioDAO instance = new UsuarioDAO();
        assertTrue(instance.registroUsuario(usuario));
    }

    @Test
    public void testActivarUsuario() throws Exception {
        System.out.println("activarUsuario");
        Usuario usuario = new Usuario();
        usuario.setCelular("2288576888");
        usuario.setOtp("552997");
        
        UsuarioDAO instance = new UsuarioDAO();
        assertTrue(instance.activarUsuario(usuario));
    }

    @Test
    public void testLogin() throws Exception {
        System.out.println("login");
        Usuario usuario = new Usuario();
        usuario.setCelular("2283563011");
        usuario.setContrasena("adrianmarcelo");
        
        UsuarioDAO instance = new UsuarioDAO();
        Usuario usuarioRecuperado = instance.login(usuario);
        assertTrue(usuarioRecuperado.getIdUsuario() != 0);
    }

    @Test
    public void testActualizar() throws Exception {
        System.out.println("actualizar");
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(3);
        usuario.setNombres("Adrian");
        usuario.setApellidos("Marcelo");
        usuario.setContrasena("medios13");
        
        UsuarioDAO instance = new UsuarioDAO();
        assertTrue(instance.actualizarUsuario(usuario));
    }
    
}
