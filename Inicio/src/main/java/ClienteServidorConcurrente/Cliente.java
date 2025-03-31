package ClienteServidorConcurrente;

import javax.swing.*;
import java.io.*;
import java.net.Socket;

public class Cliente {
    private static final String HOST = "localhost";
    private static final int PUERTO = 1234;
    private Socket socket;
    private PrintWriter output;
    private BufferedReader input;

    private void conectarServer (String host, int puerto) {
        try {
            socket = new Socket(host, puerto);
            output = new PrintWriter(socket.getOutputStream(), true);
            input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            new Thread(new RecibirMensajes()).start();
            System.out.println("Conectado con el servidor");
        } catch (IOException e) {
            String error = "Error al conectar con el servidor...";
            System.out.println(error);
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    private void enviarMensaje() {
        String msg = "A";
        output.println(msg);
        System.out.println("Se envio: " + msg);
    }

    private void salirChat() {
        try {
            if (output != null) {
                output.println("El cliente se ha desconectado");
            }
            terminarConexion();
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private void terminarConexion() {
        try {
            if (input != null) {
                input.close();
            }else if (output != null) {
                output.close();
            }else if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            System.out.println("Fallo al cerrar las conexiones: " + e.getMessage());
        }
    }

    private class RecibirMensajes implements Runnable {
        @Override
        public void run() {
            try {
                String msg = input.readLine();
                System.out.println("Respuesta: " + msg);
            } catch (IOException e) {
                if (socket.isConnected()) {
                    System.out.println("Conexion con el servidor: " + e.getMessage());
                }
            }
        }
    }
}
