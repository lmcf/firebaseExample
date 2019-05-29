package com.example.firebaseexample;

public class Reserva {
    String fecha;
    String personas;
    String nombre;
    String telefono;
    String comentarios;

    // Firebase me obliga a tener un constructor vacio
    public Reserva(){}

    public Reserva(String fecha, String personas, String nombre, String telefono, String comentarios) {
        this.fecha = fecha;
        this.personas = personas;
        this.nombre = nombre;
        this.telefono = telefono;
        this.comentarios = comentarios;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPersonas() {
        return personas;
    }

    public void setPersonas(String personas) {
        this.personas = personas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}
