package com.indytek.pufsmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.indytek.pufsmanagement.identificacion.PollClient;
import com.indytek.pufsmanagement.objects.Rango;
import com.indytek.pufsmanagement.objects.Usuario;

import java.util.HashSet;

public class Registro extends AppCompatActivity {
    Intent intAbrirLogIn;
    PollClient apiService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        intAbrirLogIn  = new Intent(Registro.this, MainActivity.class);

        apiService = new PollClient();
        findViewById(R.id.buttonRegistrerOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario u1 = Usuario.builder()
                        .username("aAguado63")
                        .password("retractil")
                        .direccion(null)
                        .rango(Rango.BRONCE)
                        .orders(new HashSet<>())
                        .persona(null)
                        .build();
                registro(u1);
            }

        });


    }

    public void registro(Usuario u){
        apiService.getRegister(u).observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                Perfil.usuario = usuario;
                startActivity(intAbrirLogIn);
            }
        });
    }
}