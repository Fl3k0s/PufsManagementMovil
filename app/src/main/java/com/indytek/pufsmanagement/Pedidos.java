package com.indytek.pufsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.indytek.pufsmanagement.adaptadores.PedidosAdapter;
import com.indytek.pufsmanagement.adaptadores.ProductsAdapter;
import com.indytek.pufsmanagement.objects.Pedido;
import com.indytek.pufsmanagement.objects.Producto;
import com.indytek.pufsmanagement.objects.Rango;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/*
Actividad de la lista de pedidos
 */
public class Pedidos extends AppCompatActivity {

    PedidosAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        RecyclerView recyclerView = findViewById(R.id.listViewPedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptador = new PedidosAdapter(this);
        recyclerView.setAdapter(adaptador);
        cargarPedidos();
        findViewById(R.id.exitPedidos).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void cargarPedidos(){
        ArrayList<Pedido> pedidos = new ArrayList<>();
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto(1,"hamborgesa","https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/media/image/2020/08/hamburguesa-2028707.jpg",12, Rango.ORO));
        productos.add(new Producto(2,"papas","https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/hamburguesa-1590595900.jpg",12,Rango.ORO));
        productos.add(new Producto(3,"perrito","https://sevilla.abc.es/gurme/wp-content/uploads/sites/24/2014/10/hamburguesas-960x540.jpg",12,Rango.ORO));
        productos.add(new Producto(4,"cocacola 1L","https://media.revistagq.com/photos/5f08621564f52a842c7f9a83/master/pass/hamburguesa%20the%20fitzgerald.jpg",12,Rango.ORO));
        pedidos.add(new Pedido(1, LocalDateTime.now(),null,true,10,new HashSet<>(productos)));
        pedidos.add(new Pedido(2, LocalDateTime.now().minusDays(2),null,true,10,new HashSet<>(productos)));
        pedidos.add(new Pedido(3, LocalDateTime.now().minusDays(3),null,true,10,new HashSet<>(productos)));
        pedidos.add(new Pedido(4, LocalDateTime.now().minusDays(7),null,true,10,new HashSet<>(productos)));
        pedidos.add(new Pedido(5, LocalDateTime.now().minusDays(15),null,true,10,new HashSet<>(productos)));
        adaptador.addData(pedidos);
    }
}