package Controller;

import BaseDeDatos.UsuarioDAO;
import ClasesModelos.Usuario;

import java.util.List;

public class UsuarioController {
    private UsuarioDAO userDAO;

    public Usuario agregarUsuario(Usuario nuevoUsuario) {
        userDAO = new UsuarioDAO();
        return userDAO.agregarUsuario(nuevoUsuario);
    }

    public List<Usuario> obtenerUsuarios() {
        userDAO = new UsuarioDAO();
        return userDAO.obtenerUsuario();
    }

    public boolean validarUsuario(String usuario) {
        userDAO = new UsuarioDAO();
        return userDAO.convalidarUsuario(usuario);
    }

    public boolean convalidarSesion(Usuario usuario) {
        userDAO = new UsuarioDAO();
        return userDAO.convalidarSesion(usuario);
    }
}
