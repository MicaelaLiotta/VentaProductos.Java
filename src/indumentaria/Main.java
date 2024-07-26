/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package indumentaria;

import Controlador.ControladorClientes;
import Controlador.ControladorProducto;
import Controlador.ControladorVentas;
import Vista.ClientesFrame;
import Vista.MenuFrame;
import Vista.ProductosFrame;
import Vista.VentasFrame;
import controlador.ControladorMenu;



public class Main {
    public static void main(String[] args) {
        // Crear instancias de los frames
        MenuFrame menuFrame = new MenuFrame();
        ProductosFrame productosFrame = new ProductosFrame();
        ClientesFrame clientesFrame = new ClientesFrame();
        VentasFrame ventasFrame = new VentasFrame();

        // Crear instancias de los controladores y pasarlos los frames
        ControladorMenu controladorMenu = new ControladorMenu(menuFrame, productosFrame, clientesFrame, ventasFrame);
        ControladorProducto controladorProducto = new ControladorProducto(productosFrame);
        ControladorClientes controladorClientes = new ControladorClientes(clientesFrame);
        ControladorVentas controladorVentas = new ControladorVentas(ventasFrame);

        // Mostrar el frame principal (MenuFrame)
        controladorMenu.mostrarMenu();
    }
}
