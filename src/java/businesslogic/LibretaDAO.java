package businesslogic;

import dataaccess.DataBaseConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pojos.Libreta;

public class LibretaDAO implements ILibretaDAO {

    @Override
    public ArrayList<Libreta> consultarLibretasDeUsuario(Libreta libreta) throws SQLException {
        ArrayList<Libreta> libretas = new ArrayList<>();
        String query = "{CALL getNotebooksFromUser(?)}";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setInt(1, libreta.getIdUsuario());
        ResultSet resultSet = callableStatement.executeQuery();
        while(resultSet.next()) {
            Libreta libretaObtenida = new Libreta();
            libretaObtenida.setIdLibreta(resultSet.getInt("id_libreta"));
            libretaObtenida.setNombre(resultSet.getString("nombre"));
            libretaObtenida.setColorHexadecimal(resultSet.getString("color_hexadecimal"));
            libretaObtenida.setIdUsuario(libreta.getIdUsuario());
            libretas.add(libretaObtenida);
        }
        dataBaseConnection.closeConection();
        return libretas;
    }

    @Override
    public boolean registrarLibreta(Libreta libreta) throws SQLException {
        String query = "{CALL registerNotebook(?,?,?)}";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setString(1, libreta.getNombre());
        callableStatement.setString(2, libreta.getColorHexadecimal());
        callableStatement.setInt(3, libreta.getIdUsuario());
        int affectedRows = callableStatement.executeUpdate();
        dataBaseConnection.closeConection();
        return (affectedRows != 0);
    }

    @Override
    public boolean actualizarLibreta(Libreta libreta) throws SQLException {
        String query = "{CALL updateNotebook(?,?,?,?)}";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setInt(1, libreta.getIdLibreta());
        callableStatement.setString(2, libreta.getNombre());
        callableStatement.setString(3, libreta.getColorHexadecimal());
        callableStatement.setInt(4, libreta.getIdUsuario());
        int affectedRows = callableStatement.executeUpdate();
        dataBaseConnection.closeConection();
        return (affectedRows != 0);
    }

    @Override
    public boolean eliminarLibreta(Libreta libreta) throws SQLException {
        String query = "{CALL deleteNotebook(?)}";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setInt(1, libreta.getIdLibreta());
        int affectedRows = callableStatement.executeUpdate();
        dataBaseConnection.closeConection();
        return (affectedRows != 0);
    }
    
}
