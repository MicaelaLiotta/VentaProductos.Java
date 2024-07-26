/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class Venta {

    
    private Usuario usuario;
    private Producto producto;
    private Date fecha;
    private int cantidad;
    private static List<Venta> ventas = new ArrayList<>();

    public Venta( Producto producto, Date fecha, int cantidad) {
        
        
        this.producto = producto;
        this.fecha = fecha;
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public static void addVenta(Venta venta) {
        ventas.add(venta);
    }
    
    public static void removeVenta(Venta venta) {
        ventas.remove(venta);
    }

    public static List<Venta> getVentas() {
        return ventas;
    }
    
    
}
