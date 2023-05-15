package ws;

import businesslogic.UsuarioDAO;
import java.sql.SQLException;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import pojos.Respuesta;
import pojos.Usuario;

@Path("auth/usuario")
public class UsuarioWS {
    @Context
    private UriInfo context;

    public UsuarioWS() {
    }
    
    @Path("actualizar")
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizar(@FormParam("idUsuario") int idUsuario,
            @FormParam("nombres") String nombres,
            @FormParam("apellidos") String apellidos,
            @FormParam("contrasena") String contrasena) {
        Respuesta respuesta = new Respuesta();
        
        if(idUsuario == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id valido");
        } else if(nombres == null || nombres.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un nombre");
        } else if(apellidos == null || apellidos.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar los apellidos");
        } else if(contrasena == null || contrasena.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar una contrasena");
        } else {
            Usuario usuario = new Usuario();
            usuario.setIdUsuario(idUsuario);
            usuario.setNombres(nombres);
            usuario.setApellidos(apellidos);
            usuario.setContrasena(contrasena);
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            boolean usuarioActualizado = false;
            try {
                usuarioActualizado = usuarioDAO.actualizarUsuario(usuario);
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
            
            if(usuarioActualizado) {
                respuesta.setError(false);
                respuesta.setMensaje("Actualizacion exitosa");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Actualizacion fallida");
            }
        }
        
        return respuesta;
    }
}
