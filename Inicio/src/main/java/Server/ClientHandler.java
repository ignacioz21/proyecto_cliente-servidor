package Server;

import ClasesModelos.*;
import Controller.*;
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
    private EmpleadoController empleadoController;


    public ClientHandler(Socket clienteSocket) {
        this.clienteSocket = clienteSocket;
        this.usuarioController = new UsuarioController();
        this.productoController = new ProductoController();
        this.inventarioController = new InventarioController();
        this.categoriaController = new CategoriaController();
        this.empleadoController = new EmpleadoController();
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
                case "VALIDAR_USUARIO":
                    Usuario usuarioValidar = (Usuario) request.getData();
                    boolean esValido = usuarioController.validarUsuario(usuarioValidar.getNombre());
                    if (esValido) {
                        return new Response("Usuario validado con éxito", true, true);
                    } else {
                        return new Response("Usuario o contraseña incorrectos", false, false);
                    }
                case "CONVALIDAR_SESION":
                    Usuario usuarioConvalidar = (Usuario) request.getData();
                    boolean sesionConvalidada = usuarioController.convalidarSesion(usuarioConvalidar);
                    if (sesionConvalidada) {
                        return new Response("Sesión convalidada con éxito", true, true);
                    } else {
                        return new Response("Usuario o contraseña incorrectos", false, false);
                    }
                case "OBTENER_ID_USUARIO":
                    Usuario usuarioId = (Usuario) request.getData();
                    Usuario idUsuario = usuarioController.obtenerUsuarioPorId(usuarioId);
                    return new Response("ID de usuario obtenido con éxito", idUsuario, true);
                case "CREAR_PRODUCTO":
                    Productos producto = (Productos) request.getData();
                    Productos productoCreado = productoController.agregarProducto(producto);
                    return new Response("Producto creado con exito", productoCreado, true);
                case "OBTENER_PRODUCTOS":
                    List<Productos> productos = productoController.obtenerProductos();
                    return new Response("Lista de productos obtenida", productos, true);
                case "ACTUALIZAR_PRODUCTO":
                    Productos productoEdit = (Productos) request.getData();
                    boolean productoActualizado = productoController.editarProducto(productoEdit);
                    if (productoActualizado) {
                        return new Response("Producto actualizado con exito", true, true);
                    } else {
                        return new Response("Error al actualizar el producto", false, false);
                    }
                case "ELIMINAR_PRODUCTO":
                    Productos productoEliminar = (Productos) request.getData();
                    boolean productoEliminado = productoController.inhabilitarProducto(productoEliminar);
                    if (productoEliminado) {
                        return new Response("Producto eliminado con exito", true, true);
                    } else {
                        return new Response("Error al eliminar el producto", false, false);
                    }
                case "GUARDAR_PRODUCTO":
                    Inventario inventario = (Inventario) request.getData();
                    Inventario inventarioGuardado = inventarioController.agregarAInventario(inventario);
                    return new Response("Producto guardado en inventario", inventarioGuardado, true);
                case "BUSCAR_PRODUCTO":
                    Productos producto1 = (Productos) request.getData();
                    Productos productoBuscado = productoController.buscarProductos(producto1);
                    if (productoBuscado != null) {
                        return new Response("Producto encontrado", productoBuscado, true);
                    } else {
                        return new Response("Producto no encontrado", null, false);
                    }
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
                case "ACTUALIZAR_STOCK":
                    Productos productoComprar = (Productos) request.getData();
                    Productos productoComprarActu = inventarioController.cambiarStock(productoComprar);
                    return new Response("Producto actualizado con exito", productoComprarActu, true);
                case "FILTRAR_PRODUCTOS":
                    String nombreCategoria = (String) request.getData();
                    List<Productos> productosFiltrados = productoController.filtrarProductosCategoria(nombreCategoria);
                    return new Response("Lista de productos filtrados", productosFiltrados, true);
                case "VERIFICAR_EMPLEADO":
                    Usuario usuarioEmpleado = (Usuario) request.getData();
                    Empleado empleado = empleadoController.verificarSesion(usuarioEmpleado);
                    if (empleado != null) {
                        return new Response("Empleado validado con éxito", empleado, true);
                    } else {
                        return new Response("Empleado no encontrado", empleado, false);
                    }
                    case "ACTUALIZAR_STOCK_PRODUCTO":
                    Productos productoStock = (Productos) request.getData();
                    boolean stockActualizado = productoController.editarStockProducto(productoStock);
                    if (stockActualizado) {
                        return new Response("Stock actualizado con éxito", true, true);
                    } else {
                        return new Response("Error al actualizar el stock", false, false);
                    }


            }
        }catch (Exception e) {
            System.out.println("Error al procesar la solicitud: " + e.getMessage());
            return new Response("Error al procesar la solicitud", null, false);
        }
        return new Response("Acción no reconocida", null, false);
    }
}
