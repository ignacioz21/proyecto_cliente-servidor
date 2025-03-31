package BaseDeDatos;

import ClasesModelos.Usuario;

public class Main {
    public static void main(String[] args) {
        Usuario andres = new Usuario("Andres", "Martinez", "1230912'309'12", true);
        System.out.println(andres.getIdUsuario());
        UsuarioDAO a = new UsuarioDAO();
        a.agregarUsuario(andres);
        System.out.println(andres.getIdUsuario());

    }
}
