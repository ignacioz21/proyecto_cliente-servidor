/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

/**
 *
 * @author XPC
 */
import ClasesModelos.Empleado;
import ClasesModelos.Usuario;
import Cliente.ClienteController;
import Controller.EmpleadoController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.JPasswordField;

public class loginEmpleado extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    
    private JButton btnIniciar;
   
   
    
    
    //Aqui se llamaria al empleado con get y set como en loginCliente

    public loginEmpleado() {
        setTitle("Plataforma de tienda en Linea - Acceso Empleado/Proveedor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        interfazEmpleado();
    }

    private void interfazEmpleado() {
    panelPrincipal = new JPanel();
    panelPrincipal.setLayout(new BorderLayout());
    panelPrincipal.setBackground(new Color(240, 240, 240));
    
    JPanel panelTitulo = new JPanel();
    panelTitulo.setBackground(new Color(0, 153, 51));
    JLabel lblTitulo = new JLabel("Acceso para Empleados y Proveedores", SwingConstants.CENTER);
    lblTitulo.setFont(new Font("Segoe UI Light", Font.BOLD, 24));
    lblTitulo.setForeground(Color.WHITE);
    panelTitulo.add(lblTitulo);
    panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
    
    JPanel panelCentral = new JPanel();
    panelCentral.setLayout(null);
    panelCentral.setBackground(new Color(240, 240, 240));
    
    JLabel lblUsuario = new JLabel("Usuario:");
    lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    lblUsuario.setBounds(150, 120, 100, 30);
    txtUsuario = new JTextField();
    txtUsuario.setBounds(300, 120, 250, 30);
    panelCentral.add(lblUsuario);
    panelCentral.add(txtUsuario);
    
    JLabel lblContrasena = new JLabel("Contraseña:");
    lblContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    lblContrasena.setBounds(150, 170, 100, 30);
    txtContrasena = new JPasswordField();
    txtContrasena.setBounds(300, 170, 250, 30);
    panelCentral.add(lblContrasena);
    panelCentral.add(txtContrasena);
    
    btnIniciar = new JButton("Iniciar Sesion");
    btnIniciar.setFont(new Font("Segoe UI", Font.BOLD, 16));
    btnIniciar.setBounds(350, 260, 150, 40);
    btnIniciar.setBackground(new Color(0, 153, 51)); 
    btnIniciar.setForeground(Color.WHITE);
    btnIniciar.addActionListener(evt -> validacionEmpleado(evt));
    panelCentral.add(btnIniciar);
    
    panelPrincipal.add(panelCentral, BorderLayout.CENTER);
    
    JPanel panelDerecho = new JPanel();
    panelDerecho.setBackground(new Color(51, 204, 51));
    panelDerecho.setPreferredSize(new Dimension(200, 0));
    panelPrincipal.add(panelDerecho, BorderLayout.EAST);
    
    add(panelPrincipal);
}

    private void validacionEmpleado(ActionEvent evt) {
        ClienteController clienteController = new ClienteController();
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());

        if (usuario.isEmpty() || contrasena.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error", JOptionPane.ERROR_MESSAGE);
        }else{
            Usuario empleadoUsuario = new Usuario(usuario, contrasena);
            if (clienteController.convalidarSesion(empleadoUsuario)) {
                EmpleadoController empleadoController = new EmpleadoController();
                Usuario admin = clienteController.obtenerUsuarioPorId(empleadoUsuario);
                Empleado existe = empleadoController.verificarSesion(admin);
                if (existe.isEstado()) {
                    JOptionPane.showMessageDialog(this, "Bienvenido " + admin.getNombre(), "Inicio de Sesión", JOptionPane.INFORMATION_MESSAGE);
                    abrirEmpleado(evt);
                }else{
                    JOptionPane.showMessageDialog(this, "Usuario no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                    txtUsuario.setText("");
                    txtContrasena.setText("");
                }
            }else {
                JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
                txtUsuario.setText("");
                txtContrasena.setText("");
            }
        }
    }

    private void abrirEmpleado(ActionEvent evt) {
         new InicioEmpleado().setVisible(true); 
        this.dispose(); 
       
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new loginEmpleado().setVisible(true));
    }
}