package businesslogic;

import dataaccess.DataBaseConnection;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import pojos.Usuario;

public class UsuarioDAO implements IUsuarioDAO {

    @Override
    public boolean registroUsuario(Usuario usuario) throws SQLException {
        String query = "{CALL registerUser(?,?,?,?,?)}";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setString(1, usuario.getNombres());
        callableStatement.setString(2, usuario.getApellidos());
        callableStatement.setString(3, usuario.getCelular());
        callableStatement.setString(4, usuario.getContrasena());
        callableStatement.setString(5, usuario.getOtp());
        int affectedRows = callableStatement.executeUpdate();
        dataBaseConnection.closeConection();
        return (affectedRows != 0);
    }

    @Override
    public boolean activarUsuario(Usuario usuario) throws SQLException {
        String query = "{CALL activateUser(?,?)}";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setString(1, usuario.getCelular());
        callableStatement.setString(2, usuario.getOtp());
        int affectedRows = callableStatement.executeUpdate();
        dataBaseConnection.closeConection();
        return (affectedRows != 0);
    }

    @Override
    public Usuario login(Usuario usuario) throws SQLException {
        String query = "{CALL login(?,?)}";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setString(1, usuario.getCelular());
        callableStatement.setString(2, usuario.getContrasena());
        ResultSet resultSet = callableStatement.executeQuery();
        Usuario usuarioRecuperado = new Usuario();
        if(resultSet.next()) {
            usuarioRecuperado.setIdUsuario(resultSet.getInt("id_usuario"));
            usuarioRecuperado.setNombres(resultSet.getString("nombres"));
            usuarioRecuperado.setApellidos(resultSet.getString("apellidos"));
            usuarioRecuperado.setTiempoRegistro(resultSet.getTimestamp("tiempo_registro"));
            usuarioRecuperado.setActivo(resultSet.getInt("activo"));
            usuarioRecuperado.setCelular(resultSet.getString("celular"));
            usuarioRecuperado.setContrasena(resultSet.getString("contrasena"));
            usuarioRecuperado.setUltimotokenAcceso(resultSet.getString("ultimo_token_acceso"));
            usuarioRecuperado.setTiempoUltimoAcceso(resultSet.getTimestamp("tiempo_ultimo_acceso"));
            usuarioRecuperado.setOtp(resultSet.getString("otp"));
            usuarioRecuperado.setTiempoActivacion(resultSet.getTimestamp("tiempo_activacion"));
        }
        dataBaseConnection.closeConection();
        return usuarioRecuperado;
    }

    @Override
    public boolean actualizarUsuario(Usuario usuario) throws SQLException {
        String query = "{CALL updateuser(?,?,?,?)}";
        DataBaseConnection dataBaseConnection = new DataBaseConnection();
        Connection connection = dataBaseConnection.getConnection();
        CallableStatement callableStatement = connection.prepareCall(query);
        callableStatement.setInt(1, usuario.getIdUsuario());
        callableStatement.setString(2, usuario.getNombres());
        callableStatement.setString(3, usuario.getApellidos());
        callableStatement.setString(4, usuario.getContrasena());
        int affectedRows = callableStatement.executeUpdate();
        dataBaseConnection.closeConection();
        return (affectedRows != 0);
    }
}
