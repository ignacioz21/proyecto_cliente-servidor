package Pruebas;

import BaseDeDatos.CategoriaDAO;
import BaseDeDatos.ProductoDAO;
import BaseDeDatos.ProveedorDAO;
import BaseDeDatos.UsuarioDAO;
import ClasesModelos.Categoria;
import ClasesModelos.Productos;
import ClasesModelos.Proveedor;
import ClasesModelos.Usuario;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        Usuario julieta = new Usuario("julieta", "Peraltez", "pepito123", true);
        UsuarioDAO daoUsuario = new UsuarioDAO();
        daoUsuario.agregarUsuario(julieta);
        System.out.println(daoUsuario.convalidarSesion(julieta.getNombre(), "1234234234"));
         */

        /*
        Categoria seleccionada = new Categoria();
        Proveedor seleccionadoProveedor = new Proveedor();
        CategoriaDAO a = new CategoriaDAO();
        ProveedorDAO b = new ProveedorDAO();
        ProductoDAO c = new ProductoDAO();
        List<Categoria> categorias = new ArrayList<>();
        List<Proveedor> proveedores = new ArrayList<>();
        List<Productos> productos = new ArrayList<>();
        categorias = a.mostrarCategorias();
        proveedores = b.mostrarProveedores();
        productos = c.mostrarProductos();
        for (Categoria categoria : categorias) {
            System.out.println("ID: " + categoria.getIdCategoria() + ", Nombre: " + categoria.getNombre() + ", Descripcion: " + categoria.getDescripcion());
        }
        for (Proveedor proveedor : proveedores) {
            System.out.println("ID: " + proveedor.getIdEmpresa() + ", Nombre: " + proveedor.getNombreEmpresa() + ", Estado: " + proveedor.isEstado());
        }
        for (Productos producto : productos) {
            System.out.println("ID: " + producto.getIdProducto() + ", Nombre: " + producto.getNombre() + ", Descripcion: " + producto.getDescripcion() + ", Precio: " + producto.getPrecio() + ", Stock Actual: " + producto.getStockActual());
        }
        for (Categoria categoria : categorias) {
            if (categoria.getNombre().equals("Comida")) {
                seleccionada = categoria;
                break;
            }
        }

        for (Proveedor proveedor : proveedores) {
            if (proveedor.getNombreEmpresa().equals("Bimbo")) {
                seleccionadoProveedor = proveedor;
                break;
            }
        }


        Usuario usuario = new Usuario("b", "b", "b", true);
         */

        ProductoDAO daoProducto = new ProductoDAO();
        CategoriaDAO categoriaDAO = new CategoriaDAO();
        List<Productos> productos = daoProducto.mostrarProductos();
        List<Categoria> categorias = categoriaDAO.mostrarCategorias();
        for (Productos productosA : productos) {
            System.out.println(productosA.toString());
        }

        for (Categoria categoria : categorias) {
            System.out.println(categoria.toString());
        }

    }
}
