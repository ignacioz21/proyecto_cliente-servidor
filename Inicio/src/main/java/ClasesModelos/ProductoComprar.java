package ClasesModelos;

import java.io.Serializable;

public class ProductoComprar implements Serializable {
   private int idProducto, cantidad;
    private float productoPrecio;

    public ProductoComprar(int idProducto, int cantidad, float productoPrecio) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.productoPrecio = productoPrecio;
    }


    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getProductoPrecio() {
        return productoPrecio;
    }

    public void setProductoPrecio(float productoPrecio) {
        this.productoPrecio = productoPrecio;
    }
}
