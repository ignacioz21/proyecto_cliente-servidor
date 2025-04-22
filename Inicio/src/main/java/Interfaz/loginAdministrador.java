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

import javax.swing.JPasswordField;

public class loginAdministrador extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    
    private JButton btnIniciar;


    public loginAdministrador() {
        setTitle("Plataforma de tienda en Linea - Acceso Admin ");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        interfazEmpleado();
    }

    
private void interfazEmpleado() {
    panelPrincipal = new JPanel();
    panelPrincipal.setLayout(new BorderLayout());
    panelPrincipal.setBackground(new Color(255, 240, 240)); 
    
    JPanel panelTitulo = new JPanel();
    panelTitulo.setBackground(new Color(153, 0, 0)); 
    JLabel lblTitulo = new JLabel("Acceso para Admin ", SwingConstants.CENTER);
    lblTitulo.setFont(new Font("Segoe UI Light", Font.BOLD, 24));
    lblTitulo.setForeground(Color.WHITE);
    panelTitulo.add(lblTitulo);
    panelPrincipal.add(panelTitulo, BorderLayout.NORTH);
    
    JPanel panelCentral = new JPanel();
    panelCentral.setLayout(null);
    panelCentral.setBackground(new Color(255, 240, 240));
    
    JLabel lblUsuario = new JLabel("Usuario:");
    lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    lblUsuario.setBounds(150, 120, 100, 30);
    txtUsuario = new JTextField();
    txtUsuario.setBounds(300, 120, 250, 30);
    panelCentral.add(lblUsuario);
    panelCentral.add(txtUsuario);
    
    JLabel lblContrasena = new JLabel("Contrasena:");
    lblContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 16));
    lblContrasena.setBounds(150, 170, 100, 30);
    txtContrasena = new JPasswordField();
    txtContrasena.setBounds(300, 170, 250, 30);
    panelCentral.add(lblContrasena);
    panelCentral.add(txtContrasena);
    
    btnIniciar = new JButton("Iniciar Sesion");
    btnIniciar.setFont(new Font("Segoe UI", Font.BOLD, 16));
    btnIniciar.setBounds(350, 260, 150, 40);
    btnIniciar.setBackground(new Color(204, 0, 0)); 
    btnIniciar.setForeground(Color.WHITE);
    btnIniciar.addActionListener(evt -> validacionAdmin(evt));
    panelCentral.add(btnIniciar);
    
    panelPrincipal.add(panelCentral, BorderLayout.CENTER);
    
    JPanel panelDerecho = new JPanel();
    panelDerecho.setBackground(new Color(220, 53, 69)); 
    panelDerecho.setPreferredSize(new Dimension(200, 0));
    panelPrincipal.add(panelDerecho, BorderLayout.EAST);
    
    add(panelPrincipal);
}

     private void validacionAdmin(ActionEvent evt) {
         
        //aqui iria la validacion de cuenta de administrador conm base de datos 
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());

        if (usuario.equals("a") && contrasena.equals("1")) {
            abrirAdmin(evt);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos.", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirAdmin(ActionEvent evt) {
     
      inicioAdmin ventanaAdmin = new inicioAdmin();
        ventanaAdmin.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new loginEmpleado().setVisible(true));
    }
}
