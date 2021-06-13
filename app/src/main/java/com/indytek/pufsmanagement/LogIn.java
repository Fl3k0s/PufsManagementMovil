package com.indytek.pufsmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;


import com.indytek.pufsmanagement.identificacion.LoginRequest;
import com.indytek.pufsmanagement.identificacion.LoginResponse;
import com.indytek.pufsmanagement.identificacion.PollClient;
import com.indytek.pufsmanagement.identificacion.PostResponse;
import com.indytek.pufsmanagement.identificacion.SesionManager;
import com.indytek.pufsmanagement.objects.Direccion;
import com.indytek.pufsmanagement.objects.Persona;
import com.indytek.pufsmanagement.objects.Rango;
import com.indytek.pufsmanagement.objects.Usuario;

import java.util.HashSet;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/*
Clase del login
 */
public class LogIn extends AppCompatActivity {
    PollClient apiClient;
    SesionManager sesion;
    EditText user, pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final Intent intAbrirMain = new Intent(LogIn.this, NewMainActivity.class);
        final Intent intAbrirRegistro = new Intent(LogIn.this, Registro.class);

        user = findViewById(R.id.editTextPayMethod);
        pass = findViewById(R.id.editChange);

        apiClient = new PollClient();
        //esto es para el token pero si no carga la api no funciona

        //sesion = new SesionManager(this);

        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiClientLoginRequest(intAbrirMain);
            }
        });

        findViewById(R.id.buttonRegistrerOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(intAbrirRegistro);
            }
        });
    }

    private void apiClientLoginRequest(Intent intAbrirMain) {
        //hace llamada a una api, para hacer las pruebas está comentado aposta
        //magia negra
        apiClient.getLogin(user.getText().toString(),pass.getText().toString())
        .observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                Perfil.usuario = usuario;
                System.out.println(usuario);
                startActivity(intAbrirMain);

            }
        });

        //para hacer la prueba sin la api
        /*if(user.getText().toString().equals("admin")&&
                pass.getText().toString().equals("admin"))
            Perfil.usuario = new Usuario(0,"admin","admin", Rango.PLATINO, new Direccion(), new HashSet<>(),new Persona());
        startActivity(intAbrirMain);*/
    }


}