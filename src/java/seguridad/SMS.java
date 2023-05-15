package seguridad;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SMS {
    private static final String ACCOUNT_SID = System.getenv("TWILIO_ACCOUNT_SID");
    private static final String AUTH_TOKEN = System.getenv("TWILIO_AUTH_TOKEN");

    public static void sendOTP(String celularUsuario, String otp) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber(celularUsuario),
                new com.twilio.type.PhoneNumber("+12545664494"),
                "Su codigo de autenticacion es: " + otp)
            .create();
    }
}
