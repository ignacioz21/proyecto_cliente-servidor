package Controller;

import BaseDeDatos.CategoriaDAO;
import ClasesModelos.Categoria;

import java.util.List;

public class CategoriaController {
    private CategoriaDAO categoriaDAO;

    public Categoria agregarCategoria(Categoria nuevaCategoria) {
        categoriaDAO = new CategoriaDAO();
        return categoriaDAO.agregarCategoria(nuevaCategoria);

    }

    public List<Categoria> obtenerCategorias() {
        categoriaDAO = new CategoriaDAO();
        return categoriaDAO.mostrarCategorias();
    }
}
