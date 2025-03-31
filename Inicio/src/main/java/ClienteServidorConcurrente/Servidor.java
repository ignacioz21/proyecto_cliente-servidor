package ClienteServidorConcurrente;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final int PUERTO = 5000;
    private static final int MAX_PERSONAS = 10;

    public static void main(String[] args) {
        try (ServerSocket servSocket = new ServerSocket(PUERTO)) {
            System.out.println("Conectando con el puerto: " + PUERTO);
            System.out.println("Esperando a que otros usuarios se conecten...");

            while (true) {
                Socket socket = servSocket.accept();
                PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
                output.println("El servidor a alcanzado al maximo de usuarios...");
                servSocket.close();
                System.out.println("Conexion recahazada maximo de usuarios alcanzados...");
            }
        } catch (IOException e) {
            System.out.println("Se causo un error en: " + e.getMessage());
        }
    }
}
