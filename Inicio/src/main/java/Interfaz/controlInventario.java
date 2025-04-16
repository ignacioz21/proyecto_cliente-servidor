/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Interfaz;

/**
 *
 * @author XPC
 */
import BaseDeDatos.CategoriaDAO;
import BaseDeDatos.ProductoDAO;
import ClasesModelos.Categoria;
import ClasesModelos.Productos;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class controlInventario extends JFrame {
    private List<Productos> catalogoProductos;
    private JPanel panelProductos;
    private JComboBox<String> filtroCategoria;
    private JComboBox<String> filtroPrecio;
    private JCheckBox filtroDisponibilidad;

    public controlInventario() {
        setTitle("Control de Inventario");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        filtros();
        inicializarCatalogo();
        panelProductos();
        crearBotonRegresar();
    }

    private void inicializarCatalogo() {
        ProductoDAO productoDAO = new ProductoDAO();
        catalogoProductos = productoDAO.mostrarProductos();
    }

    private void filtros() {
        JPanel panelFiltros = new JPanel();

        CategoriaDAO categoriaDAO = new CategoriaDAO();

        List<Categoria> categorias = categoriaDAO.mostrarCategorias();
        String[] categoriasArray = new String[categorias.size() + 1];
        categoriasArray[0] = "Todos";
        for (int i = 0; i < categorias.size(); i++) {
            categoriasArray[i + 1] = categorias.get(i).getNombre();
        }

        filtroCategoria = new JComboBox<>(categoriasArray);
        panelFiltros.add(new JLabel("Categoría:"));
        panelFiltros.add(filtroCategoria);

        String[] rangosPrecio = {"Todos", "Menos de $500", "de $500 a $1000", "Más de $1000"};
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
            JLabel lblCategoria = new JLabel("Categoría: " + producto.getCategoria());
            JLabel lblStock = new JLabel("Stock actual: " + producto.getStockActual());

            JPanel infoPanel = new JPanel();
            infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
            infoPanel.add(lblNombre);
            infoPanel.add(lblPrecio);
            infoPanel.add(lblDescripcion);
            infoPanel.add(lblCategoria);
            infoPanel.add(lblStock);

            panelProducto.add(infoPanel, BorderLayout.CENTER);

            panelProductos.add(panelProducto);
        }

        panelProductos.revalidate();
        panelProductos.repaint();
    }

    private void crearBotonRegresar() {
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton btnRegresar = new JButton("Regresar");
        btnRegresar.addActionListener(e -> {
            new inicioAdmin().setVisible(true);
            dispose();
        });
        panelInferior.add(btnRegresar);
        add(panelInferior, BorderLayout.SOUTH);
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
                        case "Más de $1000":
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
            controlInventario inventario = new controlInventario();
            inventario.setVisible(true);
        });
    }
}
