/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Vista.VentasFrame;
import Modelo.Venta;
import Modelo.Producto;
import Vista.MenuFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.List;

public class ControladorVentas {

    private VentasFrame ventasFrame;
    private MenuFrame menuFrame;

    public ControladorVentas(VentasFrame ventasFrame) {
        this.ventasFrame = ventasFrame;
        this.menuFrame = menuFrame;

        // Añadir listeners a los botones
        this.ventasFrame.buttonAgregar.addActionListener(new AgregarVentaListener());
        this.ventasFrame.buttonModificar.addActionListener(new ModificarVentaListener());
        this.ventasFrame.buttonEliminar.addActionListener(new EliminarVentaListener());
        this.ventasFrame.buttonVolver.addActionListener(new VolverListener());
    }

    private void validarDatosCliente(String nombre, String email, String dni, String telefono) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("El email no es válido.");
        }
        if (dni == null || dni.trim().isEmpty()) {
            throw new IllegalArgumentException("El DNI no puede estar vacío.");
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío.");
        }
    }

    // Listener para agregar venta
    class AgregarVentaListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                int codigoProducto = Integer.parseInt(ventasFrame.textCodigo.getText());
                int cantidad = Integer.parseInt(ventasFrame.comboBoxCantidad.getSelectedItem().toString());

                Producto producto = Producto.getProductos().stream()
                        .filter(p -> p.getCódigo() == codigoProducto)
                        .findFirst()
                        .orElse(null);

                if (producto != null) {
                    Venta venta = new Venta(producto, new Date(), cantidad);
                    Venta.addVenta(venta);
                    JOptionPane.showMessageDialog(null, "Venta agregada correctamente");
                    mostrarVentas(); // Actualiza la vista
                } else {
                    JOptionPane.showMessageDialog(null, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error en el formato de los datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Listener para modificar venta
    class ModificarVentaListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                int filaSeleccionada = ventasFrame.tableVentas.getSelectedRow();
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(null, "Seleccione una venta para modificar", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                int codigoProducto = Integer.parseInt(ventasFrame.textCodigo.getText());
                int cantidad = Integer.parseInt(ventasFrame.comboBoxCantidad.getSelectedItem().toString());

                Producto producto = Producto.getProductos().stream()
                        .filter(p -> p.getCódigo() == codigoProducto)
                        .findFirst()
                        .orElse(null);

                if (producto != null) {
                    Venta venta = Venta.getVentas().get(filaSeleccionada);
                    venta.setProducto(producto);
                    venta.setCantidad(cantidad);
                    JOptionPane.showMessageDialog(null, "Venta modificada correctamente");
                    mostrarVentas(); // Actualiza la vista
                } else {
                    JOptionPane.showMessageDialog(null, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error en el formato de los datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Listener para eliminar venta
    class EliminarVentaListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                int filaSeleccionada = ventasFrame.tableVentas.getSelectedRow();
                if (filaSeleccionada == -1) {
                    JOptionPane.showMessageDialog(null, "Seleccione una venta para eliminar", "Advertencia", JOptionPane.WARNING_MESSAGE);
                    return;
                }

                Venta venta = Venta.getVentas().get(filaSeleccionada);
                Venta.removeVenta(venta);
                JOptionPane.showMessageDialog(null, "Venta eliminada correctamente");
                mostrarVentas(); // Actualiza la vista
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar la venta", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class VolverListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            volver();
        }
    }

    // Método para mostrar ventas en el frame
    private void mostrarVentas() {
        DefaultTableModel model = (DefaultTableModel) ventasFrame.tableVentas.getModel();
        model.setRowCount(0); // Limpiar la tabla

        List<Venta> ventas = Venta.getVentas();
        for (Venta v : ventas) {
            Object[] rowData = {
                v.getProducto().getCódigo(),
                v.getProducto().getNombre(),
                v.getProducto().getPrecio(),
                v.getCantidad(),
                v.getFecha()
            };
            model.addRow(rowData);
        }
    }

    private void volver() {
        ventasFrame.setVisible(false);
        menuFrame.setVisible(true);
    }
}
