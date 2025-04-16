package Controller;

import BaseDeDatos.InventarioDAO;
import ClasesModelos.Inventario;

public class InventarioController {
    InventarioDAO inventarioDAO;

    public Inventario agregarAInventario(Inventario inventario) {
        inventarioDAO = new InventarioDAO();
        return inventarioDAO.agregarInventario(inventario);
    }
}
