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
import java.awt.event.ActionEvent;

public class nuevoProducto extends JFrame {
    private JTextField txtNombre;
    private JTextField txtCategoria;
    private JTextArea txtDescripcion;
    private JTextField txtCantidad;
    private JTextField txtPrecio;
    private JButton btnGuardar;

    public nuevoProducto() {
        setTitle("Registro de Nuevo Producto");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        interfazRegistro();
    }

    private void interfazRegistro() {
        nuevoProductoImagen fondo = new nuevoProductoImagen("imagenes/apple.jpeg");
        fondo.setLayout(null);
        setContentPane(fondo);

        JLabel lblTitulo = new JLabel("REGISTRO DE NUEVO PRODUCTO");
        lblTitulo.setBounds(150, 30, 500, 40);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lblTitulo.setForeground(Color.WHITE);
        fondo.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(200, 100, 150, 30);
        lblNombre.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblNombre.setForeground(Color.WHITE);
        fondo.add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(360, 100, 250, 30);
        fondo.add(txtNombre);

        JLabel lblCategoria = new JLabel("Categoria:");
        lblCategoria.setBounds(200, 150, 150, 30);
        lblCategoria.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblCategoria.setForeground(Color.WHITE);
        fondo.add(lblCategoria);

        txtCategoria = new JTextField();
        txtCategoria.setBounds(360, 150, 250, 30);
        fondo.add(txtCategoria);

        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setBounds(200, 200, 150, 30);
        lblDescripcion.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblDescripcion.setForeground(Color.WHITE);
        fondo.add(lblDescripcion);

        txtDescripcion = new JTextArea();
        JScrollPane scrollDescripcion = new JScrollPane(txtDescripcion);
        scrollDescripcion.setBounds(360, 200, 250, 60);
        fondo.add(scrollDescripcion);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(200, 280, 150, 30);
        lblCantidad.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblCantidad.setForeground(Color.WHITE);
        fondo.add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(360, 280, 250, 30);
        fondo.add(txtCantidad);

        JLabel lblPrecio = new JLabel("Precio:");
        lblPrecio.setBounds(200, 320, 150, 30);
        lblPrecio.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPrecio.setForeground(Color.WHITE);
        fondo.add(lblPrecio);

        txtPrecio = new JTextField();
        txtPrecio.setBounds(360, 320, 250, 30);
        fondo.add(txtPrecio);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnGuardar.setBounds(300, 400, 180, 40);
        fondo.add(btnGuardar);

        btnGuardar.addActionListener(this::guardarProducto);
    }
    public class nuevoProductoImagen extends JPanel {
    private Image imagen;

    public nuevoProductoImagen(String rutaImagen) {
        ImageIcon icon = new ImageIcon(rutaImagen);
        imagen = icon.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }
}

    private void guardarProducto(ActionEvent evt) {
        // Aqui iria el guardado del producto en la base de datos 
       
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new nuevoProducto().setVisible(true));
    }
}