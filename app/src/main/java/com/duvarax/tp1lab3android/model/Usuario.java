package com.duvarax.tp1lab3android.model;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Usuario implements Serializable {
    private String nombre;
    private Long dni;
    private String apellido;
    private String correo;
    private String contraseña;




    public Usuario() {
    }

    public Usuario(String nombre, Long dni, String apellido, String correo, String contraseña) {
        this.nombre = nombre;
        this.dni = dni;
        this.apellido = apellido;
        this.correo = correo;
        this.contraseña = contraseña;
    }




    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    @NonNull
    @Override
    public String toString() {
        return "Usuario:{"+nombre+ " | " + apellido + " | " + dni + " | " + correo + " | " + contraseña + " | }";
    }
}
