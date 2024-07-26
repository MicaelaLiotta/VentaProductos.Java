/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import Vista.ClientesFrame;
import Vista.MenuFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ControladorClientes {         //clases y atributos

    private ClientesFrame clientesFrame;
    private MenuFrame menuFrame;

    public ControladorClientes(ClientesFrame clientesFrame) {
        this.clientesFrame = clientesFrame;
        this.menuFrame = menuFrame;

        // Añadir los métodos listeners a los botones
        this.clientesFrame.buttonAgregar.addActionListener((ActionListener) new AgregarClienteListener());
        this.clientesFrame.buttonModificar.addActionListener(new ModificarClienteListener());
        this.clientesFrame.buttonEliminar.addActionListener(new EliminarClienteListener());
        this.clientesFrame.buttonVolver.addActionListener(new VolverListener());
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

    // Listener para agregar cliente
    class AgregarClienteListener implements ActionListener {       //interfaz

        public void actionPerformed(ActionEvent e) {
            try {
                String nombre = clientesFrame.textNombre.getText();      //captura datos del formulario
                String email = clientesFrame.textMail.getText();
                String dni = clientesFrame.textDNI.getText();
                String telefono = clientesFrame.textTelefono.getText();

                validarDatosCliente(nombre, email, dni, telefono);     //valida que no esten vacios

                Usuario usuario = new Usuario(nombre, email, dni, telefono);     //crea un objeto usuario con datos ingresados
                Usuario.addUsuario(usuario);                     //agrega usuario a la lista
                JOptionPane.showMessageDialog(null, "Cliente agregado correctamente");
                mostrarClientes(); // Actualiza la vista
            } catch (IllegalArgumentException ex) {         //excepción para datos invalidos
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception ex) {                       //excepción para errores genericos
                JOptionPane.showMessageDialog(null, "Error al agregar cliente", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Listener para modificar cliente
    class ModificarClienteListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            // Obtener la fila seleccionada en la tabla de clientes
            int filaSeleccionada = clientesFrame.tablaClientes.getSelectedRow();

            // Verificar si se ha seleccionado una fila
            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(null, "Seleccione un cliente para modificar", "Advertencia", JOptionPane.WARNING_MESSAGE);
                return;
            }

            try {
                // Obtener el DNI del cliente seleccionado en la tabla
                String dni = (String) clientesFrame.tablaClientes.getValueAt(filaSeleccionada, 0);

                // Buscar el cliente existente
                Usuario usuario = Usuario.getUsuarios().stream() //lo convierte en un flujo stream para procesar
                        .filter(u -> u.getDni().equals(dni)) //filtra los usuarios para encontrar el dni
                        .findFirst() //obtiene el primer usuario que coincida
                        .orElse(null);

                if (usuario != null) {
                    // Actualizar los datos del cliente
                    usuario.setNombre(clientesFrame.textNombre.getText());
                    usuario.setEmail(clientesFrame.textMail.getText());
                    usuario.setTeléfono(clientesFrame.textTelefono.getText());
                    JOptionPane.showMessageDialog(null, "Cliente modificado correctamente");
                    mostrarClientes(); // Actualiza la vista
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al modificar cliente", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Listener para eliminar cliente
    class EliminarClienteListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            try {
                String dni = clientesFrame.textDNI.getText();
                Usuario usuario = Usuario.getUsuarios().stream()
                        .filter(u -> u.getDni().equals(dni))
                        .findFirst()
                        .orElse(null);

                if (usuario != null) {
                    Usuario.getUsuarios().remove(usuario);
                    JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente");
                    mostrarClientes(); // Actualiza la vista
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Error al eliminar cliente", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    //  actualiza la tabla que muestra la lista de clientes en la interfaz gráfica
    private void mostrarClientes() {
        DefaultTableModel model = (DefaultTableModel) clientesFrame.tablaClientes.getModel();    //obtiene modelo de tabla
        model.setRowCount(0); // Limpiar la tabla
        for (Usuario u : Usuario.getUsuarios()) {
            Object[] rowData = {u.getDni(), u.getNombre(), u.getEmail(), u.getTeléfono()};
            model.addRow(rowData);
        }
    }

    class VolverListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            volver();
        }
    }

    private void volver() {
        clientesFrame.setVisible(false);
        menuFrame.setVisible(true);
    }
}
