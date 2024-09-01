package com.example.registro;

import java.util.List;

public class Usuario {
    private String nombre;
    private String apellidos;
    private String fechaNacimiento;
    private String sexo;
    private List<String> intereses;
    private boolean estado;


    public Usuario(String nombre, String apellidos, String fechaNacimiento, String sexo, List<String> intereses, boolean estado) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        this.intereses = intereses;
        this.estado=estado;
    }
    // getters y setters

    public String getNombre(){
        return nombre;
    }

    public String getApellidos(){
        return apellidos;
    }

    public String getFechaNacimiento(){
        return fechaNacimiento;
    }

    public String getSexo(){
        return sexo;
    }

    public List<String> getIntereses(){
        return intereses;
    }

    public boolean getEstado(){
        return estado;
    }


}

