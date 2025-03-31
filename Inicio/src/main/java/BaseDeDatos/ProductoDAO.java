package BaseDeDatos;

import ClasesModelos.Categoria;
import ClasesModelos.Productos;
import ClasesModelos.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoDAO {
    public void agregarProducto(Productos nuevoProducto, Categoria categoria, Proveedor proveedor) {
        String sql = "INSERT INTO productos (nombre, descripcion, id_categoria, id_proveedor," +
                " precio, precio_promocional, stock_actual, stock_minimo, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlID = "SELECT id_productos FROM productos WHERE nombre = ?";

        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            pstmt.setString(1, nuevoProducto.getNombre());
            pstmt.setString(2, nuevoProducto.getDescripcion());
            pstmt.setInt(3, categoria.getIdCategoria());
            pstmt.setInt(4, proveedor.getIdEmpresa());
            pstmt.setFloat(5, nuevoProducto.getPrecio());
            pstmt.setFloat(6, nuevoProducto.getPrecioPromocional());
            pstmt.setInt(7, nuevoProducto.getStockActual());
            pstmt.setInt(8, nuevoProducto.getStockMinimo());
            pstmt.setBoolean(9, nuevoProducto.isEstado());
            pstmt.executeUpdate();
            System.out.println("Producto agregado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al agregar el producto: " + nuevoProducto.getNombre() + "\nError: " + e.getMessage());
        }

        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sqlID)) {
            pstmt.setString(1, nuevoProducto.getNombre());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                nuevoProducto.setIdProducto(rs.getInt("id_producto"));
            }
            System.out.println("ID otorgado correctamente al producto: " + nuevoProducto.getNombre());
        } catch (SQLException e) {
            System.out.println("Error al dar el ID al producto: " + nuevoProducto.getNombre());
            System.out.println("ERROR: " + e.getMessage());
        }

    }
}
