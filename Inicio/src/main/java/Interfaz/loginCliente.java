/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class loginCliente extends JFrame {
    private JPanel panelPrincipal;
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JCheckBox chkRecordar;
    private JButton btnIniciar;

    public loginCliente() {
        setTitle("Plataforma de tienda en Linea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        initComponents();
    }

    private void initComponents() {
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.setBackground(new Color(220, 220, 220));

        JPanel panelTitulo = new JPanel();
        panelTitulo.setBackground(new Color(220, 220, 220));
        JLabel lblTitulo = new JLabel("Bienvenidos a Plataforma de tienda en Linea", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI Light", Font.BOLD, 24));
        panelTitulo.add(lblTitulo);
        panelPrincipal.add(panelTitulo, BorderLayout.NORTH);

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(null);
        panelCentral.setBackground(new Color(220, 220, 220));

        JLabel lblUsuario = new JLabel("Usuario:");
        lblUsuario.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblUsuario.setBounds(150, 100, 100, 30);

        txtUsuario = new JTextField();
        txtUsuario.setBounds(250, 100, 300, 30);

        JLabel lblContrasena = new JLabel("Contraseña:");
        lblContrasena.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblContrasena.setBounds(150, 150, 100, 30);

        txtContrasena = new JPasswordField();
        txtContrasena.setBounds(250, 150, 300, 30);

        chkRecordar = new JCheckBox("Recordar Contraseña");
        chkRecordar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        chkRecordar.setBounds(250, 190, 200, 30);
        chkRecordar.setBackground(new Color(220, 220, 220));

        btnIniciar = new JButton("Iniciar Sesion");
        btnIniciar.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnIniciar.setBounds(330, 230, 150, 40);
        btnIniciar.addActionListener(evt -> loginActionPerformed(evt));

        panelCentral.add(lblUsuario);
        panelCentral.add(txtUsuario);
        panelCentral.add(lblContrasena);
        panelCentral.add(txtContrasena);
        panelCentral.add(chkRecordar);
        panelCentral.add(btnIniciar);

        panelPrincipal.add(panelCentral, BorderLayout.CENTER);

        JPanel panelDerecho = new JPanel();
        panelDerecho.setBackground(new Color(30, 144, 255));
        panelDerecho.setPreferredSize(new Dimension(200, 0));
        panelPrincipal.add(panelDerecho, BorderLayout.EAST);

        add(panelPrincipal);
    }

    private void loginActionPerformed(ActionEvent evt) {
        String usuario = txtUsuario.getText();
        String contrasena = new String(txtContrasena.getPassword());

        if (usuario.equals("cliente") && contrasena.equals("1234")) {

            new InicioCompras().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Usuario o contraseña incorrectos",
                    "Error de autenticacion", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new loginCliente().setVisible(true));
    }
}
