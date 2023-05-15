package businesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import pojos.Libreta;

public interface ILibretaDAO {
    public ArrayList<Libreta> consultarLibretasDeUsuario(Libreta libreta) throws SQLException;
    public boolean registrarLibreta(Libreta libreta) throws SQLException;
    public boolean actualizarLibreta(Libreta libreta) throws SQLException;
    public boolean eliminarLibreta(Libreta libreta) throws SQLException;
}
