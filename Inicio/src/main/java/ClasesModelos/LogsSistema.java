package ClasesModelos;

public class LogsSistema {
    private int id_usuario;
    private String accion, fechaHora, ip, descripcion;

    public LogsSistema(int id_usuario, String accion, String fechaHora, String ip, String descripcion) {
        this.id_usuario = id_usuario;
        this.accion = accion;
        this.fechaHora = fechaHora;
        this.ip = ip;
        this.descripcion = descripcion;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
