package com.indytek.pufsmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.indytek.pufsmanagement.identificacion.PollClient;
import com.indytek.pufsmanagement.objects.Direccion;
import com.indytek.pufsmanagement.objects.Persona;
import com.indytek.pufsmanagement.objects.PersonaSerialize;
import com.indytek.pufsmanagement.objects.Rango;
import com.indytek.pufsmanagement.objects.Usuario;
import com.indytek.pufsmanagement.objects.UsuarioSerilize;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Registro extends AppCompatActivity {
    Intent intAbrirLogIn;
    PollClient apiService;
    EditText username, nombre, contrasenia, contraseniaR, apellido1, apellido2, dni, calle, portal, numero, piso, mail, puerta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        //Declaramos los EditText para el registro del usuario
        username = findViewById(R.id.editTextUser);
        nombre = findViewById(R.id.editTextNombre);
        contrasenia = findViewById(R.id.editTextPass);
        contraseniaR = findViewById(R.id.editTextPassR);
        apellido1 = findViewById(R.id.editTextApellido1);
        apellido2 = findViewById(R.id.editTextApellido2);
        dni = findViewById(R.id.editTextDni);
        calle = findViewById(R.id.editTextCalle);
        portal = findViewById(R.id.editTextPortal);
        numero = findViewById(R.id.editTextNumero);
        piso = findViewById(R.id.editTextPiso);
        mail = findViewById(R.id.editTextEmail);
        puerta = findViewById(R.id.editTextPuerta);

        intAbrirLogIn  = new Intent(Registro.this, NewMainActivity.class);

        apiService = new PollClient();
        findViewById(R.id.buttonRegistrerOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Integer pisoInt = 0;
                System.out.println(piso.getText().toString());
                String pisoStrg = piso.getText().toString();
                System.out.println(pisoStrg);
                if (!pisoStrg.equals("")){
                    pisoInt = Integer.getInteger(pisoStrg);
                }
                System.out.println(pisoInt);

                Direccion direccion = Direccion.builder()
                        .id(0)
                        .calle(calle.getText().toString())
                        .numero(calle.getText().toString())
                        .piso(piso.getText().toString())
                        .puerta(puerta.getText().toString())
                        .portal(portal.getText().toString())
                        .build();

                if (contrasenia.getText().toString().equals(contraseniaR.getText().toString())){
                    PersonaSerialize persona = PersonaSerialize.builder()
                            .dni(dni.getText().toString())
                            .name(nombre.getText().toString())
                            .email(mail.getText().toString())
                            .secondName1(apellido1.getText().toString())
                            .secondName2(apellido2.getText().toString())
                            .build();
                    System.out.println(persona);
                    UsuarioSerilize u1 = UsuarioSerilize.builder()
                            .username(username.getText().toString())
                            .password(contrasenia.getText().toString())
                            .direccion(direccion)
                            .rango(Rango.BRONCE)
                            .orders(new ArrayList<>())
                            .person(persona)
                            .build();

                    //Le mostramos el error de base de datos al usuario
                    if(calle.getText().toString().length() > 30){
                        Toast.makeText(getApplicationContext(),
                                "Registro fallido. La calle es muy larga", Toast.LENGTH_SHORT).show();
                    }

                    if (numero.getText().toString().length() > 10){
                        Toast.makeText(getApplicationContext(),
                                "Registro fallido. El numero es muy largo", Toast.LENGTH_SHORT).show();
                    }

                    if(portal.getText().toString().length() > 5){
                        Toast.makeText(getApplicationContext(),
                                "Registro fallido. El portal es muy largo", Toast.LENGTH_SHORT).show();
                    }

                    if(puerta.getText().toString().length() > 5){
                        Toast.makeText(getApplicationContext(),
                                "Registro fallido. La es muy larga", Toast.LENGTH_SHORT).show();
                    }

                    registro(u1);
                }else {
                    Toast toast1 =
                            Toast.makeText(getApplicationContext(),
                                    "Registro fallido. Las contraseñas no coinciden", Toast.LENGTH_SHORT);

                    toast1.show();
                }




            }

        });


    }

    public void registro(UsuarioSerilize u){
        apiService.newPubsRegister(u).observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                Perfil.usuario = usuario;
                System.out.println(usuario);
                startActivity(intAbrirLogIn);
            }
        });
    }
}