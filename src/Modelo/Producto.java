/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;

public class Producto {

    private String nombre;
    private double precio;
    private int código;
    private static List<Producto> productos = new ArrayList<>();

    public Producto(String nombre, double precio, int código) {

        this.nombre = nombre;
        this.precio = precio;
        this.código = código;

    }

    public int getCódigo() {
        return código;
    }

    public void setCódigo(int stock) {
        this.código = stock;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Métodos para manejar la lista de productos
    public static void addProducto(Producto producto) {
        productos.add(producto);
    }

    public static Producto getProducto(int codigo) {    //metodo para encontrar el producto con su codigo en ventas
        for (Producto p : productos) {
            if (codigo == p.getCódigo()) {
                return p;
            }
        }
        return null; // Retorna null si no se encuentra el producto
    }

    public static List<Producto> getProductos() {
        return productos;
    }

}
