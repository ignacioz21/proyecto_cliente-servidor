/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Interfaz;



import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import BaseDeDatos.CategoriaDAO;
import BaseDeDatos.ProductoDAO;
import ClasesModelos.Categoria;
import ClasesModelos.Productos;

public class InicioCompras extends JFrame {
    private List<Productos> catalogoProductos; 
    private JPanel panelProductos;
    private JComboBox<String> filtroCategoria;
    private JComboBox<String> filtroPrecio;
    private JCheckBox filtroDisponibilidad;

    public InicioCompras() {
        setTitle("Tienda de Productos");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        filtros();
        inicializarCatalogo();
        panelProductos();
        crearBotonCarrito();
    }

    private void inicializarCatalogo() {
        ProductoDAO productoDAO = new ProductoDAO();
        catalogoProductos = productoDAO.mostrarProductos();
    }

    private void filtros() {
        JPanel panelFiltros = new JPanel();

        CategoriaDAO categoriaDAO = new CategoriaDAO();

        List<Categoria> categorias = categoriaDAO.mostrarCategorias();
        String[] categoriasArray = new String[categorias.size()];

        for (int i = 0; i < categorias.size(); i++) {
            categoriasArray[i] = categorias.get(i).getNombre();
        }
        filtroCategoria = new JComboBox<>(categoriasArray);
        panelFiltros.add(new JLabel("Categoria:"));
        panelFiltros.add(filtroCategoria);
        
        String[] rangosPrecio = {"Todos", "Menos de $500", "de $500 a $1000", "Mas de $1000"};
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


    private void panelProductos() {
        panelProductos = new JPanel();
        panelProductos.setLayout(new GridLayout(0, 3, 10, 10));
        
        mostrarProductos(catalogoProductos);
        
        JScrollPane scrollPane = new JScrollPane(panelProductos);
        add(scrollPane, BorderLayout.CENTER);
    }

    private void mostrarProductos(List<Productos> productos) {
        panelProductos.removeAll();
        
        for (Productos producto : productos) {
            JPanel panelProducto = new JPanel();
            panelProducto.setLayout(new BorderLayout());
            panelProducto.setBorder(BorderFactory.createLineBorder(Color.GRAY));
            
            JLabel lblNombre = new JLabel(producto.getNombre());
            JLabel lblPrecio = new JLabel(String.format("$%.2f", producto.getPrecio()));
            JLabel lblDescripcion = new JLabel(producto.getDescripcion());
            JLabel lblCategoria = new JLabel("Categoria: " + producto.getCategoria());
            
            JButton btnVerDetalles = new JButton("Ver Detalles");
            btnVerDetalles.addActionListener(e -> {
                if (producto.getStockActual() > 0) { 
                    ProductoDetalles detalles = new ProductoDetalles(producto);
                    detalles.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(this, "Producto agotado");
                }
            });
            
            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.add(lblNombre);
            infoPanel.add(lblPrecio);
            infoPanel.add(lblDescripcion);
            infoPanel.add(lblCategoria);
            
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
        String categoriaSeleccionada = (String) filtroCategoria.getSelectedItem();
        String rangoPrecioSeleccionado = (String) filtroPrecio.getSelectedItem();
        boolean soloDisponibles = filtroDisponibilidad.isSelected();

        List<Productos> productosFiltrados = catalogoProductos.stream()
                .filter(producto -> categoriaSeleccionada.equals("Todos") || producto.getCategoria().equals(categoriaSeleccionada))
                .filter(producto -> {
                    switch (rangoPrecioSeleccionado) {
                        case "Menos de $500":
                            return producto.getPrecio() < 500;
                        case "de $500 a $1000":
                            return producto.getPrecio() >= 500 && producto.getPrecio() <= 1000;
                        case "Mas de $1000":
                            return producto.getPrecio() > 1000;
                        default:
                            return true;
                    }
                })
                .filter(producto -> !soloDisponibles || producto.getStockActual() > 0)
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