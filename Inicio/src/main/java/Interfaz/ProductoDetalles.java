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

import java.util.ArrayList;

class ProductoDetalles extends JFrame {
    private Producto producto;
    private static ArrayList<Producto> carrito = new ArrayList<>();
    private JLabel quantityLabel;
    private int cantidad;

    public ProductoDetalles(Producto producto) {
        this.producto = producto;
        this.cantidad = producto.getCantidad();
        initComponents();
    }

    private void initComponents() {
        setTitle("Detalles del Producto");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel("Nombre: " + producto.getNombre(), JLabel.CENTER);
        JLabel priceLabel = new JLabel("Precio: $" + producto.getPrecio(), JLabel.CENTER);
        quantityLabel = new JLabel("Cantidad: " + cantidad, JLabel.CENTER);

        JButton changeQuantityButton = new JButton("Cantidad");
        changeQuantityButton.addActionListener(e -> cambiarCantidad());

        JButton addToCartButton = new JButton("Agregar al Carrito");
        addToCartButton.addActionListener(e -> agregarAlCarrito());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(changeQuantityButton);
        buttonPanel.add(addToCartButton);

        panel.add(nameLabel, BorderLayout.NORTH);
        panel.add(priceLabel, BorderLayout.CENTER);
        panel.add(quantityLabel, BorderLayout.SOUTH);
        panel.add(buttonPanel, BorderLayout.PAGE_END);

        add(panel);
    }

    private void cambiarCantidad() {
        String nuevaCantidad = JOptionPane.showInputDialog(this, "Ingrese nueva cantidad:", cantidad);
        try {
            int cantidadIngresada = Integer.parseInt(nuevaCantidad);
            if (cantidadIngresada > 0) {
                // Verificar si la cantidad ingresada es mayor que la cantidad disponible
                if (cantidadIngresada > producto.getCantidad()) {
                    JOptionPane.showMessageDialog(this, "No hay suficiente stock. Solo hay " + producto.getCantidad() + " disponibles.", "Stock Insuficiente", JOptionPane.WARNING_MESSAGE);
                } else {
                    cantidad = cantidadIngresada;
                    quantityLabel.setText("Cantidad: " + cantidad);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingrese un número válido.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Ingrese un número válido.");
        }
    }

    private void agregarAlCarrito() {
        producto.setCantidad(cantidad);
        carrito.add(producto);
        JOptionPane.showMessageDialog(this, "Producto agregado al carrito");
    }

    public static ArrayList<Producto> getCarrito() {
        return carrito;
    }

    public static void mostrarCarrito() {
        new CarritoCompra(carrito).setVisible(true);
    }
}