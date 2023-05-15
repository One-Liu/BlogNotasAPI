package seguridad;

import java.util.ResourceBundle;
import java.util.StringTokenizer;
import javax.xml.bind.DatatypeConverter;

public class AutorizacionBasica {
    public static boolean autenticar(String credencialesBase64) {
        if(credencialesBase64 == null) {
            return false;
        }
        try {
            String credenciales = null;
            byte[] bytes = DatatypeConverter.parseBase64Binary(credencialesBase64);
            credenciales = new String(bytes, "UTF-8");

            final StringTokenizer tokenizer = new StringTokenizer(credenciales, ":");
            final String username = tokenizer.nextToken();
            final String password = tokenizer.nextToken();

            ResourceBundle bundle = ResourceBundle.getBundle("seguridad.credenciales_basicauth");
            String user = bundle.getString("user");
            String pass = bundle.getString("pass");
            return user.equals(username) && pass.equals(password);
        } catch(Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
