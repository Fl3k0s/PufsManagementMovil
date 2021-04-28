package com.indytek.pufsmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;


import com.indytek.pufsmanagement.identificacion.PollClient;
import com.indytek.pufsmanagement.identificacion.PostResponse;
import com.indytek.pufsmanagement.identificacion.SesionManager;

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

        final Intent intAbrirMain = new Intent(LogIn.this, MainActivity.class);
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

    private void apiClientLoginRequest(final Intent intAbrirMain) {
        //hace llamada a una api, para hacer las pruebas está comentado aposta
        //magia negra
        /*apiClient.getLogin(new LoginRequest(user.getText().toString(),pass.getText().toString()))
        .observe(this, new Observer<LoginResponse>() {
            @Override
            public void onChanged(LoginResponse loginResponse) {
                if (loginResponse.getStatusCode()!= 200){
                    sesion.saveAuthToken(loginResponse.getAuthToken());
                    startActivity(intAbrirMain);
                }
            }
        });*/
        //para hacer la prueba sin la api
        if(user.getText().toString().equals("admin")&&
                pass.getText().toString().equals("admin"))
        startActivity(intAbrirMain);
    }

    public void fetchPosts(){
        apiClient.getApiService().fetchPost("Bearer ${sessionManager.fetchAuthToken()")
        .enqueue(new Callback<PostResponse>() {
            @Override
            public void onResponse(Call<PostResponse> call, Response<PostResponse> response) {

            }

            @Override
            public void onFailure(Call<PostResponse> call, Throwable t) {

            }
        });
    }
}