package com.example.frive.sentrifugo;

public class Usuario {
    int Edad;
    String Nombre;
    String Correo;
    String Rango;
    String Puesto;
    public Usuario(){

    }
    public Usuario(int edad, String nombre, String correo, String rango, String puesto) {
        Edad = edad;
        Nombre = nombre;
        Correo = correo;
        Rango = rango;
        Puesto = puesto;
    }

    public int getEdad() {
        return Edad;
    }

    public void setEdad(int edad) {
        Edad = edad;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getRango() {
        return Rango;
    }

    public void setRango(String rango) {
        Rango = rango;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String puesto) {
        Puesto = puesto;
    }
}
