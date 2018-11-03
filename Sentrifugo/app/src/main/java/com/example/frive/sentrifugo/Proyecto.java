package com.example.frive.sentrifugo;

public class Proyecto {
    String Nombre;
    String Descripcion;
    public Proyecto(){

    }
    public Proyecto(String nombre, String descripcion) {
        Nombre = nombre;
        Descripcion = descripcion;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
