package ClasesModelos;

import BaseDeDatos.UsuarioDAO;

public class Usuario {
    private int idUsuario;
    private String nombre, apellido, password;
    private boolean estado;

    public Usuario(String nombre, String apellido, String password, boolean estado) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.password = password;
        this.estado = estado;
    }
    public Usuario() {
        this.nombre = "";
        this.apellido = "";
        this.password = "";
        this.estado = true;
    }

    public Usuario(String nombre, String contrasena) {
        this.nombre = nombre;
        this.password = contrasena;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
