package Herramientas;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Encripter {

    // Algoritmo de cifrado - asegúrate de usar CBC con padding
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    // Clave secreta (en producción, guárdala de forma segura, no en el código)
    private static final String SECRET_KEY = "HerediaCampeonUAUA";
    // Sal para añadir seguridad (idealmente, debe ser única por usuario)
    private static final String SALT = "salespecifico123";

    /**
     * Método para encriptar una contraseña
     * @param password La contraseña a encriptar
     * @return La contraseña encriptada en formato Base64
     */
    public static String encriptar(String password) {
        try {
            // Generar un IV aleatorio
            byte[] iv = new byte[16];
            SecureRandom random = new SecureRandom();
            random.nextBytes(iv);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            // Derivar una clave segura - asegúrate de que sea exactamente de 16, 24 o 32 bytes para AES
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

            // Configurar el cifrador para encriptar
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);

            // Encriptar la contraseña
            byte[] encryptedData = cipher.doFinal(password.getBytes(StandardCharsets.UTF_8));

            // Combinar IV y datos encriptados (IMPORTANTE: primero el IV, luego los datos)
            byte[] encryptedContent = new byte[iv.length + encryptedData.length];
            System.arraycopy(iv, 0, encryptedContent, 0, iv.length);
            System.arraycopy(encryptedData, 0, encryptedContent, iv.length, encryptedData.length);

            // Codificar a Base64 para almacenamiento o transmisión segura
            return Base64.getEncoder().encodeToString(encryptedContent);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException |
                 InvalidKeyException | InvalidAlgorithmParameterException |
                 IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace(); // Añadido para depuración
            throw new RuntimeException("Error al encriptar la contraseña: " + e.getMessage(), e);
        }
    }

    /**
     * Método para desencriptar una contraseña
     * @param encryptedPassword La contraseña encriptada en formato Base64
     * @return La contraseña original
     */
    public static String desencriptar(String encryptedPassword) {
        try {
            // Decodificar el contenido de Base64
            byte[] encryptedContent = Base64.getDecoder().decode(encryptedPassword);

            // Verificar que el contenido tenga al menos el tamaño del IV
            if (encryptedContent.length < 16) {
                throw new IllegalArgumentException("El texto encriptado no tiene un formato válido");
            }

            // Extraer el IV (los primeros 16 bytes)
            byte[] iv = new byte[16];
            System.arraycopy(encryptedContent, 0, iv, 0, iv.length);
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            // Extraer los datos encriptados (el resto del contenido)
            byte[] encryptedData = new byte[encryptedContent.length - iv.length];
            System.arraycopy(encryptedContent, iv.length, encryptedData, 0, encryptedData.length);

            // Derivar la misma clave que se usó para encriptar
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(SECRET_KEY.toCharArray(), SALT.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");

            // Configurar el cifrador para desencriptar
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);

            // Desencriptar los datos
            byte[] decryptedData = cipher.doFinal(encryptedData);

            // Convertir a String y devolver
            return new String(decryptedData, StandardCharsets.UTF_8);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException |
                 InvalidKeyException | InvalidAlgorithmParameterException |
                 IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace(); // Añadido para depuración
            throw new RuntimeException("Error al desencriptar la contraseña: " + e.getMessage(), e);
        }
    }
}
