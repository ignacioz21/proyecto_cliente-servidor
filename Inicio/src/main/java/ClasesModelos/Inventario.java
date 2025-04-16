package ClasesModelos;

import java.io.Serializable;

public class Inventario implements Serializable {
    private int id_inventario, cantidad, id_empleado, id_producto;
    private String fechaActualizacion, tipoMovimiento;

    public Inventario(int id_inventario, int cantidad, String fechaActualizacion, String tipoMovimiento) {
        this.id_inventario = id_inventario;
        this.cantidad = cantidad;
        this.fechaActualizacion = fechaActualizacion;
        this.tipoMovimiento = tipoMovimiento;
    }

    public Inventario(int id_inventario, int cantidad, int id_empleado, int id_producto, String fechaActualizacion, String tipoMovimiento) {
        this.id_inventario = id_inventario;
        this.cantidad = cantidad;
        this.id_empleado = id_empleado;
        this.id_producto = id_producto;
        this.fechaActualizacion = fechaActualizacion;
        this.tipoMovimiento = tipoMovimiento;
    }

    public Inventario() {
    }

    public int getId_inventario() {
        return id_inventario;
    }

    public void setId_inventario(int id_inventario) {
        this.id_inventario = id_inventario;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(String fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
}
