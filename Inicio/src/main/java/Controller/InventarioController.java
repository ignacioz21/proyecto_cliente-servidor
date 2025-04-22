package Controller;

import BaseDeDatos.InventarioDAO;
import ClasesModelos.Inventario;
import ClasesModelos.ProductoComprar;
import ClasesModelos.Productos;

public class InventarioController {
    InventarioDAO inventarioDAO;

    public Inventario agregarAInventario(Inventario inventario) {
        inventarioDAO = new InventarioDAO();
        return inventarioDAO.agregarInventario(inventario);
    }

    public Productos cambiarStock(Productos productoComprar) {
        inventarioDAO = new InventarioDAO();
        return inventarioDAO.cambiarStockInventario(productoComprar);
    }
}
