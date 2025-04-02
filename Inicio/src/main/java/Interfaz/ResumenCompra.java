package Interfaz;

import ClasesModelos.Productos;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ResumenCompra extends JFrame {

    public ResumenCompra(ArrayList<Productos> carrito, double total) {
        setTitle("Factura");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new BorderLayout());
        JTextArea facturaArea = new JTextArea();
        facturaArea.setEditable(false);
        facturaArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        facturaArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        total = calcularTotal(carrito);
        StringBuilder factura = getStringBuilder(carrito, total);

        facturaArea.setText(factura.toString());
        panel.add(new JScrollPane(facturaArea), BorderLayout.CENTER);

        JButton closeButton = new JButton("Cerrar");
        closeButton.addActionListener(e -> System.exit(0));
        panel.add(closeButton, BorderLayout.SOUTH);

        add(panel);
    }

    private static double calcularTotal(ArrayList<Productos> carrito) {
        double total = 0;
        for (Productos producto : carrito) {
            total += producto.getPrecio() * producto.getStockActual();
        }
        return total;
    }

    private static StringBuilder getStringBuilder(ArrayList<Productos> carrito, double total) {
        StringBuilder factura = new StringBuilder();
        factura.append("Factura de Compra\n\n");
        factura.append(String.format("%-20s %-10s %-10s %-10s\n", "Producto", "Precio", "Cantidad", "Subtotal"));
        factura.append("------------------------------------------------------------\n");

        for (Productos producto : carrito) {
            double subtotal = producto.getPrecio() * producto.getStockActual();
            factura.append(String.format("%-20s %-10.2f %-10d %-10.2f\n", producto.getNombre(), producto.getPrecio(), producto.getStockActual(), subtotal));
        }

        factura.append("------------------------------------------------------------\n");
        factura.append(String.format("Total: $%.2f", total));
        return factura;
    }
}