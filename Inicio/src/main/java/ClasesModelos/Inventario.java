package ClasesModelos;

public class Inventario {
    private int idProducto, cantidad, idEmpleado;
    private String fechaActualizacion, tipoMovimiento;

    public Inventario(int idProducto, int cantidad, int idEmpleado, String fechaActualizacion, String tipoMovimiento) {
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.idEmpleado = idEmpleado;
        this.fechaActualizacion = fechaActualizacion;
        this.tipoMovimiento = tipoMovimiento;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        this.idEmpleado = idEmpleado;
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
}
