package com.mycompany.inicio;



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

public class Inicio extends JFrame {
    private JPanel panelPrincipal;
    private JMenuBar menuBar;
    private JMenu menuOpciones;
    private JMenuItem menuItemAdmin, menuItemCliente, menuItemEmpleado;
    private ImagenFondo panelFondo;

    public Inicio() {
        setTitle("Plataforma de Ventas en Linea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        iniciarInterfaz();
    }

    private void iniciarInterfaz() {
       
        panelFondo = new ImagenFondo("imagenes/imagenInicioE.jpeg");
        panelFondo.setLayout(new BorderLayout());

        
        JLabel jLabel1 = new JLabel("Bienvenido a la Plataforma de tienda en linea", SwingConstants.CENTER);
        jLabel1.setFont(new Font("Segoe UI Light", Font.BOLD, 36));
        jLabel1.setForeground(Color.WHITE);
        jLabel1.setOpaque(false);
        panelFondo.add(jLabel1, BorderLayout.NORTH);

       
        crearMenu();

       
        panelPrincipal = panelFondo;
        add(panelPrincipal);
    }

    private void crearMenu() {
        menuBar = new JMenuBar();
        menuOpciones = new JMenu("Opciones de Acceso");
        menuOpciones.setFont(new Font("Segoe UI", Font.BOLD, 16));

       
        menuItemAdmin = new JMenuItem("Administrador");
        menuItemAdmin.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        menuItemAdmin.addActionListener(this::inicioAdminActionPerformed);

        menuItemCliente = new JMenuItem("Cliente");
        menuItemCliente.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        menuItemCliente.addActionListener(this::clienteInicioActionPerformed);

        menuItemEmpleado = new JMenuItem("Proveedor/Empleado");
        menuItemEmpleado.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        menuItemEmpleado.addActionListener(this::empleadoInicioActionPerformed);

        menuOpciones.add(menuItemAdmin);
        menuOpciones.add(menuItemCliente);
        menuOpciones.add(menuItemEmpleado);

        menuBar.add(menuOpciones);
        setJMenuBar(menuBar);
    }

    private void inicioAdminActionPerformed(ActionEvent evt) {
        new loginAdministrador().setVisible(true);
        this.setVisible(false);
    }

    private void clienteInicioActionPerformed(ActionEvent evt) {
        new loginCliente().setVisible(true);
        this.setVisible(false);
    }

    private void empleadoInicioActionPerformed(ActionEvent evt) {
        new loginEmpleado().setVisible(true);
        this.setVisible(false);
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Inicio inicio = new Inicio();
            inicio.setVisible(true);
        });
    }
}