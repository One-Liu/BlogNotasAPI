package businesslogic;

import dataaccess.DataBaseConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pojos.Prioridad;

public class PrioridadDAO implements IPrioridadDAO {

    @Override
    public ArrayList<Prioridad> consultarPrioridades() throws SQLException {
        ArrayList<Prioridad> prioridades = new ArrayList<>();
        String  query = "{CALL getPriorities()}";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        ResultSet resultSet = callableStatement.executeQuery();
        while(resultSet.next()) {
            Prioridad prioridad = new Prioridad();
            prioridad.setIdPrioridad(resultSet.getInt("id_prioridad"));
            prioridad.setNombre(resultSet.getString("nombre"));
            prioridades.add(prioridad);
        }
         dataBaseConnection.closeConection();
        return prioridades;
    }
}
