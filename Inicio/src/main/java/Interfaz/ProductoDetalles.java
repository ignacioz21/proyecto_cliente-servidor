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
import ClasesModelos.Productos; 

class ProductoDetalles extends JFrame {
    private Productos producto; 
    private static ArrayList<Productos> carrito = new ArrayList<>();
    private JLabel quantityLabel;
    private int cantidad;

    public ProductoDetalles(Productos producto) {
        this.producto = producto;
        this.cantidad = 1; 
        InterfazDetalles();
    }

    private void InterfazDetalles() {
        setTitle("Detalles del Producto");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JLabel nameLabel = new JLabel("Nombre: " + producto.getNombre(), JLabel.CENTER);
        JLabel priceLabel = new JLabel("Precio: " + producto.getPrecio(), JLabel.CENTER);
        quantityLabel = new JLabel("Cantidad: " + cantidad, JLabel.CENTER);

        JButton changeQuantityButton = new JButton("Cambiar Cantidad");
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
        String nuevaCantidad = JOptionPane.showInputDialog(this, "Ingrese la cantidad que desea :", cantidad);
        try {
            int cantidadIngresada = Integer.parseInt(nuevaCantidad);
            if (cantidadIngresada > 0) {
                
                if (cantidadIngresada > producto.getStockActual()) { 
                    JOptionPane.showMessageDialog(this, "No hay suficiente stock. Solo hay " + producto.getStockActual() + " disponibles ");
                } else {
                    cantidad = cantidadIngresada;
                    quantityLabel.setText("Cantidad: " + cantidad);
                }
            } else {
                JOptionPane.showMessageDialog(this, " No tenemos esa cantidad ");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "No tenemos esa cantidad ");
        }
    }

    private void agregarAlCarrito() {
       
        Productos productoCarrito = new Productos(
            producto.getNombre(),
            producto.getDescripcion(),
            producto.getIdCategoria(),
            producto.getIdProveedor(),
            cantidad, 
            producto.getStockMinimo(),
            producto.getPrecio(),
            producto.getPrecioPromocional(),
            producto.isEstado()
        );
        carrito.add(productoCarrito);
        JOptionPane.showMessageDialog(this, "Producto agregado al carrito");
    }

    public static ArrayList<Productos> getCarrito() {
        return carrito;
    }

    public static void mostrarCarrito() {
        new CarritoCompra(carrito).setVisible(true);
    }
}