package Server;

import ClasesModelos.Categoria;
import ClasesModelos.Inventario;
import ClasesModelos.Productos;
import ClasesModelos.Usuario;
import Controller.CategoriaController;
import Controller.InventarioController;
import Controller.ProductoController;
import Controller.UsuarioController;
import shared.Request;
import shared.Response;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable{
    private final Socket clienteSocket;
    private UsuarioController usuarioController;
    private ProductoController productoController;
    private InventarioController inventarioController;
    private CategoriaController categoriaController;


    public ClientHandler(Socket clienteSocket) {
        this.clienteSocket = clienteSocket;
        this.usuarioController = new UsuarioController();
        this.productoController = new ProductoController();
        this.inventarioController = new InventarioController();
        this.categoriaController = new CategoriaController();
    }

    @Override
    public void run() {
        try(
                ObjectOutputStream out = new ObjectOutputStream(clienteSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clienteSocket.getInputStream())

        ){
            while (!clienteSocket.isClosed()) {
                Request request = (Request) in.readObject();
                Response response = processRequest(request);
                out.writeObject(response);
                out.flush();
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        } finally {
            try {
                clienteSocket.close();
            } catch (IOException e) {
                System.out.println("Error al cerrar el socket: " + e.getMessage());
            }
        }
    }

    private Response processRequest(Request request) {
        try {
            switch (request.getAction()) {
                case "CREAR_USUARIO":
                    Usuario usuario = (Usuario) request.getData();
                    Usuario creado = usuarioController.agregarUsuario(usuario);
                    return new Response("Usuario creado con exito", creado, true);
                case "ACTUALIZAR_USUARIO":
                    // Lógica para actualizar un usuario
                    break;
                case "ELIMINAR_USUARIO":
                    // Lógica para eliminar un usuario
                    break;
                case "CREAR_PRODUCTO":
                    Productos producto = (Productos) request.getData();
                    Productos productoCreado = productoController.agregarProducto(producto);
                    return new Response("Producto creado con exito", productoCreado, true);
                case "OBTENER_PRODUCTOS":
                    List<Productos> productos = productoController.obtenerProductos();
                    return new Response("Lista de productos obtenida", productos, true);
                case "ACTUALIZAR_PRODUCTO":
                    // Lógica para actualizar un producto
                    break;
                case "ELIMINAR_PRODUCTO":
                    // Lógica para eliminar un producto
                    break;
                case "GUARDAR_PRODUCTO":
                    Inventario inventario = (Inventario) request.getData();
                    Inventario inventarioGuardado = inventarioController.agregarAInventario(inventario);
                    return new Response("Producto guardado en inventario", inventarioGuardado, true);
                case "BUSCAR_PRODUCTO":
                    // Lógica para buscar un producto
                    break;
                case "CREAR_CATEGORIA":
                    Categoria categoria = (Categoria) request.getData();
                    Categoria categoriaCreada = categoriaController.agregarCategoria(categoria);
                    return new Response("Categoria creada con exito", categoriaCreada, true);
                case "ACTUALIZAR_CATEGORIA":
                    // Lógica para actualizar una categoría
                    break;
                case "OBTENER_CATEGORIAS":
                    List<Categoria> categorias = categoriaController.obtenerCategorias();
                    return new Response("Lista de categorias obtenida", categorias, true);

            }
        }catch (Exception e) {
            System.out.println("Error al procesar la solicitud: " + e.getMessage());
            return new Response("Error al procesar la solicitud", null, false);
        }
        return new Response("Acción no reconocida", null, false);
    }
}
