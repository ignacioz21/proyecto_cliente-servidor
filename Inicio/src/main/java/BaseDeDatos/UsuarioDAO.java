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
        Encripter encripter = new Encripter();
        String sql = "INSERT INTO usuario (nombre, apellido, password, estado) VALUES (?, ?, ?, ?)";
        String sqlID = "SELECT id_usuario FROM usuario WHERE nombre = ?";
        try (Connection conex = ConexionDB.getConexion()) {
            PreparedStatement pstmt = conex.prepareStatement(sql);

            pstmt.setString(1, nuevoUsuario.getNombre());
            pstmt.setString(2, nuevoUsuario.getApellido());
            pstmt.setString(3, encripter.encriptar(nuevoUsuario.getPassword()));
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

    public boolean convalidarUsuario(String username) {
        String sql = "SELECT nombre FROM usuario WHERE nombre = ?";
        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            pstmt.setString(1, username);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar el usuario: " + e.getMessage());
        }
        return false;
    }

    public boolean convalidarSesion(Usuario usuario) {
        Encripter encripter = new Encripter();
        System.out.println("Contraseña desde USUARIOA DAO: " + usuario.getPassword());
        System.out.println("Contraseña desde USUARIOA DAO ENCRIPTADA: " + encripter.encriptar(usuario.getPassword()));
        String sql = "SELECT nombre, password FROM usuario WHERE nombre = ? AND password = ?";
        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            pstmt.setString(1, usuario.getNombre());
            pstmt.setString(2, encripter.encriptar(usuario.getPassword()));
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Usuario y contraseña correctos");
                return true;
            }else{
                System.out.println("Usuario o contraseña incorrectos");
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la sesion: " + e.getMessage());
        }
        return false;
    }
}
