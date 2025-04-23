package Cliente;

import ClasesModelos.*;
import shared.Request;
import shared.Response;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;

public class ClienteController {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 1234;
    private Socket clientSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public ClienteController() {
        try {
            clientSocket = new Socket(SERVER_ADDRESS, SERVER_PORT);
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
            System.out.println("Conexión establecida con el servidor.");
        } catch (Exception e) {
            System.err.println("Error al conectar con el servidor: " + e.getMessage());
        }
    }

    public Response eviarSolictud(Request request) {
        try {
            out.writeObject(request);
            out.flush();
            return (Response) in.readObject();
        } catch (Exception e) {
            System.err.println("Error al enviar la solicitud: " + e.getMessage());
            e.printStackTrace();
            return new Response("Error al procesar la solicitud", null, false);
        }
    }

    public Usuario crearUsuario(Usuario usuario) {
        Request request = new Request("CREAR_USUARIO", usuario);
        Response response = eviarSolictud(request);
        if (response.isSuccess()) {
            return (Usuario) response.getData();
        } else {
            System.err.println("Error al crear el usuario: " + response.getMessage());
            return null;
        }
    }

    public boolean convalidarUsuario(Usuario usuario) {
        Request request = new Request("VALIDAR_SESION", usuario);
        Response response = eviarSolictud(request);
        if (response.isSuccess()) {
            return (boolean) response.getData();
        } else {
            System.err.println("Error al validar la sesión: " + response.getMessage());
            return false;
        }
    }

    public boolean convalidarSesion(Usuario usuario) {
        Request request = new Request("CONVALIDAR_SESION", usuario);
        Response response = eviarSolictud(request);
        if (response.isSuccess()) {
            return (boolean) response.getData();
        } else {
            System.err.println("Error al validar la sesión: " + response.getMessage());
            return false;
        }
    }

    public Productos crearProducto(Productos producto) {
        Request request = new Request("CREAR_PRODUCTOS", producto);
        Response response = eviarSolictud(request);
        if (response.isSuccess()) {
            return (Productos) response.getData();
        } else {
            System.err.println("Error al crear el producto: " + response.getMessage());
            return null;
        }
    }

    public List<Productos> obtenerProductos() {
        Request request = new Request("OBTENER_PRODUCTOS", null);
        Response response = eviarSolictud(request);
        if (response.isSuccess()) {
            return (List<Productos>) response.getData();
        } else {
            System.err.println("Error al obtener los productos: " + response.getMessage());
            return null;
        }
    }

    public Inventario guardarInventario(Inventario inventario) {
        Request request = new Request("GUARDAR_INVENTARIO", inventario);
        Response response = eviarSolictud(request);
        if (response.isSuccess()) {
            return (Inventario) response.getData();
        } else {
            System.err.println("Error al crear el inventario: " + response.getMessage());
            return null;
        }
    }

    public Categoria crearCategoria(Categoria categoria) {
        Request request = new Request("CREAR_CATEGORIA", categoria);
        Response response = eviarSolictud(request);
        if (response.isSuccess()) {
            return (Categoria) response.getData();
        } else {
            System.err.println("Error al crear la categoria: " + response.getMessage());
            return null;
        }
    }

    public List<Categoria> obtenerCategorias() {
        Request request = new Request("OBTENER_CATEGORIAS", null);
        Response response = eviarSolictud(request);
        if (response.isSuccess()) {
            return (List<Categoria>) response.getData();
        } else {
            System.err.println("Error al obtener las categorias: " + response.getMessage());
            return null;
        }
    }

    public Productos cambiarStock (Productos productoComprar) {
        Request request = new Request("ACTUALIZAR_STOCK", productoComprar);
        Response response = eviarSolictud(request);
        if (response.isSuccess()) {
            return (Productos) response.getData();
        } else {
            System.err.println("Error al actualizar el stock: " + response.getMessage());
            return null;
        }
    }

    public List<Productos> filtrarProductos (String nombre) {
        Request request = new Request("FILTRAR_PRODUCTOS", nombre);
        Response response = eviarSolictud(request);
        if (response.isSuccess()) {
            return (List<Productos>) response.getData();
        } else {
            System.err.println("Error al filtrar los productos: " + response.getMessage());
            return null;
        }
    }

    public boolean verificarEmpleado(Usuario usuario) {
        Request request = new Request("VERIFICAR_EMPLEADO", usuario);
        Response response = eviarSolictud(request);
        if (response.isSuccess()) {
            return (boolean) response.getData();
        } else {
            System.err.println("Error al verificar el empleado: " + response.getMessage());
            return false;
        }
    }

    public Usuario obtenerUsuarioPorId(Usuario usuario) {
        Request request = new Request("OBTENER_ID_USUARIO", usuario);
        Response response = eviarSolictud(request);
        if (response.isSuccess()) {
            return (Usuario) response.getData();
        } else {
            System.err.println("Error al obtener el ID del usuario: " + response.getMessage());
            return null;
        }
    }
}
