package businesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import pojos.Prioridad;

public interface IPrioridadDAO {
    public ArrayList<Prioridad> consultarPrioridades() throws SQLException;
}
