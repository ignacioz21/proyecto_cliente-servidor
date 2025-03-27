/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class InicioCompras extends JFrame {
    private ArrayList<Producto> catalogoProductos;
    private JPanel panelProductos;
    private JComboBox<String> filtroCategoria;
    private JComboBox<String> filtroPrecio;
    private JCheckBox filtroDisponibilidad;

    public InicioCompras() {
        setTitle("Tienda de Productos");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        inicializarCatalogo();
        crearPanelFiltros();
        crearPanelProductos();
        crearBotonCarrito();
    }

    private void inicializarCatalogo() {
        catalogoProductos = new ArrayList<>();
        
        catalogoProductos.add(new Producto("iPhone 13", 799.99, 10, "Smartphone de Apple", "Smartphones"));
        catalogoProductos.add(new Producto("Samsung Galaxy S21", 699.99, 15, "Smartphone Samsung", "Smartphones"));
        catalogoProductos.add(new Producto("MacBook Pro", 1299.99, 5, "Laptop de Apple", "Laptops"));
        catalogoProductos.add(new Producto("Dell XPS", 1099.99, 8, "Laptop Dell", "Laptops"));
        catalogoProductos.add(new Producto("iPad Pro", 799.99, 12, "Tablet de Apple", "Tablets"));
        catalogoProductos.add(new Producto("Samsung Galaxy Tab", 549.99, 7, "Tablet Samsung", "Tablets"));
    }

    private void crearPanelFiltros() {
        JPanel panelFiltros = new JPanel();
        
        String[] categorias = {"Todos", "Smartphones", "Laptops", "Tablets"};
        filtroCategoria = new JComboBox<>(categorias);
        panelFiltros.add(new JLabel("Categoría:"));
        panelFiltros.add(filtroCategoria);
        
        String[] rangosPrecio = {"Todos", "Menos de $500", "$500 - $1000", "Más de $1000"};
        filtroPrecio = new JComboBox<>(rangosPrecio);
        panelFiltros.add(new JLabel("Precio:"));
        panelFiltros.add(filtroPrecio);
        
        filtroDisponibilidad = new JCheckBox("Solo disponibles");
        panelFiltros.add(filtroDisponibilidad);
        
        JButton btnAplicarFiltros = new JButton("Aplicar Filtros");
        btnAplicarFiltros.addActionListener(e -> aplicarFiltros());
        panelFiltros.add(btnAplicarFiltros);
        
        add(panelFiltros, BorderLayout.NORTH);
    }

    private void crearPanelProductos() {
        panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, 3, 10, 10));
        
        mostrarProductos(catalogoProductos);
        
        JScrollPane scrollPane = new JScrollPane(panelProductos);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void mostrarProductos(List<Producto> productos) {
        panelProductos.removeAll();
        
        for (Producto producto : productos) {
            JPanel panelProducto = new JPanel();
            panelProducto.setLayout(new BorderLayout());
            panelProducto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            
            // Removed image loading and display
            JLabel lblNombre = new JLabel(producto.getNombre());
            JLabel lblPrecio = new JLabel(String.format("$%.2f", producto.getPrecio()));
            JLabel lblDescripcion = new JLabel(producto.getDescripcion());
            
            JButton btnVerDetalles = new JButton("Ver Detalles");
            btnVerDetalles.addActionListener(e -> {
                if (producto.getCantidad() > 0) {
                    ProductoDetalles detalles = new ProductoDetalles(producto);
                    detalles.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Producto agotado", "Sin Stock", JOptionPane.WARNING_MESSAGE);
                }
            });
            
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.add(lblNombre);
            infoPanel.add(lblPrecio);
            infoPanel.add(lblDescripcion);
            
            panelProducto.add(infoPanel, BorderLayout.CENTER);
            panelProducto.add(btnVerDetalles, BorderLayout.SOUTH);
            
            panelProductos.add(panelProducto);
        }
        
        panelProductos.revalidate();
        panelProductos.repaint();
    }

    private void crearBotonCarrito() {
        JButton btnCarrito = new JButton("Ver Carrito");
        btnCarrito.addActionListener(e -> {
            ProductoDetalles.mostrarCarrito();
        });
        
        add(btnCarrito, BorderLayout.SOUTH);
    }

    private void aplicarFiltros() {
        List<Producto> productosFiltrados = catalogoProductos.stream()
            .filter(p -> filtroCategoria.getSelectedItem().equals("Todos") || 
                     p.getCategoria().equals(filtroCategoria.getSelectedItem()))
            .filter(p -> {
                String precioSeleccionado = (String) filtroPrecio.getSelectedItem();
                switch(precioSeleccionado) {
                    case "Menos de $500":
                        return p.getPrecio() < 500;
                    case "$500 - $1000":
                        return p.getPrecio() >= 500 && p.getPrecio() <= 1000;
                    case "Más de $1000":
                        return p.getPrecio() > 1000;
                    default:
                        return true;
                }
            })
            .filter(p -> !filtroDisponibilidad.isSelected() || p.getCantidad() > 0)
            .collect(Collectors.toList());
        
        mostrarProductos(productosFiltrados);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            InicioCompras tienda = new InicioCompras();
            tienda.setVisible(true);
        });
    }
}