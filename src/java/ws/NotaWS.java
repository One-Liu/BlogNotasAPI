package ws;

import businesslogic.NotaDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import pojos.Nota;
import pojos.Respuesta;

@Path("auth/nota")
public class NotaWS {
    @Context
    private UriInfo context;
    
    public NotaWS() {
    }
    
    @Path("consultar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta consultarNotas(
            @QueryParam("idUsuario") Integer idUsuario,
            @QueryParam("idLibreta") Integer idLibreta,
            @QueryParam("idPrioridad") Integer idPrioridad) {
        Respuesta respuesta = new Respuesta();
        
        if(idUsuario == null) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de usuario valido");
        } else {
            Nota nota = new Nota();
            nota.setIdUsuarioCreacion(idUsuario);
            nota.setIdLibreta(idLibreta);
            nota.setIdPrioridad(idPrioridad);
            NotaDAO notaDAO = new NotaDAO();
            ArrayList<Nota> notas = new ArrayList<>();
            try {
                notas = notaDAO.consultarNotasDeUsuario(nota);
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
            
            if(notas.isEmpty()) {
                respuesta.setError(true);
                respuesta.setMensaje("No se encontraron notas con los datos proporcionados");
            } else {
                respuesta.setError(false);
                respuesta.setMensaje("Notas recuperadas");
                respuesta.setNotas(notas);
            }
        }
        
         return respuesta;
    }
    
    @Path("registrar")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta registrarNota(
            @FormParam("titulo") String titulo,
            @FormParam("contenido") String contenido,
            @FormParam("idUsuarioCreacion") Integer idUsuarioCreacion,
            @FormParam("idLibreta") Integer idLibreta,
            @FormParam("idPrioridad") Integer idPrioridad) {
        Respuesta respuesta = new Respuesta();
        
        if(titulo == null || titulo.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un titulo");
        } else if(contenido == null || contenido.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un contenido");
        } else if(idUsuarioCreacion == null || idUsuarioCreacion < 1) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de usuario valido");
        } else if(idLibreta == null || idLibreta < 1) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de libreta valido");
        } else if(idPrioridad == null || idPrioridad < -1 || idPrioridad > 1) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere de ingresar un id de prioridad valido");
        } else {
            Nota nota = new Nota();
            nota.setTitulo(titulo);
            nota.setContenido(contenido);
            nota.setIdUsuarioCreacion(idUsuarioCreacion);
            nota.setIdLibreta(idLibreta);
            nota.setIdPrioridad(idPrioridad);
            NotaDAO notaDAO = new NotaDAO();
            boolean notaRegistrada = false;
            try {
                notaRegistrada = notaDAO.registrarNota(nota);
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
            
            if(notaRegistrada) {
                respuesta.setError(false);
                respuesta.setMensaje("Nota registrada exitosamente");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Registro de nota fallido. Una nota tiene el mismo titulo, libreta y usuario");
            }
        }
        
        return respuesta;
    }
    
    @Path("actualizar")
    @PUT
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta actualizarNota(
            @FormParam("idNota") Integer idNota,
            @FormParam("titulo") String titulo,
            @FormParam("contenido") String contenido,
            @FormParam("idUsuarioCreacion") Integer idUsuarioCreacion,
            @FormParam("idLibreta") Integer idLibreta,
            @FormParam("idPrioridad") Integer idPrioridad) {
        Respuesta respuesta = new Respuesta();
        
        if(idNota == null) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de nota valido");
        } else if(titulo == null || titulo.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un titulo");
        } else if(contenido == null || contenido.trim().isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un contenido");
        } else if(idUsuarioCreacion == null) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de usuario valido");
        } else if(idLibreta == null) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de libreta valido");
        } else if(idPrioridad == null || idPrioridad < -1 || idPrioridad > 1) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere de ingresar un id de prioridad valido");
        } else {
            Nota nota = new Nota();
            nota.setIdNota(idNota);
            nota.setTitulo(titulo);
            nota.setContenido(contenido);
            nota.setIdUsuarioCreacion(idUsuarioCreacion);
            nota.setIdLibreta(idLibreta);
            nota.setIdPrioridad(idPrioridad);
            NotaDAO notaDAO = new NotaDAO();
            boolean notaActualizada = false;
            try {
                notaActualizada = notaDAO.actualizarNota(nota);
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
            
            if(notaActualizada) {
                respuesta.setError(false);
                respuesta.setMensaje("Nota actualizada exitosamente");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Actualizacion de nota fallido. Una nota tiene el mismo titulo, libreta y usuario");
            }
        }
        
        return respuesta;
    }
    
    @Path("eliminar")
    @DELETE
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta eliminarNota(
            @FormParam("idNota") Integer idNota) {
        Respuesta respuesta = new Respuesta();
        
        if(idNota == null) {
            respuesta.setError(true);
            respuesta.setMensaje("Se requiere ingresar un id de nota valido");
        } else {
            Nota nota = new Nota();
            nota.setIdNota(idNota);
            NotaDAO notaDAO = new NotaDAO();
            boolean notaEliminada = false;
            try {
                notaEliminada = notaDAO.eliminarNota(nota);
            } catch(SQLException ex) {
                ex.printStackTrace();
            }
            
            if(notaEliminada) {
                respuesta.setError(false);
                respuesta.setMensaje("Nota eliminada exitosamente");
            } else {
                respuesta.setError(true);
                respuesta.setMensaje("Eliminacion de nota fallido");
            }
        }
        
        return respuesta;
    }
}
