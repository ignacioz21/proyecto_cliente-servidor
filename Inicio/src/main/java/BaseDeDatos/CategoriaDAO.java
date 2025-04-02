package BaseDeDatos;

import ClasesModelos.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {

    public void agregarCategoria(Categoria nuevaCategoria){
        String sql = "INSERT INTO categoria (nombre, descripcion, estado) VALUES (?, ?, ?)";
        String sqlID = "SELECT id_categoria FROM categoria WHERE nombre = ?";

        try (
                Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)
        ) {
            pstmt.setString(1, nuevaCategoria.getNombre());
            pstmt.setString(2, nuevaCategoria.getDescripcion());
            pstmt.setBoolean(3, nuevaCategoria.isEstado());
            pstmt.executeUpdate();
            System.out.println("Categoria registrada con exito");
        } catch (SQLException e) {
            System.out.println("Error al crear la categoria: " + nuevaCategoria.getNombre() + "ERROR: " + e.getMessage());
        }

        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sqlID)) {
            pstmt.setString(1, nuevaCategoria.getNombre());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                nuevaCategoria.setIdCategoria(rs.getInt("id_categoria"));
            }
            System.out.println("Otorgado correctamente el id a la categoria: " + nuevaCategoria.getNombre());
        } catch (SQLException e) {
            System.out.println("Error al otorgar el ID a la categoria: " + nuevaCategoria.getNombre());
            System.out.println(e.getMessage());
        }
    }
    public List<Categoria> mostrarCategorias() {
        String sql = "SELECT * FROM categoria";
        List<Categoria> categorias = new ArrayList<>();
        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                categoria.setNombre(rs.getString("nombre"));
                categoria.setDescripcion(rs.getString("descripcion"));
                categoria.setEstado(rs.getBoolean("estado"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar las categorias: " + e.getMessage());
        }
        return categorias;
    }
}
