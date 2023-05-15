package ws;

import businesslogic.LibretaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import pojos.Libreta;
import pojos.Respuesta;

@Path("auth/libreta")
public class LibretaWS {
    @Context
    private UriInfo context;

    public LibretaWS() {
    }
    
    @Path("consultar/{idUsuario}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta consultar(
            @PathParam("idUsuario") int idUsuario) {
        Respuesta respuesta = new Respuesta();
        
        if(idUsuario == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id valido");
        } else {
            Libreta libreta = new Libreta();
            libreta.setIdUsuario(idUsuario);
            LibretaDAO libretaDAO = new LibretaDAO();
            ArrayList<Libreta> libretasRecuperadas = new ArrayList<>();
            try {
                libretasRecuperadas = libretaDAO.consultarLibretasDeUsuario(libreta);
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
            
            if(libretasRecuperadas.isEmpty()) {
                respuesta.setError(true);
                respuesta.setMensaje("El usuario no tiene libretas");
            } else {
                respuesta.setLibretas(libretasRecuperadas);
                respuesta.setError(false);
                respuesta.setMensaje("Libretas recuperadas");
            }
        }
        
        return respuesta;
    }
    
    @Path("registrar")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrar(
            @FormParam("nombre") String nombre,
            @FormParam("colorHexadecimal") String colorHexadecimal,
            @FormParam("idUsuario") int idUsuario) {
        Respuesta respuesta = new Respuesta();
        
        if(nombre == null || nombre.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un nombre");
        } else if(colorHexadecimal == null || colorHexadecimal.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un color hexadecimal");
        } else if(idUsuario == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id valido");
        } else {
            Libreta libreta = new Libreta();
            libreta.setNombre(nombre);
            libreta.setColorHexadecimal(colorHexadecimal);
            libreta.setIdUsuario(idUsuario);
            LibretaDAO libretaDAO = new LibretaDAO();
            boolean libretaRegistrada = false;
            try {
                libretaRegistrada = libretaDAO.registrarLibreta(libreta);
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
            
            if(libretaRegistrada) {
                respuesta.setError(false);
                respuesta.setMensaje("Registro exitoso");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Registro fallido, una libreta tiene el mismo nombre");
            }
        }
        
        return respuesta;
    }
    
    @Path("actualizar")
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizar(
            @FormParam("idLibreta") int idLibreta,
            @FormParam("nombre") String nombre,
            @FormParam("colorHexadecimal") String colorHexadecimal,
            @FormParam("idUsuario") int idUsuario) {
        Respuesta respuesta = new Respuesta();
        
        if(idLibreta == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de libreta valido");
        } else if(nombre == null || nombre.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un nombre");
        } else if(colorHexadecimal == null || colorHexadecimal.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un color hexadecimal");
        } else if(idUsuario == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de usuario valido");
        } else {
            Libreta libreta = new Libreta();
            libreta.setIdLibreta(idLibreta);
            libreta.setNombre(nombre);
            libreta.setColorHexadecimal(colorHexadecimal);
            libreta.setIdUsuario(idUsuario);
            LibretaDAO libretaDAO = new LibretaDAO();
            boolean libretaActualizada = false;
            try {
                libretaActualizada = libretaDAO.actualizarLibreta(libreta);
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
            
            if(libretaActualizada) {
                respuesta.setError(false);
                respuesta.setMensaje("Actualizacion exitosa");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Actualizacion fallida, una libreta tiene el mismo nombre");
            }
        }
        
        return respuesta;
    }
    
    @Path("eliminar")
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminar(
            @FormParam("idLibreta") int idLibreta) {
        Respuesta respuesta = new Respuesta();
        
        if(idLibreta == 0) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de libreta valido");
        } else {
            Libreta libreta = new Libreta();
            libreta.setIdLibreta(idLibreta);
            LibretaDAO libretaDAO = new LibretaDAO();
            boolean libretaEliminada = false;
            try {
                libretaEliminada = libretaDAO.eliminarLibreta(libreta);
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
            
            if(libretaEliminada) {
                respuesta.setError(false);
                respuesta.setMensaje("Eliminacion exitosa");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Eliminacion fallida");
            }
        }
        
        return respuesta;
    }
}
