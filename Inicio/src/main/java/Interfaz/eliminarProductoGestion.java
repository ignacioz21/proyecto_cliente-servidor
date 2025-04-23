/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import ClasesModelos.Productos;
import Cliente.ClienteController;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author XPC
 */
import javax.swing.*;
import java.awt.*;

class eliminarProductoGestion extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtNombreProducto;
    private JButton btnBuscar, btnCancelar;

    public eliminarProductoGestion() {
        setTitle("Eliminar Producto");
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
        panelTitulo.setBackground(new Color(255, 204, 0)); // Color amarillo
        JLabel lblTitulo = new JLabel("Eliminar Producto", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI Light", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK);
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

        btnBuscar = new JButton("Eliminar");
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnBuscar.setBounds(200, 150, 130, 40);
        btnBuscar.setBackground(new Color(255, 204, 0)); // Color amarillo
        btnBuscar.setForeground(Color.BLACK);
        btnBuscar.addActionListener(e -> eliminarProducto());
        panelCentral.add(btnBuscar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCancelar.setBounds(350, 150, 130, 40);
        btnCancelar.setBackground(new Color(220, 220, 220));
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.addActionListener(e -> dispose());
        panelCentral.add(btnCancelar);

        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        // Panel lateral derecho
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(new Color(255, 215, 0)); // Amarillo dorado
        panelDerecho.setPreferredSize(new Dimension(100, 0));
        panelPrincipal.add(panelDerecho, BorderLayout.EAST);

        add(panelPrincipal);
    }

    private void eliminarProducto() {
        String nombreProducto = txtNombreProducto.getText().trim();
        if (!nombreProducto.isEmpty()) {
            ClienteController clienteController = new ClienteController();
            Productos productos = new Productos();
            productos.setNombre(nombreProducto);
            if (clienteController.buscarProducto(productos)){
                int confirm = JOptionPane.showConfirmDialog(this,
                        "¿Está seguro de que desea eliminar el producto " + nombreProducto + "?",
                        "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    // Lógica para eliminar el producto de la base de datos
                    if (eliminarProductoDeBD(productos)) {
                        JOptionPane.showMessageDialog(this,
                                "Producto eliminado correctamente.",
                                "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        dispose(); // Cierra la ventana actual
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Error al eliminar el producto.",
                                "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
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
    }

    private boolean eliminarProductoDeBD(Productos productos) {
        ClienteController clienteController = new ClienteController();
        productos.setEstado(false);
        return clienteController.inhabilitarProducto(productos);
    }
}