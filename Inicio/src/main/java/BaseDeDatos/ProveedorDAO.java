package BaseDeDatos;

import ClasesModelos.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    public void agregarProveedor(Proveedor nuevoProveedor){
        String sql = "INSERT INTO proveedor (nombreEmpresa, estado) VALUES (?, ?)";
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

        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sqlID)) {
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
    public List<Proveedor> mostrarProveedores() {
        String sql = "SELECT * FROM proveedor";
        List<Proveedor> proveedores = new ArrayList<>();
        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdEmpresa(rs.getInt("id_proveedor"));
                proveedor.setNombreEmpresa(rs.getString("nombreEmpresa"));
                proveedor.setEstado(rs.getBoolean("estado"));
                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar los proveedores: " + e.getMessage());
        }
        return proveedores;
    }
}
