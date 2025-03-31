package ClasesModelos;

import BaseDeDatos.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Empleado {
    private int idUsuario, idEmpleado;
    private String cargo, departamento, fechaContrato;
    private double salario;

    public Empleado(int idUsuairio, String cargo, String departamento, String fechaContrato, double salario) {
        this.idUsuario = idUsuairio;
        this.cargo = cargo;
        this.departamento = departamento;
        this.fechaContrato = fechaContrato;
        this.salario = salario;
        String sql = "SELECT id_empleado FROM productos WHERE id_usuario = ?";
        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            pstmt.setInt(1, this.idUsuario);
            ResultSet rs = pstmt.executeQuery();
            this.idEmpleado = rs.getInt("id_usuario");
        } catch (SQLException e){
            System.out.println("Error al establecer el id del objeto: " + this.idUsuario + "\nERROR: " + e.getMessage());
        }
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getFechaContrato() {
        return fechaContrato;
    }

    public void setFechaContrato(String fechaContrato) {
        this.fechaContrato = fechaContrato;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
}
