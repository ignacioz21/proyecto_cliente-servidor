package BaseDeDatos;

import ClasesModelos.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProveedorDAO {
    public void agregarProveedor(Proveedor nuevoProveedor){
        String sql = "INSERT INTO proveedor (nombreEmpresa, estado) WHERE (?, ?)";
        String sqlID = "SELECT id_proveedor FROM proveedor WHERE nombreEmpresa = ?";
        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            pstmt.setString(1, nuevoProveedor.getNombreEmpresa());
            pstmt.setBoolean(2, nuevoProveedor.isEstado());
            pstmt.executeUpdate();
            System.out.println("Proveedor agregado exitosamente");
        } catch (SQLException e) {
            System.out.println("Erro al agregar al proveedor: " + nuevoProveedor.getNombreEmpresa());
            System.out.println("ERROR: " + e.getMessage());
        }

        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            pstmt.setString(1, nuevoProveedor.getNombreEmpresa());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                nuevoProveedor.setIdEmpresa(rs.getInt("id_proveedor"));
            }
            System.out.println("Otorgado correctamente el id al proveedor: " + nuevoProveedor.getNombreEmpresa());
        } catch (SQLException e) {
            System.out.println("Error al dar el ID al usuario: " + nuevoProveedor.getNombreEmpresa());
            System.out.println(e.getMessage());
        }

    }
}
