/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import ClasesModelos.Productos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class CarritoCompra extends JFrame {

    private ArrayList<Productos> carrito;

    public CarritoCompra(ArrayList<Productos> carrito) {
        this.carrito = carrito;

        setTitle("Carrito de Compras");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        String[] columnNames = {"Producto", "Precio", "Cantidad", "Subtotal"};

        Object[][] data = new Object[carrito.size()][4];
        double subtotal = 0;
        double total = 0;
        for (int i = 0; i < carrito.size(); i++) {
            Productos producto = carrito.get(i);
            data[i][0] = producto.getNombre();
            data[i][1] = String.format("$%.2f", producto.getPrecio());
            data[i][2] = producto.getStockActual();
            subtotal = producto.getPrecio() * producto.getStockActual();
            data[i][3] = String.format("$%.2f", subtotal);
            total += subtotal;
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        JLabel totalLabel = new JLabel("Total: $" + String.format("%.2f", total));
        panel.add(totalLabel, BorderLayout.SOUTH);

        JButton checkoutButton = new JButton("Finalizar Compra");
        double finalTotal = total;
        checkoutButton.addActionListener((ActionEvent e) -> {
                        new ResumenCompra(carrito, finalTotal).setVisible(true);
                        dispose();
            carrito.clear();
            dispose();
        });

        panel.add(checkoutButton, BorderLayout.NORTH);
        add(panel);
    }
}
