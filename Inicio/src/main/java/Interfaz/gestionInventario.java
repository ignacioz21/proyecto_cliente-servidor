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


public class gestionInventario extends JFrame {
    private JPanel panelPrincipal;
    private JMenuBar menuBar;
    private JMenu menuOpciones;
    private JMenuItem menuItemModificarProducto, menuItemEliminarProducto;
    private ImagenFondo panelFondo;

    public gestionInventario() {
        setTitle("Gestión de Inventario");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        iniciarInterfaz();
    }

    private void iniciarInterfaz() {
       
        panelFondo = new ImagenFondo("imagenes/almacen.jpeg"); 
        panelFondo.setLayout(new BorderLayout());

        JLabel jLabel1 = new JLabel("Gestion de Inventario", SwingConstants.CENTER);
        jLabel1.setFont(new Font("Segoe UI Light", Font.BOLD, 36));
        jLabel1.setForeground(Color.BLACK);
        panelFondo.add(jLabel1, BorderLayout.NORTH);

      
        menuBar = new JMenuBar();
       

        menuOpciones = new JMenu("Opciones");
        menuOpciones.setFont(new Font("Segoe UI", Font.BOLD, 16));
        menuOpciones.setForeground(Color.WHITE); 

        menuItemModificarProducto = new JMenuItem("Modificar Producto");
        menuItemModificarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        menuItemModificarProducto.addActionListener(this::btnModificarProductoActionPerformed);
        menuOpciones.add(menuItemModificarProducto);

        menuItemEliminarProducto = new JMenuItem("Eliminar Producto");
        menuItemEliminarProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        menuItemEliminarProducto.addActionListener(this::btnEliminarProductoActionPerformed);
        menuOpciones.add(menuItemEliminarProducto);

        menuBar.add(menuOpciones);
        setJMenuBar(menuBar);

        add(panelFondo);
    }

    private void btnModificarProductoActionPerformed(ActionEvent evt) {
      BuscarProductoGestion buscarFrame = new BuscarProductoGestion();
        buscarFrame.setVisible(true);
    }

    private void btnEliminarProductoActionPerformed(ActionEvent evt) {
   eliminarProductoGestion eliminarFrame = new eliminarProductoGestion();
        eliminarFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            gestionInventario gestionInv = new gestionInventario();
            gestionInv.setVisible(true);
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