package com.indytek.pufsmanagement.objects;

import com.google.gson.annotations.SerializedName;

public class Producto {
    @SerializedName("nombre")
    private String nombre;
    @SerializedName("imagen")
    private String imagen;
    @SerializedName("precio")
    private float precio;

    public Producto() {

    }

    public Producto(String nombre, String imagen, float precio) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "nombre='" + nombre + '\'' +
                ", precio=" + precio +
                '}';
    }
}
