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
    private JMenuItem  menuItemControlInventarios, menuItemRestablecerProducto;
    private ImagenFondo panelFondo;

    public inicioAdmin() {
        setTitle("Plataforma de Ventas en Lniea - Administrador");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        iniciarInterfaz();
    }

    private void iniciarInterfaz() {
        panelFondo = new ImagenFondo("imagenes/adminnn.jpeg");
        panelFondo.setLayout(new BorderLayout());

        JLabel jLabel1 = new JLabel("Bienvenido Administrador", SwingConstants.CENTER);
        jLabel1.setFont(new Font("Segoe UI Light", Font.BOLD, 36));
        jLabel1.setForeground(Color.WHITE);
        panelFondo.add(jLabel1, BorderLayout.NORTH);

        menuBar = new JMenuBar();
        menuOpciones = new JMenu("Opciones");
        menuOpciones.setFont(new Font("Segoe UI", Font.BOLD, 16));




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


  private void btnControlInventariosActionPerformed(ActionEvent evt) {
       
        new controlInventario().setVisible(true);
        this.dispose(); 
    }

    private void btnRestablecerProductoActionPerformed(ActionEvent evt) {
        SwingUtilities.invokeLater(() -> {
            buscarProductoCantidad admin = new  buscarProductoCantidad();
            admin.setVisible(true);
        });
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
