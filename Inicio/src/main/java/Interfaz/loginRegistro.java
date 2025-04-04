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
import java.util.Arrays;

import BaseDeDatos.UsuarioDAO;
import ClasesModelos.Usuario;
import Herramientas.Encripter;

public class loginRegistro extends JFrame {
    private JPanel panelPrincipal;
    private JTextField solicitudNombre, solicitudApellido;
    private JTextField solicitudContrasena;
    private JButton botonRegistrar;
    private JButton botonVolver;
    String nombre, apellido, contrasena;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public loginRegistro() {
        setTitle("Registro de Plataforma de tienda en Linea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        InterfazRegistro();
    }

    private void InterfazRegistro() {
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(null);
        panelPrincipal.setBackground(new Color(255, 223, 102));

        JLabel lblTitulo = new JLabel("Registro de Usuario", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI Light", Font.BOLD, 24));
        lblTitulo.setBounds(250, 30, 300, 30);
        panelPrincipal.add(lblTitulo);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblNombre.setBounds(150, 80, 100, 30);
        solicitudNombre = new JTextField();
        solicitudNombre.setBounds(250, 80, 300, 30);
        panelPrincipal.add(lblNombre);
        panelPrincipal.add(solicitudNombre);

        JLabel lblCorreo = new JLabel("Apellido:");
        lblCorreo.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblCorreo.setBounds(150, 130, 100, 30);
        solicitudApellido = new JTextField();
        solicitudApellido.setBounds(250, 130, 300, 30);
        panelPrincipal.add(lblCorreo);
        panelPrincipal.add(solicitudApellido);

        JLabel lblContrasena = new JLabel("Contrasena:");
        lblContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblContrasena.setBounds(150, 230, 100, 30);
        solicitudContrasena = new JPasswordField();
        solicitudContrasena.setBounds(250, 230, 300, 30);
        panelPrincipal.add(lblContrasena);
        panelPrincipal.add(solicitudContrasena);

       botonRegistrar = new JButton("Registrarse");
       botonRegistrar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        botonRegistrar.setBounds(300, 300, 200, 40);
        botonRegistrar.setBackground(new Color(255, 165, 0));
        botonRegistrar.setForeground(Color.WHITE);
       botonRegistrar.addActionListener(this::registrarUsuario);
        panelPrincipal.add(botonRegistrar);
        
        
        botonVolver = new JButton("Volver ");
        botonVolver.setFont(new Font("Segoe UI", Font.PLAIN, 14));
       botonVolver.setBounds(300, 350, 200, 30);
        botonVolver.setBackground(new Color(200, 200, 200));
        botonVolver.addActionListener(this::volverLogin);
        panelPrincipal.add(botonVolver);

        add(panelPrincipal);
    }

    private void registrarUsuario(ActionEvent evt) {
        setNombre(solicitudNombre.getText());
        setApellido(solicitudApellido.getText());
        setContrasena(solicitudContrasena.getText());
        System.out.println("Nombre: " + getNombre());
        System.out.println("Apellido: " + getApellido());
        System.out.println("Contrasena: " + getContrasena());
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (getNombre().isEmpty() || getApellido().isEmpty() || getContrasena().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
        } else if (usuarioDAO.convalidarUsuario(nombre)) {
            JOptionPane.showMessageDialog(this, "El nombre de usuario ya existe. Por favor, elija otro.");
        } else {
            Usuario nuevoUsuario = new Usuario();
            nuevoUsuario.setNombre(getNombre());
            nuevoUsuario.setApellido(getApellido());
            nuevoUsuario.setPassword(getContrasena());
            usuarioDAO.agregarUsuario(nuevoUsuario);
            JOptionPane.showMessageDialog(this, "Usuario registrado exitosamente.");
            volverALoginCliente();
        }


    }
    
    private void volverLogin(ActionEvent evt) {
        volverALoginCliente();
    }
    
    private void volverALoginCliente() {
        loginCliente login = new loginCliente();
        login.setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new loginRegistro().setVisible(true));
    }
}