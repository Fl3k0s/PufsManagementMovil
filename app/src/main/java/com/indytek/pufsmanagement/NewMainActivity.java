package com.indytek.pufsmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.Observer;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.navigation.NavigationView;
import com.indytek.pufsmanagement.adaptadores.ProductsAdapter;
import com.indytek.pufsmanagement.identificacion.PollClient;
import com.indytek.pufsmanagement.identificacion.SesionManager;
import com.indytek.pufsmanagement.objects.Producto;
import com.indytek.pufsmanagement.objects.Tipo;

import java.util.List;

public class NewMainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    ProductsAdapter adaptador;
    PollClient apiClient;
    SesionManager sesion;
    List<Producto> productoList;
    Tipo type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Codigo creado por Android studio
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_main);
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
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment2);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        //fin del codigo autogenerado

        RecyclerView recyclerView = findViewById(R.id.productosView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adaptador = new ProductsAdapter(this);
        recyclerView.setAdapter(adaptador);

        apiClient = new PollClient();

        Intent productos = new Intent(getApplicationContext(), Productos.class);
        Intent perfil = new Intent(getApplicationContext(), Perfil.class);

        ImageView perfilBtn = navigationView.getHeaderView(0).findViewById(R.id.profilePhoto);
        TextView user = navigationView.getHeaderView(0).findViewById(R.id.nameOfUser),
                mail = navigationView.getHeaderView(0).findViewById(R.id.emailOfUser);

        user.setText(Perfil.usuario.getUsername());
        mail.setText(Perfil.usuario.getPersona().getEmail());

        perfilBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(perfil);
            }
        });
        cargarComida();

        findViewById(R.id.btnBebida).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaptador.clearData();
                cargarProductos(Tipo.BEBIDA);
            }
        });

        findViewById(R.id.btnComida).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarComida();
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
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment2);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void cargarComida(){
        adaptador.clearData();
        cargarProductos(Tipo.HAMBURGUESA);
        cargarProductos(Tipo.PATATAS);
        cargarProductos(Tipo.ENTRANTE);
        cargarProductos(Tipo.PLATO);
    }
    public void cargarProductos(Tipo type){

        apiClient.getProductos(type, Perfil.usuario.getRango())
                .observe(this, new Observer<List<Producto>>() {
                    @Override
                    public void onChanged(List<Producto> productos) {
                        productos.forEach(System.out::println);
                        adaptador.addData(productos);
                    }
                });


    }
}