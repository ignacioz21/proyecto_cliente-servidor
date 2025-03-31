package BaseDeDatos;

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {
    private static final String URL = "jdbc:mysql://localhost:3306/pulpeDB";
    private static final String PASSWORD = "123456789";
    private static final String HOST = "root";

    public static Connection getConexion() {
        Connection conex = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conex = DriverManager.getConnection(URL, HOST, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver jdbc no encontrado");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Coneccion fallida:\nError: " + e.getMessage());
            e.printStackTrace();
        }
        return conex;
    }

    public static void cerrarConexion(Connection conex) {
        if (conex != null) {
            try {
                conex.close();
                System.out.println("Se ha desconectado de la base de datos...");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
