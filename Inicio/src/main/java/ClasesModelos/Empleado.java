package ClasesModelos;

public class Empleado {
    private int idUsuairio;
    private String cargo, departamento, fechaContrato;
    private double salario;

    public Empleado(int idUsuairio, String cargo, String departamento, String fechaContrato, double salario) {
        this.idUsuairio = idUsuairio;
        this.cargo = cargo;
        this.departamento = departamento;
        this.fechaContrato = fechaContrato;
        this.salario = salario;
    }

    public int getIdUsuairio() {
        return idUsuairio;
    }

    public void setIdUsuairio(int idUsuairio) {
        this.idUsuairio = idUsuairio;
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
