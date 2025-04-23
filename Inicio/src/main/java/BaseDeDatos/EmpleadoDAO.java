package BaseDeDatos;

import ClasesModelos.Empleado;
import ClasesModelos.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO {
    public Empleado agregarEmpleado(Empleado empleado) {
        String sql = "INSERT INTO empleado (id_usuario, cargo, fecha_contrato, salario, departamento)" +
                " VALUES (?, ?, ?, ?, ?)";
        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            pstmt.setInt(1, empleado.getIdUsuario());
            pstmt.setString(2, empleado.getCargo());
            pstmt.setString(3, empleado.getFechaContrato());
            pstmt.setDouble(4, empleado.getSalario());
            pstmt.setString(5, empleado.getDepartamento());
            pstmt.executeUpdate();
            System.out.println("Empleado agregado exitosamente");
            return empleado;
        } catch (SQLException e) {
            System.out.println("Error al agregar el empleado: " + e.getMessage());
        }
        return null;
    }

    public List<Empleado> obtenerEmpleados(){
        String sql = "SELECT * FROM empleado";
        List<Empleado> empleados = new ArrayList<>();
        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("id_empleado"));
                empleado.setIdUsuario(rs.getInt("id_usuario"));
                empleado.setCargo(rs.getString("cargo"));
                empleado.setDepartamento(rs.getString("departamento"));
                empleado.setFechaContrato(rs.getString("fecha_contrato"));
                empleado.setSalario(rs.getDouble("salario"));
                empleados.add(empleado);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar los empleados: " + e.getMessage());
        }
        return empleados;
    }

    public boolean verificarSesion(Usuario usuario) {
        String sql = "SELECT * FROM empleado WHERE id_usuario = ?";
        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            pstmt.setInt(1, usuario.getIdUsuario());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("El empleado existe");
                return true;
            } else {
                System.out.println("El empleado no existe");
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la sesión del empleado: " + e.getMessage());
        }
        return false;
    }

    public Empleado obtenerEmpleadoID(int id){
        String sql = "SELECT * FROM empleado WHERE id_empleado = ?";
        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt("id_empleado"));
                empleado.setIdUsuario(rs.getInt("id_usuario"));
                empleado.setCargo(rs.getString("cargo"));
                empleado.setDepartamento(rs.getString("departamento"));
                empleado.setFechaContrato(rs.getString("fecha_contrato"));
                empleado.setSalario(rs.getDouble("salario"));
                return empleado;
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el empleado por ID: " + e.getMessage());
        }
        return null;
    }
}
