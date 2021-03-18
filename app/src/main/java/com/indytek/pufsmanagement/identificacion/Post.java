package com.indytek.pufsmanagement.identificacion;

public class Post {
    private int id;

    private String titulo;

    private String descripcion;

    private String contexto;

    public Post(int id, String titulo, String descripcion, String contexto) {
        this.id = id;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.contexto = contexto;
    }

    public Post() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getContexto() {
        return contexto;
    }

    public void setContexto(String contexto) {
        this.contexto = contexto;
    }
}
