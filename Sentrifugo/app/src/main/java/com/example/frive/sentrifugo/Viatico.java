package com.example.frive.sentrifugo;

public class Viatico {
    String Nombre;
    String CorreoSolicitante;
    String Justificacion;
    int Monto;
    public Viatico(){

    }
    public Viatico(String nombre, String correoSolicitante, String justificacion, int monto) {
        Nombre = nombre;
        CorreoSolicitante = correoSolicitante;
        Justificacion = justificacion;
        Monto = monto;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getCorreoSolicitante() {
        return CorreoSolicitante;
    }

    public void setCorreoSolicitante(String correoSolicitante) {
        CorreoSolicitante = correoSolicitante;
    }

    public String getJustificacion() {
        return Justificacion;
    }

    public void setJustificacion(String justificacion) {
        Justificacion = justificacion;
    }

    public int getMonto() {
        return Monto;
    }

    public void setMonto(int monto) {
        Monto = monto;
    }
}
