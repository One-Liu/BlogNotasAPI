package businesslogic;

import java.sql.SQLException;
import pojos.Usuario;

public interface IUsuarioDAO {
    public boolean registroUsuario(Usuario usuario) throws SQLException;
    public boolean activarUsuario(Usuario usuario) throws SQLException;
    public Usuario login(Usuario usuario) throws SQLException;
    public boolean actualizarUsuario(Usuario usuario) throws SQLException;
}
