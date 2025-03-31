package BaseDeDatos;

import ClasesModelos.Empleado;
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


    public static void agregarInventario(Productos producto, Empleado empleado, Inventario inventario) {
        String sql = "INSERT INTO (id_producto, cantidad, fecha_actualizacion, tipo_movimiento, empleado_id) " +
                "VALUES (?, ?, ?, ?, ?)";
        try (
                Connection conex = ConexionDB.getConexion();
                PreparedStatement pstmt = conex.prepareStatement(sql)

        ) {
            pstmt.setInt(1, producto.getIdProducto());
            pstmt.setInt(2, inventario.getCantidad());
            pstmt.setString(3, inventario.getFechaActualizacion());
            pstmt.setString(4, inventario.getTipoMovimiento());
            pstmt.setInt(5, empleado.getIdEmpleado());
        } catch (SQLException e) {
            System.out.println("Error al agregar el producto en el inventario: " + e.getMessage());
        }
    }
}