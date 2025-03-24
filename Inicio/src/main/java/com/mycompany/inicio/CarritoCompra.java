/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.inicio;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author XPC
 */
class CarritoCompra extends JFrame {
    public CarritoCompra(ArrayList<Producto> carrito) {
        setTitle("Carrito de Compras");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        String[] columnNames = {"Producto", "Precio", "Cantidad", "Subtotal"};
        Object[][] data = new Object[ProductoDetalles.getCarrito().size()][4];

        for (int i = 0; i < ProductoDetalles.getCarrito().size(); i++) {
            Producto producto = ProductoDetalles.getCarrito().get(i);
            data[i][0] = producto.getNombre();
            data[i][1] = producto.getPrecio();
            data[i][2] = producto.getCantidad();
            data[i][3] = producto.getPrecio() * producto.getCantidad();
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JButton checkoutButton = new JButton("Finalizar Compra");
        checkoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Compra finalizada con éxito!");
                ProductoDetalles.getCarrito().clear();
                dispose();
            }
        });

        panel.add(checkoutButton, BorderLayout.SOUTH);
        add(panel);
    }
}

