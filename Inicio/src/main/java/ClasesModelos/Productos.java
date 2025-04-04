package ClasesModelos;

import BaseDeDatos.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Productos {
    private String nombre, descripcion, categoria;
    private int idCategoria, idProveedor, stockActual, stockMinimo, idProducto;
    private float precio, precioPromocional;
    private boolean estado;

    public Productos() {
    }

    public Productos(String nombre, String descripcion, int idCategoria, int idProveedor, int stockActual,
                     int stockMinimo, float precio, float precioPromocional, boolean estado) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.idProveedor = idProveedor;
        this.stockActual = stockActual;
        this.stockMinimo = stockMinimo;
        this.precio = precio;
        this.precioPromocional = precioPromocional;
        this.estado = estado;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdProveedor() {
        return idProveedor;
    }

    public void setIdProveedor(int idProveedor) {
        this.idProveedor = idProveedor;
    }

    public int getStockActual() {
        return stockActual;
    }

    public void setStockActual(int stockActual) {
        this.stockActual = stockActual;
    }

    public int getStockMinimo() {
        return stockMinimo;
    }

    public void setStockMinimo(int stockMinimo) {
        this.stockMinimo = stockMinimo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getPrecioPromocional() {
        return precioPromocional;
    }

    public void setPrecioPromocional(float precioPromocional) {
        this.precioPromocional = precioPromocional;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Productos{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", categoria='" + categoria + '\'' +
                ", idCategoria=" + idCategoria +
                ", idProveedor=" + idProveedor +
                ", stockActual=" + stockActual +
                ", stockMinimo=" + stockMinimo +
                ", idProducto=" + idProducto +
                ", precio=" + precio +
                ", precioPromocional=" + precioPromocional +
                ", estado=" + estado +
                '}';
    }
}
