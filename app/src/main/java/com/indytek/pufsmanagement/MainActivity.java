package com.indytek.pufsmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Codigo creado por Android studio
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_nuevoPedido, R.id.nav_pedidos, R.id.nav_sobreNosotros)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //fin del codigo autogenerado



        Intent productos = new Intent(getApplicationContext(), Productos.class);
        Intent perfil = new Intent(getApplicationContext(), Perfil.class);

        ImageButton perfilBtn = navigationView.getHeaderView(0).findViewById(R.id.profilePhoto);

        perfilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(perfil);
            }
        });

        findViewById(R.id.hambuguesas).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productos.putExtra("tipo","hamburguesas");
                startActivity(productos);
            }
        });
        findViewById(R.id.perritos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productos.putExtra("tipo","perritos");
                startActivity(productos);
            }
        });
        findViewById(R.id.raciones).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productos.putExtra("tipo","raciones");
                startActivity(productos);
            }
        });
        findViewById(R.id.bebida).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productos.putExtra("tipo","bebida");
                startActivity(productos);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}