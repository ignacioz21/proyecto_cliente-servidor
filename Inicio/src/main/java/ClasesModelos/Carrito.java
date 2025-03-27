package ClasesModelos;

public class Carrito {
    private int id_usuario;
    private String fecha_creacion;
    private boolean estado;

    public Carrito(int id_usuario, String fecha_creacion, boolean estado) {
        this.id_usuario = id_usuario;
        this.fecha_creacion = fecha_creacion;
        this.estado = estado;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
