package seguridad;

import java.util.Random;

public class OTP {
    public static String generarOTP() {
        int tamanioOTP = 6;
        String numeros = "0123456789";
        Random random = new Random();
        char[] otp = new char[tamanioOTP];
        for (int i = 0; i < tamanioOTP; i++) {  
            otp[i] = numeros.charAt(random.nextInt(numeros.length()));  
        }  
        return new String(otp);
    }
}
