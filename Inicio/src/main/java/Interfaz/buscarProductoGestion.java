/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import ClasesModelos.Productos;
import Cliente.ClienteController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author XPC
 */
class BuscarProductoGestion extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtNombreProducto;
    private JButton btnBuscar, btnCancelar;

    public BuscarProductoGestion() {
        setTitle("Buscar Producto");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
        initComponents();
    }

    private void initComponents() {
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(220, 220, 220));

        // Panel de título
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(0, 51, 204));
        JLabel lblTitulo = new JLabel("Buscar Producto", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI Light", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);

        // Panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(null);
        panelCentral.setBackground(new Color(220, 220, 220));

        JLabel lblNombre = new JLabel("Nombre del producto:");
        lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNombre.setBounds(100, 80, 180, 30);
        panelCentral.add(lblNombre);

        txtNombreProducto = new JTextField();
        txtNombreProducto.setBounds(280, 80, 250, 30);
        panelCentral.add(txtNombreProducto);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnBuscar.setBounds(200, 150, 130, 40);
        btnBuscar.setBackground(new Color(0, 51, 204));
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.addActionListener(e -> {
            String nombreProducto = txtNombreProducto.getText().trim();
            if (!nombreProducto.isEmpty()) {
                ClienteController clienteController = new ClienteController();
                Productos productos = new Productos();
                productos.setNombre(nombreProducto);
                if (clienteController.buscarProducto(productos)){
                    modificarProductoGestion modificarFrame = new modificarProductoGestion(nombreProducto);
                    modificarFrame.setVisible(true);
                    dispose(); // Cierra la ventana actual
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Producto no encontrado.",
                        "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, 
                    "Por favor, ingrese el nombre del producto.", 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelCentral.add(btnBuscar);


        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        // Panel lateral derecho
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(new Color(30, 144, 255));
        panelDerecho.setPreferredSize(new Dimension(100, 0));
        panelPrincipal.add(panelDerecho, BorderLayout.EAST);

        add(panelPrincipal);
    }
}