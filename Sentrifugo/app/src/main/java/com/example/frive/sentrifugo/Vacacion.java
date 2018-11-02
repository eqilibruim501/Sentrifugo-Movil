package com.example.frive.sentrifugo;

public class Vacacion {
    String Nombre;
    String Fecha;
    int Dias;
    String Motivo;
    String Correo;
    public Vacacion(){

    }

    public Vacacion(String nombre, String fecha, int dias, String motivo, String correo) {
        Nombre = nombre;
        Fecha = fecha;
        Dias = dias;
        Motivo = motivo;
        Correo = correo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getFecha() {
        return Fecha;
    }

    public void setFecha(String fecha) {
        Fecha = fecha;
    }

    public int getDias() {
        return Dias;
    }

    public void setDias(int dias) {
        Dias = dias;
    }

    public String getMotivo() {
        return Motivo;
    }

    public void setMotivo(String motivo) {
        Motivo = motivo;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }
}
