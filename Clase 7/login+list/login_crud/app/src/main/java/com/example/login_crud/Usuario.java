package com.example.login_crud;

public class Usuario {
    private String nombre;
    private String edad;
    private String correo;

    public Usuario() {
        // Constructor vacío requerido para Firebase
    }

    public Usuario(String nombre, String edad, String correo) {
        this.nombre = nombre;
        this.edad = edad;
        this.correo = correo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEdad() {
        return edad;
    }

    public String getCorreo() {
        return correo;
    }
}
