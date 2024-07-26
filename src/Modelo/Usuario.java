/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.ArrayList;
import java.util.List;



public class Usuario {
    
    private String nombre;
    private String email;
    private String dni;
    private String teléfono;

    
    private static List<Usuario> usuarios = new ArrayList<>();

    public String getTeléfono() {
        return teléfono;
    }

    public void setTeléfono(String teléfono) {
        this.teléfono = teléfono;
    }
    


    public Usuario(String nombre, String email, String dni, String teléfono) {
        
        this.nombre = nombre;
        this.email = email;
        this.dni = dni;
        this.teléfono = teléfono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

   

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
     // Métodos para manejar la lista de usuarios
    public static void addUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static List<Usuario> getUsuarios() {
        return usuarios;
    }
}

    

