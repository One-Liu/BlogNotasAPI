package businesslogic;

import dataaccess.DataBaseConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import pojos.Nota;

public class NotaDAO implements INotaDAO {

    @Override
    public ArrayList<Nota> consultarNotasDeUsuario(Nota nota) throws SQLException {
        ArrayList<Nota> notas = new ArrayList<>();
        String query = "{CALL getNotesFromUser(?,?,?)}";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setInt(1, nota.getIdUsuarioCreacion());
        callableStatement.setObject(2, nota.getIdLibreta(), Types.INTEGER);
        callableStatement.setObject(3, nota.getIdPrioridad(), Types.INTEGER);
        ResultSet resultSet = callableStatement.executeQuery();
        while(resultSet.next()) {
            Nota notaRecuperada = new Nota();
            notaRecuperada.setIdNota(resultSet.getInt("id_nota"));
            notaRecuperada.setTitulo(resultSet.getString("titulo"));
            notaRecuperada.setContenido(resultSet.getString("contenido"));
            notaRecuperada.setTiempoCreacion(resultSet.getTimestamp("tiempo_creacion"));
            notaRecuperada.setIdUsuarioCreacion(resultSet.getInt("id_usuario_creacion"));
            notaRecuperada.setEliminado(resultSet.getInt("eliminado"));
            notaRecuperada.setIdLibreta(resultSet.getInt("id_libreta"));
            notaRecuperada.setIdPrioridad(resultSet.getInt("id_prioridad"));
            notas.add(notaRecuperada);
        }
        dataBaseConnection.closeConection();
        return notas;
    }

    @Override
    public boolean registrarNota(Nota nota) throws SQLException {
        String query = "{CALL registerNote(?,?,?,?,?)}";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setString(1, nota.getTitulo());
        callableStatement.setString(2, nota.getContenido());
        callableStatement.setInt(3, nota.getIdUsuarioCreacion());
        callableStatement.setInt(4, nota.getIdLibreta());
        callableStatement.setInt(5, nota.getIdPrioridad());
        int affectedRows = callableStatement.executeUpdate();
        dataBaseConnection.closeConection();
        return (affectedRows != 0);
    }

    @Override
    public boolean actualizarNota(Nota nota) throws SQLException {
        String query = "{CALL updateNote(?,?,?,?,?,?)}";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setInt(1, nota.getIdNota());
        callableStatement.setString(2, nota.getTitulo());
        callableStatement.setString(3, nota.getContenido());
        callableStatement.setInt(4, nota.getIdUsuarioCreacion());
        callableStatement.setInt(5, nota.getIdLibreta());
        callableStatement.setInt(6, nota.getIdPrioridad());
        int affectedRows = callableStatement.executeUpdate();
        dataBaseConnection.closeConection();
        return (affectedRows != 0);
    }

    @Override
    public boolean eliminarNota(Nota nota) throws SQLException {
        String query = "{CALL deleteNote(?)}";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setInt(1, nota.getIdNota());
        int affectedRows = callableStatement.executeUpdate();
        dataBaseConnection.closeConection();
        return (affectedRows != 0);
    }
}
