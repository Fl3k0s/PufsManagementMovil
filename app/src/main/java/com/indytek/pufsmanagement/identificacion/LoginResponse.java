package com.indytek.pufsmanagement.identificacion;

import com.indytek.pufsmanagement.objects.Usuario;

public class LoginResponse {
    private int statusCode;

    private String authToken;

    private Usuario usuario;

    public LoginResponse() {
    }

    public LoginResponse(int statusCode, String authToken, Usuario usuario) {
        this.statusCode = statusCode;
        this.authToken = authToken;
        this.usuario = usuario;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

