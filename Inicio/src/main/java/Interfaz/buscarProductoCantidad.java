/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

/**
 *
 * @author XPC
 */
import ClasesModelos.Productos;
import Cliente.ClienteController;
import javax.swing.*;
import java.awt.*;
import javax.swing.*;
import java.awt.*;

class buscarProductoCantidad extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtNombreProducto;
    private JButton btnBuscar, btnCancelar;

    public buscarProductoCantidad() {
        setTitle("Buscar Producto para modificar Cantidad");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setResizable(false);
       interfazCantidad();
    }

    private void interfazCantidad() {
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(220, 220, 220));

       
        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(128, 0, 128));
        JLabel lblTitulo = new JLabel("Buscar Producto para cambio de Cantidad", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI Light", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
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

        btnBuscar = new JButton("Buscar");
        btnBuscar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnBuscar.setBounds(200, 150, 130, 40);
        btnBuscar.setBackground(new Color(128, 0, 128)); 
        btnBuscar.setForeground(Color.WHITE);
        btnBuscar.addActionListener(e -> {
            String nombreProducto = txtNombreProducto.getText().trim();
            if (!nombreProducto.isEmpty()) {
                ClienteController clienteController = new ClienteController();
                Productos productos = new Productos();
                productos.setNombre(nombreProducto);
                if (clienteController.buscarProducto(productos)){
                    pedirNuevaCantidad(productos.getNombre());
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Producto no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this,
                        "Por favor, ingrese el nombre del producto.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panelCentral.add(btnBuscar);


        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    private void pedirNuevaCantidad(String nombreProducto) {
        String nuevaCantidadStr = JOptionPane.showInputDialog(this, 
                "Ingrese la nueva cantidad para " + nombreProducto + ":");
        
        if (nuevaCantidadStr != null && !nuevaCantidadStr.trim().isEmpty()) {
            try {
                int nuevaCantidad = Integer.parseInt(nuevaCantidadStr);
                Productos productos = new Productos();
                productos.setNombre(nombreProducto);
                productos.setStockActual(nuevaCantidad);
                ClienteController clienteController = new ClienteController();
                clienteController.actualizarStockProducto(productos);
                JOptionPane.showMessageDialog(this,
                        "Cantidad actualizada correctamente.", "Exito", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, 
                        "Por favor, ingrese un número valido.",  "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, 
                    "La cantidad no puede estar vacia.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}