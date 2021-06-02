package com.indytek.pufsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.indytek.pufsmanagement.adaptadores.PedidosAdapter;
import com.indytek.pufsmanagement.adaptadores.ProductsAdapter;
import com.indytek.pufsmanagement.identificacion.PollClient;
import com.indytek.pufsmanagement.identificacion.SesionManager;
import com.indytek.pufsmanagement.objects.Pedido;
import com.indytek.pufsmanagement.objects.Persona;
import com.indytek.pufsmanagement.objects.Producto;
import com.indytek.pufsmanagement.objects.Rango;
import com.indytek.pufsmanagement.objects.Tipo;
import com.indytek.pufsmanagement.objects.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
Actividad de la lista de pedidos
 */
public class Pedidos extends AppCompatActivity {
    PollClient apiClient;
    SesionManager sesion;
    List<Pedido> listaDePedidos = new ArrayList<>();

    PedidosAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);
        apiClient = new PollClient();

        RecyclerView recyclerView = findViewById(R.id.listViewPedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptador = new PedidosAdapter(this);
        recyclerView.setAdapter(adaptador);
        pedidosApi();

        findViewById(R.id.exitPedidos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    public void pedidosApi(){
        apiClient.getPedidos(Perfil.usuario.getUsername())
                .observe(this, new Observer<List<Pedido>>() {
                    @Override
                    public void onChanged(List<Pedido> pedidos) {
                        pedidos.forEach(System.out::println);
                        adaptador.addData(pedidos);

                    }
                });
        listaDePedidos.forEach(System.out::println);

    }

    public void cargarPedidos(){

    }
}