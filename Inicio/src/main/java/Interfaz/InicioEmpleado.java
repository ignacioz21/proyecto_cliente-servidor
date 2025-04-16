/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

/**
 *
 * @author XPC
 */
import Interfaz.loginAdministrador;
import Interfaz.loginCliente;
import Interfaz.loginEmpleado;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class InicioEmpleado extends JFrame {
    private JPanel panelPrincipal;
    private JMenuBar menuBar;
    private JMenu menuOpciones;
    private JMenuItem menuItemNuevoProducto, menuItemGestionInventario;
    private ImagenFondo panelFondo;

    public InicioEmpleado() {
        setTitle("Plataforma de Ventas en Linea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        iniciarInterfaz();
    }

    private void iniciarInterfaz() {
        panelFondo = new ImagenFondo("imagenes/fondoEmpleado.jpeg");
        panelFondo.setLayout(new BorderLayout());

       
        JLabel jLabel1 = new JLabel("Bienvenido Proveedor/Empleado", SwingConstants.CENTER);
        jLabel1.setFont(new Font("Segoe UI Light", Font.BOLD, 36));
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setOpaque(false);
        panelFondo.add(jLabel1, BorderLayout.NORTH);

        menuBar = new JMenuBar();
        menuOpciones = new JMenu("Opciones");
        menuOpciones.setFont(new Font("Segoe UI", Font.BOLD, 16));

       
        menuItemNuevoProducto = new JMenuItem("Registrar Nuevo Producto");
        menuItemNuevoProducto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        menuItemNuevoProducto.addActionListener(this::btnNuevoProductoActionPerformed);
        menuOpciones.add(menuItemNuevoProducto);

        
        menuItemGestionInventario = new JMenuItem("Gestión del Inventario");
        menuItemGestionInventario.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        menuItemGestionInventario.addActionListener(this::btnGestionInventarioActionPerformed);
        menuOpciones.add(menuItemGestionInventario);

        menuBar.add(menuOpciones);
        setJMenuBar(menuBar);

       
        panelPrincipal = panelFondo;
        add(panelPrincipal);
    }

    private void btnNuevoProductoActionPerformed(ActionEvent evt) {
      
        nuevoProducto nuevoProd = new nuevoProducto();
           nuevoProd.setVisible(true);
       
    }

    private void btnGestionInventarioActionPerformed(ActionEvent evt) {
      
        gestionInventario gestionInv = new gestionInventario();
        gestionInv.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InicioEmpleado inicio = new  InicioEmpleado();
            inicio.setVisible(true);
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