/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.inicio;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class InicioCompras extends JFrame {
    private JPanel panelPrincipal;
    private JButton btnCarrito;
    private JButton[] productButtons;
    private JButton[] verDetallesBtns;
    private static ArrayList<Producto> carrito = new ArrayList<>();

    public InicioCompras() {
        setTitle("Carrito de Compras");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        panelPrincipal = new JPanel(new BorderLayout());

        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnCarrito = new JButton("Carrito");
        btnCarrito.setFont(new Font("Segoe UI", Font.BOLD, 20));
        btnCarrito.addActionListener(evt -> new CarritoCompra(carrito).setVisible(true));
        panelSuperior.add(btnCarrito);
        panelPrincipal.add(panelSuperior, BorderLayout.NORTH);

        JPanel panelProductos = new JPanel(new GridLayout(2, 3, 10, 10));
        panelProductos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        productButtons = new JButton[5];
        verDetallesBtns = new JButton[5];

        for (int i = 0; i < 5; i++) {
            final int productoIndex = i;
            Producto producto = new Producto("Producto " + (i + 1), 100.0 + (i * 50), 10);
            
            JPanel panelProducto = new JPanel(new BorderLayout());
            panelProducto.setBackground(Color.LIGHT_GRAY);

            JLabel tituloProducto = new JLabel(producto.getNombre(), SwingConstants.CENTER);
            panelProducto.add(tituloProducto, BorderLayout.NORTH);

            productButtons[i] = new JButton("imagenes/inicioLogin.jpeg");
            productButtons[i].addActionListener(evt -> agregarAlCarrito(producto));

            JPanel panelImagen = new JPanel(new BorderLayout());
            panelImagen.setBackground(Color.WHITE);
            panelImagen.add(productButtons[i], BorderLayout.CENTER);
            panelProducto.add(panelImagen, BorderLayout.CENTER);

            verDetallesBtns[i] = new JButton("Ver detalles");
            verDetallesBtns[i].addActionListener(evt -> new ProductoDetalles(producto).setVisible(true));

            JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.CENTER));
            panelBoton.add(verDetallesBtns[i]);
            panelProducto.add(panelBoton, BorderLayout.SOUTH);

            panelProductos.add(panelProducto);
        }

        panelPrincipal.add(panelProductos, BorderLayout.CENTER);
        add(panelPrincipal);
    }

    private void agregarAlCarrito(Producto producto) {
        carrito.add(producto);
        JOptionPane.showMessageDialog(this, producto.getNombre() + " agregado al carrito.");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InicioCompras().setVisible(true));
    }
}