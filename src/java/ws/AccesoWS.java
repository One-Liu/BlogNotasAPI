package ws;


import businesslogic.UsuarioDAO;
import seguridad.OTP;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import pojos.Respuesta;
import pojos.SesionToken;
import pojos.Usuario;
import seguridad.AutorizacionTokenJWT;

@Path("basic/acceso")
public class AccesoWS {

    @Context
    private UriInfo context;

    public AccesoWS() {
    }
    
    @Path("registro")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registro(
            @FormParam("nombre") String nombre,
            @FormParam("apellidos") String apellidos,
            @FormParam("celular") String celular,
            @FormParam("contrasena") String contrasena) {
        Respuesta respuesta = new Respuesta();
        
        if(nombre == null || nombre.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un nombre");
        } else if(apellidos == null || apellidos.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar los apellidos");
        } else if(celular == null || celular.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un celular");
        } else if(contrasena == null || contrasena.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar una contrasena");
        } else {
            Usuario usuario = new Usuario();
            usuario.setNombres(nombre);
            usuario.setApellidos(apellidos);
            usuario.setCelular(celular);
            usuario.setContrasena(contrasena);
            usuario.setOtp(OTP.generarOTP());
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            boolean usuarioRegistrado = false;
            try {
                usuarioRegistrado = usuarioDAO.registroUsuario(usuario);
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
            
            if(usuarioRegistrado) {
                respuesta.setError(false);
                respuesta.setMensaje("Registro exitoso");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Registro fallido");
            }
        }
        
        return respuesta;
    }
    
    @Path("activar")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta activar(
            @FormParam("celular") String celular,
            @FormParam("otp") String otp) {
        Respuesta respuesta = new Respuesta();
        
        if(celular == null || celular.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un celular");
        } else if(otp == null || otp.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un otp");
        } else {
            Usuario usuario = new Usuario();
            usuario.setCelular(celular);
            usuario.setOtp(otp);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            boolean usuarioActivado = false;
            try {
                usuarioActivado = usuarioDAO.activarUsuario(usuario);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            
            if(usuarioActivado) {
                respuesta.setError(false);
                respuesta.setMensaje("Activacion exitosa");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Activacion fallida");
            }
        }
        
        return respuesta;
    }
    
    @Path("login")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta login(
            @FormParam("celular") String celular,
            @FormParam("contrasena") String contrasena) {
        Respuesta respuesta = new Respuesta();
        
        if(celular == null || celular.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un celular");
        } else if(contrasena == null || contrasena.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar una contrasena");
        } else {
            Usuario usuario = new Usuario();
            usuario.setCelular(celular);
            usuario.setContrasena(contrasena);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuarioRecuperado = new Usuario();
            try {
                usuarioRecuperado = usuarioDAO.login(usuario);
            } catch(Exception ex) {
                ex.printStackTrace();
            }
            
            if(usuarioRecuperado.getIdUsuario() == 0) {
                respuesta.setError(true);
                respuesta.setMensaje("Credenciales invalidas");
            } else {
                SesionToken sesionToken = new SesionToken();
                sesionToken.setIdUsuario(respuesta.getUsuario().getIdUsuario());
                sesionToken.setNombres(respuesta.getUsuario().getNombres());
                sesionToken.setApellidos(respuesta.getUsuario().getApellidos());
                sesionToken.setCelular(respuesta.getUsuario().getCelular());
                sesionToken = AutorizacionTokenJWT.generarToken(sesionToken);
                if(sesionToken == null || sesionToken.getTokenacceso()== null || sesionToken.getTokenacceso().isEmpty()) {
                    respuesta.setError(true);
                    respuesta.setMensaje("No se puede generar el token de acceso...");
                } else {
                    respuesta.setError(false);
                    respuesta.setMensaje("Bienvenido: " + sesionToken.getNombres());
                    respuesta.setUsuario(usuarioRecuperado);
                    respuesta.setSesionToken(sesionToken);
                }
            }
        }
        
        return respuesta;
    }
}
