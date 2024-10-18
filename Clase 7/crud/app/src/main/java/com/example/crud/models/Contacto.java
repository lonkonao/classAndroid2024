package com.example.crud.models;

public class Contacto {
    private String id;
    private String nombre;
    private int edad;

    public Contacto() {
        // Constructor vacío requerido por Firebase
    }

    public Contacto(String id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }
}

