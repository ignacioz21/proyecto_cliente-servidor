package com.mycompany.inicio;

import java.sql.*;

public class ConexionDB {
    private Connection conexion;
    private PreparedStatement consulta;
    private ResultSet resultado;
    private static final String URL = "jdbc:mysql://localhost:3306/mydb";
    private static final String NAME = "root";
    private static final String PASSWORD = "123456789";

    public static Connection getConexiox() {
        Connection conexion = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = DriverManager.getConnection(URL, NAME, PASSWORD);
            System.out.println("Conexion con exito");
        } catch (ClassNotFoundException e){
            System.out.println("Driver JDBC no encontrado");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Conexion fallida:\nError: " + e.getMessage());
            e.printStackTrace();
        }
        return conexion;
    }
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try     {
                conexion.close();
                System.out.println("Se desconecto la base de datos");
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }


}