package com.indytek.pufsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.indytek.pufsmanagement.adaptadores.ProductsAdapter;
import com.indytek.pufsmanagement.objects.Producto;

import java.util.ArrayList;

public class Productos extends AppCompatActivity {

    ProductsAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productos);
        RecyclerView recyclerView = findViewById(R.id.rview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adaptador = new ProductsAdapter(this);
        recyclerView.setAdapter(adaptador);
        cargarProductos();
        findViewById(R.id.moreProducts).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarProductos();
            }
        });
    }


    public void cargarProductos(){
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Producto("prod","imagen",12));
        productos.add(new Producto("prod","imagen",12));
        productos.add(new Producto("prod","imagen",12));
        productos.add(new Producto("prod","imagen",12));
        adaptador.addData(productos);
    }
}
