package Controller;

import BaseDeDatos.ProductoDAO;
import ClasesModelos.Productos;

import java.util.List;

public class ProductoController {
    private ProductoDAO productoDAO;

    public Productos agregarProducto(Productos producto) {
        productoDAO = new ProductoDAO();
        return productoDAO.agregarProducto(producto);
    }

    public List<Productos> obtenerProductos() {
        productoDAO = new ProductoDAO();
        return productoDAO.mostrarProductos();
    }
}
