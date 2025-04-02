package BaseDeDatos;

import ClasesModelos.Usuario;
import Herramientas.Encripter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class UsuarioDAO {

    public void agregarUsuario(Usuario nuevoUsuario){
        String sql = "INSERT INTO usuario (nombre, apellido, password, estado) VALUES (?, ?, ?, ?)";
        String sqlID = "SELECT id_usuario FROM usuario WHERE nombre = ?";
        String passwordEncriptada = Encripter.encriptar(nuevoUsuario.getPassword());
        try (Connection conex = ConexionDB.getConexion()) {
            PreparedStatement pstmt = conex.prepareStatement(sql);

            pstmt.setString(1, nuevoUsuario.getNombre());
            pstmt.setString(2, nuevoUsuario.getApellido());
            pstmt.setString(3, passwordEncriptada);
            pstmt.setBoolean(4, nuevoUsuario.isEstado());
            pstmt.executeUpdate();
            System.out.println("Usuario creado exitosamente");
        } catch (SQLException e) {
            System.out.println("Error al crear el usuario: " + nuevoUsuario.getNombre() + "\nERROR: " + e.getMessage());
        }

        try (
                Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sqlID)
        ) {
            pstmt.setString(1, nuevoUsuario.getNombre());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                nuevoUsuario.setIdUsuario(rs.getInt("id_usuario"));
            }
            System.out.println("Otorgado correctamente el id al usuario: " + nuevoUsuario.getNombre());
        } catch (SQLException e) {
            System.out.println("Error al dar el ID al usuario: " + nuevoUsuario.getNombre());
            System.out.println(e.getMessage());
        }
    }

    public boolean convalidarSesion(String nombreU, String passwordU) {
        String sql = "SELECT password FROM usuario WHERE nombre = ?";

        try (Connection conex = ConexionDB.getConexion();
             PreparedStatement pstmt = conex.prepareStatement(sql)) {

            pstmt.setString(1, nombreU);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String passwordEncriptada = rs.getString("password");
                    String passwordDesencriptada = Encripter.desencriptar(passwordEncriptada);

                    if (Objects.equals(passwordDesencriptada, passwordU)) {
                        System.out.println("Usuario validado correctamente");
                        return true;
                    } else {
                        System.out.println("Usuario o contraseña incorrectos");
                    }
                } else {
                    System.out.println("Usuario no encontrado");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al validar el usuario: " + e.getMessage());
        }
        return false;
    }

}
