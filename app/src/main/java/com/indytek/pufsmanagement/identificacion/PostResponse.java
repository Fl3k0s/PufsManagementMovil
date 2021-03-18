package com.indytek.pufsmanagement.identificacion;

import java.util.List;

public class PostResponse {
    private int status;

    private String mensaje;

    private List<Post> posts;

    public PostResponse() {
    }

    public PostResponse(int status, String mensaje, List<Post> posts) {
        this.status = status;
        this.mensaje = mensaje;
        this.posts = posts;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }
}


