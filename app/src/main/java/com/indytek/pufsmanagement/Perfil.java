package com.indytek.pufsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.indytek.pufsmanagement.objects.Direccion;
import com.indytek.pufsmanagement.objects.Usuario;

/*
Actividad del perfil del usuario
 */
public class Perfil extends AppCompatActivity {


    public static Usuario usuario = new Usuario();

    TextView username, nombre, apellidos, email, direccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        username= findViewById(R.id.textViewNick);
        nombre= findViewById(R.id.textViewName);
        apellidos= findViewById(R.id.textViewSurname);
        email= findViewById(R.id.textViewEmail);
        direccion= findViewById(R.id.textViewAddress);

        if (usuario.getPersona().getSecondName1() == null)
            usuario.getPersona().setSecondName1("");

        if (usuario.getPersona().getSecondName2() == null)
            usuario.getPersona().setSecondName2("");

        username.setText(usuario.getUsername());
        nombre.setText(usuario.getPersona().getName());
        apellidos.setText(usuario.getPersona().getSecondName1() + " " + usuario.getPersona().getSecondName2());
        email.setText(usuario.getPersona().getEmail());
        Direccion direccion = usuario.getDireccion();
        this.direccion.setText(direccion.getCalle() + "," +direccion.getNumero() + ", " +direccion.getPortal()
        + ", " + direccion.getPiso() + " " + direccion.getPuerta());


        findViewById(R.id.buttonExitProfile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}