package Interfaz;

import ClasesModelos.Productos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class Caja extends JFrame {
    private ArrayList<Productos> carrito;
    private JLabel totalLabel;
    private double total;

    public Caja(ArrayList<Productos> carrito) {
        this.carrito = carrito;

        setTitle("Caja");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        String[] columnNames = {"Producto", "Precio", "Cantidad", "Subtotal"};

        Object[][] data = new Object[carrito.size()][4];
        total = 0;

        for (int i = 0; i < carrito.size(); i++) {
            Productos producto = carrito.get(i);
            data[i][0] = producto.getNombre();
            data[i][1] = String.format("$%.2f", producto.getPrecio());
            data[i][2] = producto.getStockActual();
            double subtotal = producto.getPrecio() * producto.getStockActual();
            data[i][3] = String.format("$%.2f", subtotal);
            total += subtotal;
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        totalLabel = new JLabel("Total: $" + String.format("%.2f", total));
        panel.add(totalLabel, BorderLayout.SOUTH);

        JButton payButton = new JButton("Pagar");
        payButton.addActionListener((ActionEvent e) -> realizarPago());

        panel.add(payButton, BorderLayout.NORTH);
        add(panel);
    }

    private void realizarPago() {
        JOptionPane.showMessageDialog(this, "Pago realizado con éxito. Total pagado: $" + String.format("%.2f", total));
        carrito.clear();
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ArrayList<Productos> carrito = new ArrayList<>();
            // Agregar productos al carrito para pruebas
            carrito.add(new Productos("Producto 1", "Descripción 1", 1, 1, 10, 1, 10.0f, 10.0f, true));
            carrito.add(new Productos("Producto 2", "Descripción 2", 2, 2, 20, 2, 20.0f, 20.0f, true));

            Caja caja = new Caja(carrito);
            caja.setVisible(true);
        });
    }
}

