package ClasesModelos;

public class Pedidos {
    private int id_usuario;
    private String fechaPedido, estadoPedido, metodoPago, direccionEnvio;
    private float precioTotal;

    public Pedidos(int id_usuario, String fechaPedido, String estadoPedido, String metodoPago,
                   String direccionEnvio, float precioTotal) {
        this.id_usuario = id_usuario;
        this.fechaPedido = fechaPedido;
        this.estadoPedido = estadoPedido;
        this.metodoPago = metodoPago;
        this.direccionEnvio = direccionEnvio;
        this.precioTotal = precioTotal;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFechaPedido() {
        return fechaPedido;
    }

    public void setFechaPedido(String fechaPedido) {
        this.fechaPedido = fechaPedido;
    }

    public String getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(String estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public String getMetodoPago() {
        return metodoPago;
    }

    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public float getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(float precioTotal) {
        this.precioTotal = precioTotal;
    }
}
