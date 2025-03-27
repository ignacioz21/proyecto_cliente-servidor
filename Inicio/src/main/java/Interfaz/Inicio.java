/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Interfaz;

/**
 *
 * @author XPC
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


public class Inicio extends JFrame {
    private JPanel panelPrincipal;
    private JButton inicioAdmin, clienteInicio,  imagenInicio;
    private JLabel jLabel1;

    public Inicio() {
        
        setTitle("Plataforma de Ventas en Linea");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 800);
        setLocationRelativeTo(null);
        InicioTienda();
    }

    private void InicioTienda() {
        
        panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());

       
        jLabel1 = new JLabel("Bienvenido a la Plataforma de tienda en linea", SwingConstants.CENTER);
        jLabel1.setFont(new Font("Segoe UI Light", Font.BOLD, 36));
        panelPrincipal.add(jLabel1, BorderLayout.NORTH);

       
        imagenInicio = new JButton();
        
       
        ImageIcon icono = new ImageIcon("imagenes/inicioLogin.jpeg");
        ImageIcon iconoEscalado = new ImageIcon(icono.getImage().getScaledInstance(1100, 1100, Image.SCALE_FAST));
        
        
         imagenInicio.setIcon(iconoEscalado);
        panelPrincipal.add(imagenInicio, BorderLayout.CENTER);

       
        JPanel panelBotones = new JPanel();
        panelBotones.setLayout(new FlowLayout());

       
        inicioAdmin = new JButton("Administrador");
        inicioAdmin.setFont(new Font("Segoe UI", Font.BOLD, 24));
        inicioAdmin.addActionListener(evt -> inicioAdminActionPerformed(evt));
        panelBotones.add(inicioAdmin);
        clienteInicio = new JButton("Cliente");
        clienteInicio.setFont(new Font("Segoe UI", Font.BOLD, 24));
        clienteInicio.addActionListener(evt -> clienteInicioActionPerformed(evt));
        panelBotones.add(clienteInicio);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        add(panelPrincipal);
    }

    private void inicioAdminActionPerformed(ActionEvent evt) {
        
    }

    private void clienteInicioActionPerformed(ActionEvent evt) {
      
        new loginCliente().setVisible(true);
        this.setVisible(false); 
    }
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> new Inicio().setVisible(true));
    }
}