package Pruebas;

import BaseDeDatos.UsuarioDAO;
import ClasesModelos.Usuario;

public class Main {
    public static void main(String[] args) {
        Usuario julieta = new Usuario("julieta", "Peraltez", "pepito123", true);
        UsuarioDAO daoUsuario = new UsuarioDAO();
        daoUsuario.agregarUsuario(julieta);
        System.out.println(daoUsuario.convalidarSesion(julieta.getNombre(), julieta.getPassword()));

    }
}
