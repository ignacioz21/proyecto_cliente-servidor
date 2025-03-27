/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CarritoCompra extends JFrame {
    private ArrayList<Producto> carrito;

    public CarritoCompra(ArrayList<Producto> carrito) {
        this.carrito = carrito; 
        
        setTitle("Carrito de Compras");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel panel = new JPanel(new BorderLayout());
        String[] columnNames = {"Producto", "Precio", "Cantidad", "Subtotal"};
        
       
        Object[][] data = new Object[carrito.size()][4];
        double total = 0; 

        for (int i = 0; i < carrito.size(); i++) {
            Producto producto = carrito.get(i);
            data[i][0] = producto.getNombre();
            data[i][1] = producto.getPrecio();
            data[i][2] = producto.getCantidad(); 
            double subtotal = producto.getPrecio() * producto.getCantidad(); 
            data[i][3] = subtotal;
            total += subtotal; 
        }
        
        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Crear un JLabel para mostrar el total
        JLabel totalLabel = new JLabel("Total: $" + String.format("%.2f", total));
        panel.add(totalLabel, BorderLayout.SOUTH);
        
        JButton checkoutButton = new JButton("Finalizar Compra");
        checkoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Compra finalizada con éxito!");
                carrito.clear(); 
                dispose();
            }
        });
        
        panel.add(checkoutButton, BorderLayout.NORTH);
        add(panel);
    }
}