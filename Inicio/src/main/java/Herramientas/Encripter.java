package Herramientas;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class Encripter {

    static String LLAVE = "HerediaCampeonUAUA";
    private static final String SALT = "salespecifico123";


    public SecretKeySpec crearClave(String key) {
        try {
            byte[] chain = key.getBytes("UTF-8");
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            chain = md.digest(chain);
            chain = Arrays.copyOf(chain, 16);
            return new SecretKeySpec(chain, "AES");
        } catch (Exception e) {
            System.out.println("Error al crear la clave: " + e.getMessage());
            return null;
        }
    }

    public String encriptar(String encriptar) {
        try {
            SecretKeySpec secretKey = crearClave(LLAVE);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] chain = encriptar.getBytes("UTF-8");
            byte[] encrypted = cipher.doFinal(chain);
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            System.out.println("Error al encriptar la contraseña: " + e.getMessage());
            return "";
        }
    }

    public String desencriptar(String desencriptar) {
        try {
            SecretKeySpec secretKey = new Encripter().crearClave(LLAVE);
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] chain = Base64.getDecoder().decode(desencriptar);
            byte[] desencrypted = cipher.doFinal(chain);
            return new String(desencrypted);

        } catch (Exception e) {
            System.out.println("Error al desencriptar la contraseña: " + e.getMessage());
            return "";
        }
    }

}