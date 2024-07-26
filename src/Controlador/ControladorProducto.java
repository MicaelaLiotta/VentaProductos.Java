/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Producto;
import Vista.MenuFrame;

import Vista.ProductosFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorProducto {

    public ProductosFrame productoFrame;
    private MenuFrame menuFrame;

    public ControladorProducto(ProductosFrame productoFrame) {
        this.productoFrame = productoFrame;
        this.menuFrame = menuFrame;

        // Añadir listeners a los botones
        this.productoFrame.buttonAgregar.addActionListener(new AgregarProductoListener());
        this.productoFrame.buttonModificar.addActionListener(new ModificarProductoListener());
        this.productoFrame.buttonEliminar.addActionListener(new EliminarProductoListener());
        
        this.productoFrame.buttonVolver.addActionListener(new VolverListener());
    }

    private void validarDatosProducto(String nombre, String precioStr, String codigoStr) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío.");
        }
        if (precioStr == null || precioStr.trim().isEmpty()) {
            throw new IllegalArgumentException("El precio no puede estar vacío.");
        }
        if (codigoStr == null || codigoStr.trim().isEmpty()) {
            throw new IllegalArgumentException("El código no puede estar vacío.");
        }
        try {
            Double.parseDouble(precioStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El precio debe ser un número válido.");
        }
        try {
            Integer.parseInt(codigoStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("El código debe ser un número válido.");
        }
    }

    // Listener para agregar producto
    class AgregarProductoListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                String nombre = productoFrame.textFieldNombre.getText();
                String precioStr = productoFrame.textFieldPrecio.getText();
                String codigoStr = productoFrame.textFieldCodigo.getText();

                // Validar los datos del producto
                validarDatosProducto(nombre, precioStr, codigoStr);

                double precio = Double.parseDouble(precioStr);
                int codigo = Integer.parseInt(codigoStr);

                Producto producto = new Producto(nombre, precio, codigo);
                Producto.addProducto(producto);
                JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
                mostrarProductos(); // Actualiza la vista
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    class ModificarProductoListener implements ActionListener {

    public void actionPerformed(ActionEvent e) {
        // Obtener la fila seleccionada en la tabla de productos
        int filaSeleccionada = productoFrame.tableProductos.getSelectedRow();

        // Verificar si se ha seleccionado una fila
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(null, "Seleccione un producto para modificar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // Obtener el código del producto de la tabla
            int codigoTabla = (int) productoFrame.tableProductos.getValueAt(filaSeleccionada, 0);

            // Obtener los datos del producto de los campos de texto
            String nombre = productoFrame.textFieldNombre.getText();
            String precioStr = productoFrame.textFieldPrecio.getText();
            String codigoStr = productoFrame.textFieldCodigo.getText();

            // Validar los datos del producto
            validarDatosProducto(nombre, precioStr, codigoStr);

            double precio = Double.parseDouble(precioStr);
            int codigo = Integer.parseInt(codigoStr);

            // Verificar que el código del campo de texto coincide con el código del producto seleccionado
            if (codigo != codigoTabla) {
                JOptionPane.showMessageDialog(null, "El código del producto no coincide con el producto seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Buscar el producto existente
            Producto existingProducto = Producto.getProducto(codigo);
            if (existingProducto != null) {
                // Actualizar los datos del producto
                existingProducto.setNombre(nombre);
                existingProducto.setPrecio(precio);
                JOptionPane.showMessageDialog(null, "Producto modificado correctamente");
                mostrarProductos(); // Actualiza la vista
            } else {
                JOptionPane.showMessageDialog(null, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    // Listener para eliminar producto
    class EliminarProductoListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                int codigo = Integer.parseInt(productoFrame.textFieldCodigo.getText());
                Producto producto = Producto.getProducto(codigo);
                if (producto != null) {
                    Producto.getProductos().remove(producto);
                    JOptionPane.showMessageDialog(null, "Producto eliminado correctamente");
                    mostrarProductos(); // Actualiza la vista
                } else {
                    JOptionPane.showMessageDialog(null, "Producto no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Error en el formato de los datos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

   

    // Método para mostrar productos en el frame
    public void mostrarProductos() {
        DefaultTableModel model = (DefaultTableModel) productoFrame.tableProductos.getModel();
        model.setRowCount(0); // Limpiar la tabla
        for (Producto p : Producto.getProductos()) {
            Object[] rowData = {p.getCódigo(), p.getNombre(), p.getPrecio()};
            model.addRow(rowData);
        }
    }

    class VolverListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            volver();
        }
    }

    private void volver() {
        productoFrame.setVisible(false);
        menuFrame.setVisible(true);
    }
}
