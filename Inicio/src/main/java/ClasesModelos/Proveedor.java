package ClasesModelos;

public class Proveedor {
    private int idEmpresa;
    private String nombreEmpresa;
    private boolean estado;

    public Proveedor(String nombreEmpresa, boolean estado) {
        this.nombreEmpresa = nombreEmpresa;
        this.estado = estado;
    }

    public Proveedor() {
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public String getNombreEmpresa() {
        return nombreEmpresa;
    }

    public void setNombreEmpresa(String nombreEmpresa) {
        this.nombreEmpresa = nombreEmpresa;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
