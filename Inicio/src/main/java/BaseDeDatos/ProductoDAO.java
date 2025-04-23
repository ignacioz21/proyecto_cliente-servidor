package BaseDeDatos;

import ClasesModelos.Categoria;
import ClasesModelos.Productos;
import ClasesModelos.Proveedor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {

    public Productos agregarProducto(Productos nuevoProducto) {
        String sql = "INSERT INTO productos (nombre, descripcion, id_categoria, id_proveedor," +
                " precio, precio_promocional, stock_actual, stock_minimo, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String sqlID = "SELECT id_producto FROM productos WHERE nombre = ?";

        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            pstmt.setString(1, nuevoProducto.getNombre());
            pstmt.setString(2, nuevoProducto.getDescripcion());
            pstmt.setInt(3, nuevoProducto.getIdCategoria());
            pstmt.setInt(4, nuevoProducto.getIdProveedor());
            pstmt.setFloat(5, nuevoProducto.getPrecio());
            pstmt.setFloat(6, nuevoProducto.getPrecioPromocional());
            pstmt.setInt(7, nuevoProducto.getStockActual());
            pstmt.setInt(8, nuevoProducto.getStockMinimo());
            pstmt.setBoolean(9, nuevoProducto.isEstado());
            pstmt.executeUpdate();
            System.out.println("Producto agregado correctamente");
        } catch (SQLException e) {
            System.out.println("Error al agregar el producto: " + nuevoProducto.getNombre() + "\nError: " + e.getMessage());
        }

        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sqlID)) {
            pstmt.setString(1, nuevoProducto.getNombre());
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()) {
                nuevoProducto.setIdProducto(rs.getInt("id_producto"));
            }
            System.out.println("ID otorgado correctamente al producto: " + nuevoProducto.getNombre());
            return nuevoProducto;
        } catch (SQLException e) {
            System.out.println("Error al dar el ID al producto: " + nuevoProducto.getNombre());
            System.out.println("ERROR: " + e.getMessage());
        }
        return null;
    }

    public List<Productos> mostrarProductos() {
        List<Productos> productos = new ArrayList<>();
        String sql = "SELECT nombre, descripcion, precio, stock_actual, estado, id_producto FROM productos";
        String sqlCategoria = "SELECT productos.nombre, categoria.nombre AS categoria_nombre " +
                "FROM productos " +
                "INNER JOIN categoria ON productos.id_categoria = categoria.id_categoria";

        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Productos producto = new Productos();
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setStockActual(rs.getInt("stock_actual"));
                producto.setEstado(rs.getBoolean("estado"));
                producto.setIdProducto(rs.getInt("id_producto"));
                productos.add(producto);
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar los productos: " + e.getMessage());
        }

        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sqlCategoria)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                for (Productos producto : productos) {
                    if (producto.getNombre().equals(rs.getString("nombre"))) {
                        producto.setCategoria(rs.getString("categoria_nombre"));
                        break;
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar las categorias: " + e.getMessage());
        }

        return productos;
    }

    public List<Productos> obtenerProductosID(String nombreCategoria) {
        List<Productos> productos = new ArrayList<>();
        String sql = "SELECT nombre, descripcion, precio, stock_actual, estado, id_producto FROM productos INNER " +
                "JOIN categorias ON productos.id_categoria = categoria.id_categoria WHERE nombre = ?";
        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            pstmt.setString(1, nombreCategoria);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Productos producto = new Productos();
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setStockActual(rs.getInt("stock_actual"));
                producto.setEstado(rs.getBoolean("estado"));
                producto.setIdProducto(rs.getInt("id_producto"));
                productos.add(producto);
                System.out.println("Se han obtenido con exito los productos con su categoria");
                return productos;
            }
        } catch (SQLException e) {
            System.out.println("Error al mostrar los productos: " + e.getMessage());
        }
        return null;
    }

    public Productos buscarProducto(Productos producto) {
        String sql = "SELECT nombre, descripcion, precio, stock_actual, estado FROM productos WHERE nombre = ?";
        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            pstmt.setString(1, producto.getNombre());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                producto.setNombre(rs.getString("nombre"));
                producto.setDescripcion(rs.getString("descripcion"));
                producto.setPrecio(rs.getFloat("precio"));
                producto.setStockActual(rs.getInt("stock_actual"));
                producto.setEstado(rs.getBoolean("estado"));
                System.out.println("Producto encontrado: " + producto.getNombre());
                return producto;
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el producto: " + e.getMessage());
        }
        return null;
    }

    public boolean inhabilitarProducto(Productos productos) {
        String sql = "UPDATE productos SET estado = ? WHERE nombre = ?";
        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            pstmt.setBoolean(1, productos.isEstado());
            pstmt.setString(2, productos.getNombre());
            pstmt.executeUpdate();
            System.out.println("Producto inhabilitado correctamente");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al inhabilitar el producto: " + e.getMessage());
        }
        return false;
    }

    public boolean habilitarProducto(Productos productos) {
        String sql = "UPDATE productos SET estado = ? WHERE nombre = ?";
        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            pstmt.setBoolean(1, productos.isEstado());
            pstmt.setString(2, productos.getNombre());
            pstmt.executeUpdate();
            System.out.println("Producto habilitado correctamente");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al habilitar el producto: " + e.getMessage());
        }
        return false;
    }

    public boolean editarProducto(Productos productos) {
        String sql = "UPDATE productos SET stock_actual = ?, precio = ?, id_categoria = ?, descripcion = ? WHERE nombre = ?";
        String sqlIdCategoria = "SELECT id_categoria FROM categoria WHERE nombre = ?";
        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sqlIdCategoria)) {
            pstmt.setString(1, productos.getCategoria());
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                productos.setIdCategoria(rs.getInt("id_categoria"));
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el ID de la categoria: " + e.getMessage());
        }
        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            pstmt.setInt(1, productos.getStockActual());
            pstmt.setFloat(2, productos.getPrecio());
            pstmt.setInt(3, productos.getIdCategoria());
            pstmt.setString(4, productos.getDescripcion());
            pstmt.setString(5, productos.getNombre());
            pstmt.executeUpdate();
            System.out.println("Producto editado correctamente");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al editar el producto: " + e.getMessage());
        }
        return false;
    }

    public boolean editarStockProducto(Productos productos) {
        String sql = "UPDATE productos SET stock_actual = ? WHERE nombre = ?";
        try (Connection conex = ConexionDB.getConexion(); PreparedStatement pstmt = conex.prepareStatement(sql)) {
            pstmt.setInt(1, productos.getStockActual());
            pstmt.setString(2, productos.getNombre());
            pstmt.executeUpdate();
            System.out.println("Stock del producto editado correctamente");
            return true;
        } catch (SQLException e) {
            System.out.println("Error al editar el stock del producto: " + e.getMessage());
        }
        return false;
    }
}
