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
import java.sql.Connection;
import java.sql.SQLException;
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
        interfazEliminarProducto();
    }

    private void interfazEliminarProducto() {
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(220, 220, 220));

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(255, 204, 0));
        JLabel lblTitulo = new JLabel("Eliminar Producto", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI Light", Font.BOLD, 24));
        lblTitulo.setForeground(Color.BLACK);
        panelTitulo.add(lblTitulo);
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);

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
        btnBuscar.setBackground(new Color(255, 204, 0));
        btnBuscar.setForeground(Color.BLACK);
        btnBuscar.addActionListener(e -> eliminarProducto());
        panelCentral.add(btnBuscar);

        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(new Color(255, 215, 0));
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
            if (clienteController.buscarProducto(productos)) {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "¿Esta seguro de que desea eliminar el producto " + nombreProducto + "?", "Confirmar eliminacion", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {

                    if (eliminarProductoDeBD(productos)) {
                        JOptionPane.showMessageDialog(this,
                                "Producto eliminado correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(this,
                                "Error al eliminar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Producto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Por favor, ingrese el nombre del producto.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean eliminarProductoDeBD(Productos productos) {
        ClienteController clienteController = new ClienteController();
        productos.setEstado(false);
        return clienteController.inhabilitarProducto(productos);
    }
}
