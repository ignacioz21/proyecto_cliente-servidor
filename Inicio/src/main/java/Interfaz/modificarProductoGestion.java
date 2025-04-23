/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import ClasesModelos.Categoria;
import ClasesModelos.Productos;
import Cliente.ClienteController;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 *
 * @author XPC
 */
class modificarProductoGestion extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtCantidad, txtPrecio;
    private JTextArea txtDescripcion;
    private JComboBox<String> cmbCategoria;
    private JButton btnGuardar, btnCancelar;
    private String nombreProducto;

    public modificarProductoGestion(String nombreProducto) {
        this.nombreProducto = nombreProducto;
        setTitle("Modificar Producto");
        setSize(600, 500);
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
        JLabel lblTitulo = new JLabel("Modificar: " + nombreProducto, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI Light", Font.BOLD, 24));
        lblTitulo.setForeground(Color.WHITE);
        panelTitulo.add(lblTitulo);
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);

        // Panel central
        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(null);
        panelCentral.setBackground(new Color(220, 220, 220));

        // Producto (no editable)
        JLabel lblNombre = new JLabel("Producto:");
        lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNombre.setBounds(100, 40, 120, 30);
        panelCentral.add(lblNombre);

        JTextField txtNombre = new JTextField(nombreProducto);
        txtNombre.setEditable(false);
        txtNombre.setBounds(230, 40, 250, 30);
        panelCentral.add(txtNombre);

        // Campos para la edición
        JLabel lblCantidad = new JLabel("Nueva cantidad:");
        lblCantidad.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCantidad.setBounds(100, 90, 120, 30);
        panelCentral.add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(230, 90, 250, 30);
        panelCentral.add(txtCantidad);

        JLabel lblPrecio = new JLabel("Nuevo precio:");
        lblPrecio.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblPrecio.setBounds(100, 140, 120, 30);
        panelCentral.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(230, 140, 250, 30);
        panelCentral.add(txtPrecio);

        JLabel lblCategoria = new JLabel("Nueva categoría:");
        lblCategoria.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCategoria.setBounds(100, 190, 120, 30);
        panelCentral.add(lblCategoria);

        ClienteController clienteController = new ClienteController();
        List<Categoria> categorias = clienteController.obtenerCategorias();
        cmbCategoria = new JComboBox<>(categorias.stream().map(Categoria::getNombre).toArray(String[]::new));
        cmbCategoria.setBounds(230, 190, 250, 30);
        panelCentral.add(cmbCategoria);

        JLabel lblDescripcion = new JLabel("Nueva descripción:");
        lblDescripcion.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblDescripcion.setBounds(100, 240, 150, 30);
        panelCentral.add(lblDescripcion);

        txtDescripcion = new JTextArea();
        txtDescripcion.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(txtDescripcion);
        scrollPane.setBounds(100, 280, 380, 80);
        panelCentral.add(scrollPane);

        btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnGuardar.setBounds(150, 380, 180, 40);
        btnGuardar.setBackground(new Color(0, 51, 204));
        btnGuardar.setForeground(Color.WHITE);
        btnGuardar.addActionListener(e -> {
            ClienteController cc = new ClienteController();
            String nombre = txtNombre.getText().trim();
            String cantidad = txtCantidad.getText().trim();
            String precio = txtPrecio.getText().trim();
            String categoria = (String) cmbCategoria.getSelectedItem();
            String descripcion = txtDescripcion.getText().trim();
            if (nombre.isEmpty() || cantidad.isEmpty() || precio.isEmpty() || categoria.isEmpty() || descripcion.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                Productos productoModif = new Productos();
                productoModif.setNombre(nombre);
                productoModif.setStockActual(Integer.parseInt(cantidad));
                productoModif.setPrecio(Float.parseFloat(precio));
                productoModif.setCategoria(categoria);
                productoModif.setDescripcion(descripcion);
                if (cc.editarProducto(productoModif)) {
                    JOptionPane.showMessageDialog(this, "Producto modificado correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Cerrar esta ventana
                } else {
                    JOptionPane.showMessageDialog(this, "Error al modificar el producto.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        panelCentral.add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnCancelar.setBounds(340, 380, 130, 40);
        btnCancelar.setBackground(new Color(0, 51, 204));
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.addActionListener(e -> dispose());
        panelCentral.add(btnCancelar);

        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        // Panel lateral derecho
        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(new Color(30, 144, 255));
        panelDerecho.setPreferredSize(new Dimension(100, 0));
        panelPrincipal.add(panelDerecho, BorderLayout.EAST);

        add(panelPrincipal);
    }
    
}