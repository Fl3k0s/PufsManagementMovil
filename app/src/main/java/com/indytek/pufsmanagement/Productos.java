package com.indytek.pufsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.indytek.pufsmanagement.adaptadores.ProductsAdapter;
import com.indytek.pufsmanagement.identificacion.PollClient;
import com.indytek.pufsmanagement.identificacion.SesionManager;
import com.indytek.pufsmanagement.objects.Cliente;
import com.indytek.pufsmanagement.objects.Producto;
import com.indytek.pufsmanagement.objects.Rango;
import com.indytek.pufsmanagement.objects.Tipo;
import com.indytek.pufsmanagement.objects.Usuario;

import java.util.ArrayList;
import java.util.List;

/*
Actividad que muestra los productos
 */
public class Productos extends AppCompatActivity {

    ProductsAdapter adaptador;
    PollClient apiClient;
    SesionManager sesion;
    List<Producto> productoList;
    Tipo type;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        String tipo =getIntent().getStringExtra("tipo");
        apiClient = new PollClient();

        type= segunTipo(tipo);


        RecyclerView recyclerView = findViewById(R.id.rview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adaptador = new ProductsAdapter(this);
        recyclerView.setAdapter(adaptador);


        cargarProductosApi();

    }

    public void cargarProductosApi(){
        apiClient.getProductos(type, Perfil.usuario.getRango())
                .observe(this, new Observer<List<Producto>>() {
                    @Override
                    public void onChanged(List<Producto> productos) {
                        productos.forEach(System.out::println);
                        adaptador.addData(productos);
                    }
                });


    }

    public Tipo segunTipo(String tipo){
        Tipo t = Tipo.HAMBURGUESA;

        switch (tipo){
            case "hamburguesas":
                t = Tipo.HAMBURGUESA;
                break;
            case "perritos":
                t = Tipo.ENTRANTE;
                break;
            case "raciones":
                t = Tipo.PATATAS;
                break;
            case "bebida":
                t = Tipo.BEBIDA;
                break;
        }

        return t;
    }


    public void cargarProductos(){

    }


}
