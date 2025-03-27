package ClasesModelos;

public class Promociones {
    private String nombrePromociones, descripcion, tipo, fechaInicio, fechaFin, aplicaciones;
    private float valorDescuento;

    public Promociones(String nombrePromociones, String descripcion, String tipo, String fechaInicio,
                       String fechaFin, String aplicaciones, float valorDescuento) {
        this.nombrePromociones = nombrePromociones;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.aplicaciones = aplicaciones;
        this.valorDescuento = valorDescuento;
    }

    public String getNombrePromociones() {
        return nombrePromociones;
    }

    public void setNombrePromociones(String nombrePromociones) {
        this.nombrePromociones = nombrePromociones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getAplicaciones() {
        return aplicaciones;
    }

    public void setAplicaciones(String aplicaciones) {
        this.aplicaciones = aplicaciones;
    }

    public float getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(float valorDescuento) {
        this.valorDescuento = valorDescuento;
    }
}
