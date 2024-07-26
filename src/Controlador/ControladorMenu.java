/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Vista.MenuFrame;
import Vista.ClientesFrame;
import Vista.ProductosFrame;
import Vista.VentasFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class ControladorMenu {

    private MenuFrame menuFrame;
    private ProductosFrame productosFrame;
    private ClientesFrame clientesFrame;
    private VentasFrame ventasFrame;

    public ControladorMenu(MenuFrame menuFrame, ProductosFrame productosFrame, ClientesFrame clientesFrame, VentasFrame ventasFrame) {
        this.menuFrame = menuFrame;
        this.productosFrame = productosFrame;
        this.clientesFrame = clientesFrame;
        this.ventasFrame = ventasFrame;

        // Llamo al metodo onfigurar los ActionListeners para los botones del MenuFrame
        setupListeners();
    }
    //actionListener es una interfaz que se utiliza para manejar los eventos de acción.
    //actionPerformed es el método que trabaja

    private void setupListeners() {
        // Agregar ActionListeners a los botones
        menuFrame.getBotonProductos().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarProductos();
            }
        });

        menuFrame.getBotonClientes().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarClientes();
            }
        });

        menuFrame.getBotonVentas().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarVentas();
            }
        });
    }

    public void mostrarMenu() {                //métodos para mostrar cada ventana
        menuFrame.setVisible(true);
    }

    public void mostrarProductos() {
        productosFrame.setVisible(true);
    }

    public void mostrarClientes() {
        clientesFrame.setVisible(true);
    }

    public void mostrarVentas() {
        ventasFrame.setVisible(true);
    }
}
