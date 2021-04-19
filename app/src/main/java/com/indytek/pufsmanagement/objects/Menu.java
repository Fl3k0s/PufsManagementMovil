package com.indytek.pufsmanagement.objects;

import java.util.List;

public class Menu extends Producto{

    private List<Producto> productos;

    public Menu(List<Producto> productos) {
        this.productos = productos;
    }

    public Menu(String nombre, String imagen, float precio, List<Producto> productos) {
        super(nombre, imagen, precio);
        this.productos = productos;
    }

    public Menu() {
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "productos=" + productos + super.toString()+
                '}';
    }


}
