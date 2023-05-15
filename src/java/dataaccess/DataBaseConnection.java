package dataaccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataBaseConnection {

    private Connection connection;
    private static final String DIRECTION = System.getenv("BLOGNOTAS_DIRECTION");
    private static final String USER = System.getenv("BLOGNOTAS_USER");
    private static final String PASS = System.getenv("BLOGNOTAS_PASSWORD");

    public Connection getConnection() throws SQLException {
        connect();
        return connection;
    }

    private void connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        connection = DriverManager.getConnection(
                DIRECTION, 
                USER, 
                PASS);
    }

    public void closeConection() {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(DataBaseConnection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
