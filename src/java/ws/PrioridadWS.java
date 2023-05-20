package ws;

import businesslogic.PrioridadDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import pojos.Prioridad;
import pojos.Respuesta;

@Path("auth/prioridad")
public class PrioridadWS {
    @Context
    private UriInfo context;
    
    public PrioridadWS() {
    }
    
    @Path("consultar")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Respuesta consultarPrioridad() {
        Respuesta respuesta = new Respuesta();
        
        PrioridadDAO prioridadDAO = new PrioridadDAO();
        ArrayList<Prioridad> prioridades = new ArrayList<>();
        try {
            prioridades = prioridadDAO.consultarPrioridades();
        } catch(SQLException ex) {
            ex.printStackTrace();
        }
        
        if(prioridades.isEmpty()) {
            respuesta.setError(true);
            respuesta.setMensaje("No hay prioridades registradas");
        } else {
            respuesta.setError(false);
            respuesta.setMensaje("Prioridades recuperadas");
            respuesta.setPrioridades(prioridades);
        }
        
        return respuesta;
    }
}
