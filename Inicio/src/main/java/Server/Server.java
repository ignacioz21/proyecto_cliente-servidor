package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PUERTO = 1234;

    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto: " + PUERTO);
            System.out.println("Esperando usuarios...");

            while (true) {
                Socket clienteSocket = serverSocket.accept();
                System.out.println("Nuevo cliente se ha conectado desde: " + clienteSocket.getInetAddress());

                ClientHandler cliente = new ClientHandler(clienteSocket);
                new Thread(cliente).start();
            }
        }catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
