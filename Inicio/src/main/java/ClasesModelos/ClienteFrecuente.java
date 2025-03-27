package ClasesModelos;

public class ClienteFrecuente {
    private int id_usuario, puntosLealtad;

    public ClienteFrecuente(int id_usuario, int puntosLealtad) {
        this.id_usuario = id_usuario;
        this.puntosLealtad = puntosLealtad;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getPuntosLealtad() {
        return puntosLealtad;
    }

    public void setPuntosLealtad(int puntosLealtad) {
        this.puntosLealtad = puntosLealtad;
    }
}
