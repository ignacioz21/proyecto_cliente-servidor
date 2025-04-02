package ClienteServidorConcurrente;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static final int PUERTO = 5000;
    private static final int MAX_PERSONAS = 5;

    public static void main(String[] args) {
        try (ServerSocket servSocket = new ServerSocket(PUERTO)) {
            System.out.println("Conectando con el puerto: " + PUERTO);
            System.out.println("Esperando a que otros usuarios se conecten...");

            int conexiones = 0;
            while (conexiones < MAX_PERSONAS) {
                Socket socket = servSocket.accept();
                conexiones++;
                new Thread(new ManejadorCliente(socket)).start();
                System.out.println("Cliente conectado. Total de conexiones: " + conexiones);
            }
            System.out.println("El servidor ha alcanzado el máximo de usuarios...");
        } catch (IOException e) {
            System.out.println("Se causo un error en: " + e.getMessage());
        }
    }

    private static class ManejadorCliente implements Runnable {
        private Socket socket;

        public ManejadorCliente(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (PrintWriter output = new PrintWriter(socket.getOutputStream(), true)) {
                output.println("Bienvenido al servidor!");
                // Aquí puedes agregar más lógica para manejar la comunicación con el cliente
            } catch (IOException e) {
                System.out.println("Error al manejar la conexión del cliente: " + e.getMessage());
            }
        }
    }
}
