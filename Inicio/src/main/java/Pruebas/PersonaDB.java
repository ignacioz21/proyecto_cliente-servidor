package Pruebas;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaDB {
    private static final String URL = "jdbc:mysql://localhost:3306/mydb1";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "123456789";

    public static class Persona {
        private int id;
        private String nombre;
        private String email;
        private String direccion;
        private String password;
        private String telefono;

        public Persona(String nombre, String email) {
            this.nombre = nombre;
            this.email = email;
        }

        public Persona(int id, String nombre, String email, String direccion, String telefono, String password) {
            this.id = id;
            this.nombre = nombre;
            this.email = email;
            this.direccion = direccion;
            this.password = password;
            this.telefono = telefono;
        }

        public String getTelefono() {
            return telefono;
        }

        public void setTelefono(String telefono) {
            this.telefono = telefono;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDireccion() {
            return direccion;
        }

        public void setDireccion(String direccion) {
            this.direccion = direccion;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public static void crearUsuario(Persona usuario) {
            String sql = "INSERT INTO Persona (nombre, email, telefono, direccion, pass) VALUES (?, ?, ?, ?, ?)";

            try (Connection conex = ConexionDB.getConexiox()) {
                PreparedStatement pstmt = conex.prepareStatement(sql);

                pstmt.setString(1, usuario.getNombre());
                pstmt.setString(2, usuario.getEmail());
                pstmt.setString(3, usuario.getTelefono());
                pstmt.setString(4, usuario.getDireccion());
                pstmt.setString(5, usuario.getPassword());
                pstmt.executeUpdate();
                System.out.println("Usuario creado exitosamente");
            } catch (SQLException e) {
                System.out.println("Error al crear el usuario: " + usuario + "\nERROR: " + e.getMessage());
            }
        }

        public static List<Persona> obtenerPersonas() {
            List<Persona> usuarios = new ArrayList<>();
            String sql = "SELECT * FROM Persona";

            try (Connection conex = DriverManager.getConnection(URL, USUARIO, PASSWORD)) {
                Statement stmt = conex.createStatement();
                ResultSet rs = stmt.executeQuery(sql);

                while (rs.next()) {
                    usuarios.add(new Persona(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            rs.getString("direccion"),
                            rs.getString("pass")
                    ));
                }
            } catch (SQLException e) {
                System.out.println("Error al obetener usuarios: " + e.getMessage());
            }
            return usuarios;
        }

        public static Persona buscarUsuarioPorID(int id) {
            String sql = "SELECT * FROM Persona WHERE id = ?";

            try (Connection conex = DriverManager.getConnection(URL, USUARIO, PASSWORD); PreparedStatement pstmt = conex.prepareStatement(sql)) {
                pstmt.setInt(1, id);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    return new Persona(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getString("email"),
                            rs.getString("telefono"),
                            rs.getString("direccion"),
                            rs.getString("pass")
                    );
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar el usuario: " + e.getMessage());
            }
            return null;
        }

        public static void actualizarUsuario(Persona usuario) {
            String sql = "UPDATE Persona SET nombre = ?, email = ?, telefono = ?, direccion = ?, contraseña = ? WHERE id = ?";

            try (
                    Connection conex = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                    PreparedStatement pstmt = conex.prepareStatement(sql)
                    ) {
                pstmt.setString(1, usuario.getNombre());
                pstmt.setString(2, usuario.getEmail());
                pstmt.setString(3, usuario.getDireccion());
                pstmt.setString(4, usuario.getPassword());
                pstmt.setInt(5, usuario.getId());

                System.out.println("El usuario: " + usuario.getNombre() + " ha sido actualizado con exito.");
            } catch (SQLException e) {
                System.out.println("Error al actualizar el usuario: " + usuario.getNombre() + "\nError: " + e.getMessage());
            }
        }

        public static void eliminarUsuario(int id) {
            String sql = "DELETE from Persona WHERE id = ?";

            try (
                    Connection conex = DriverManager.getConnection(URL, USUARIO, PASSWORD);
                    PreparedStatement pstmt = conex.prepareStatement(sql)
                    ) {
                pstmt.setInt(1, id);
                pstmt.executeUpdate();
                System.out.println("Usuario eliminado correctamente");

            }catch (SQLException e) {
                System.out.println("Error al eliminar el usuario de id: " + id + "\nError: " + e.getMessage());
            }
        }
    }
}
