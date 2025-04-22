package BaseDeDatos;

import ClasesModelos.ProductoComprar;
import ClasesModelos.Inventario;
import ClasesModelos.Productos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventarioDAO {
    private Inventario inventario;

    public InventarioDAO(Inventario inventario) {
        this.inventario = inventario;
    }

    public InventarioDAO() {
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }


    public Inventario agregarInventario(Inventario inventario) {
        String sql = "INSERT INTO (id_producto, cantidad, fecha_actualizacion, tipo_movimiento, empleado_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (
                Connection conex = ConexionDB.getConexion();
                PreparedStatement pstmt = conex.prepareStatement(sql)

        ) {
            pstmt.setInt(1, inventario.getId_producto());
            pstmt.setInt(2, inventario.getCantidad());
            pstmt.setString(3, inventario.getFechaActualizacion());
            pstmt.setString(4, inventario.getTipoMovimiento());
            pstmt.setInt(5, inventario.getId_empleado());
            return inventario;
        } catch (SQLException e) {
            System.out.println("Error al agregar el producto en el inventario: " + e.getMessage());
        }
        return null;
    }

    public Productos cambiarStockInventario(Productos producto){
        String sql = "UPDATE productos SET stock_actual = ? WHERE id_producto = ?";
        try (
                Connection conex = ConexionDB.getConexion();
                PreparedStatement pstmt = conex.prepareStatement(sql)
        ) {
            pstmt.setInt(1, producto.getStockActual());
            pstmt.setInt(2, producto.getIdProducto());
            pstmt.executeUpdate();
            System.out.println("Producto actualizado con exito");
            return producto;
        } catch (SQLException e) {
            System.out.println("Error al actualizar la cantidad de inventario");
        }
        return null;
    }
}