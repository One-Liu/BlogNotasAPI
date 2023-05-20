package businesslogic;

import java.sql.SQLException;
import java.util.ArrayList;
import pojos.Nota;

public interface INotaDAO {
    public ArrayList<Nota> consultarNotasDeUsuario(Nota nota) throws SQLException;
    public boolean registrarNota(Nota nota) throws SQLException;
    public boolean actualizarNota(Nota nota) throws SQLException;
    public boolean eliminarNota(Nota nota) throws SQLException;
}
