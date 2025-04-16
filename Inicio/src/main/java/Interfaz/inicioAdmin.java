/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

/**
 *
 * @author XPC
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class inicioAdmin extends JFrame {
    private JPanel panelPrincipal;
    private JMenuBar menuBar;
    private JMenu menuOpciones;
    private JMenuItem menuItemReporteVentas, menuItemControlInventarios, menuItemRestablecerProducto;
    private ImagenFondo panelFondo;

    public inicioAdmin() {
        setTitle("Plataforma de Ventas en Línea - Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        iniciarInterfaz();
    }

    private void iniciarInterfaz() {
        panelFondo = new ImagenFondo("imagenes/admin.jpeg"); 
        panelFondo.setLayout(new BorderLayout());

        JLabel jLabel1 = new JLabel("Bienvenido Administrador", SwingConstants.CENTER);
        jLabel1.setFont(new Font("Segoe UI Light", Font.BOLD, 36));
        jLabel1.setForeground(Color.WHITE);
        panelFondo.add(jLabel1, BorderLayout.NORTH);

        menuBar = new JMenuBar();
        menuOpciones = new JMenu("Opciones");
        menuOpciones.setFont(new Font("Segoe UI", Font.BOLD, 16));

      
        menuItemReporteVentas = new JMenuItem("Reporte de Ventas");
        menuItemReporteVentas.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        menuItemReporteVentas.addActionListener(this::btnReporteVentasActionPerformed);
        menuOpciones.add(menuItemReporteVentas);

       
        menuItemControlInventarios = new JMenuItem("Control de Inventarios");
        menuItemControlInventarios.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        menuItemControlInventarios.addActionListener(this::btnControlInventariosActionPerformed);
        menuOpciones.add(menuItemControlInventarios);

      
        menuItemRestablecerProducto = new JMenuItem("Restablecer Producto");
        menuItemRestablecerProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        menuItemRestablecerProducto.addActionListener(this::btnRestablecerProductoActionPerformed);
        menuOpciones.add(menuItemRestablecerProducto);

        menuBar.add(menuOpciones);
        setJMenuBar(menuBar);

        panelPrincipal = panelFondo;
        add(panelPrincipal);
    }

    private void btnReporteVentasActionPerformed(ActionEvent evt) {
    // Aquí se puede poner el reporte de ventas, le voy a poner un ejemplo de como lo hicimos en el examen 
    JOptionPane.showMessageDialog(this, "Funcionalidad de reporte de ventas aún no implementada.");
    
    // private void generarReporte() {
    //     int valorCostoTotal = 0;
    //     int valorVentaTotal = 0;
    //     int valorGananciaTotal = 0;

    //     StringBuilder electronicos = new StringBuilder();
    //     StringBuilder ropa = new StringBuilder();

    //     for (Producto producto : listaProductos) {
    //         int precioVenta = (int) producto.getPrecioVenta();
    //         int precioCosto = (int) producto.precioCosto;
    //         int ganancia = precioVenta - precioCosto;

    //         valorCostoTotal += precioCosto;
    //         valorVentaTotal += precioVenta;
    //         valorGananciaTotal += ganancia;

    //         String productoDetalle = "- [" + producto.getCodigo() + "] [" + producto.getNombre() + "] [" + producto.getCondicion() + "] [" + precioVenta + "]";

    //         if (producto instanceof Electronico) {
    //             electronicos.append(productoDetalle).append("\n");
    //         } else if (producto instanceof Ropa) {
    //             ropa.append(productoDetalle).append("\n");
    //         }
    //     }

    //     String escribirFichero = "*** Reporte de Productos ***" +
    //             "\nCosto Total: [" + valorCostoTotal + "]" +
    //             "\nPrecio de Venta Total: [" + valorVentaTotal + "]" +
    //             "\nGanancia Total: [" + valorGananciaTotal + "]" +
    //             "\nProductos Electronicos:\n" + electronicos.toString() +
    //             "\nProductos Ropa:\n" + ropa.toString();

    //     escribirTexto("reporte.txt", escribirFichero);
    // }

    // public static void escribirTexto(String nombreArchivo, String contenido) {
    //     try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
    //         writer.write(contenido);
    //         writer.newLine();
    //         writer.write("==========================================");
    //         JOptionPane.showMessageDialog(null, "Archivo escrito correctamente.");
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     }
    // }
}

  private void btnControlInventariosActionPerformed(ActionEvent evt) {
       
        new controlInventario().setVisible(true);
        this.dispose(); 
    }

    private void btnRestablecerProductoActionPerformed(ActionEvent evt) {
        //Aqui le puede poner un JOptionPane que le pida el nombre como los otros y pedirle con otro JOption Pane la nueva cantidad
        JOptionPane.showMessageDialog(this, "Funcionalidad de restablecer producto aún no implementada.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
           inicioAdmin admin = new inicioAdmin();
            admin.setVisible(true);
        });
    }

    class ImagenFondo extends JPanel {
        private Image imagen;

        public ImagenFondo(String rutaImagen) {
            ImageIcon icon = new ImageIcon(rutaImagen);
            imagen = icon.getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (imagen != null) {
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}