package Controller;

import BaseDeDatos.EmpleadoDAO;
import ClasesModelos.Empleado;
import ClasesModelos.Usuario;

import java.util.List;

public class EmpleadoController {
    private EmpleadoDAO empleadoDAO;

    public boolean verificarSesion(Usuario usuario){
        empleadoDAO = new EmpleadoDAO();
        return empleadoDAO.verificarSesion(usuario);
    }

    public List<Empleado> obtenerEmpleados() {
        empleadoDAO = new EmpleadoDAO();
        return empleadoDAO.obtenerEmpleados();
    }
}
