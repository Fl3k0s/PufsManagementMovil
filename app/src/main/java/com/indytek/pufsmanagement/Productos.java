package com.indytek.pufsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.indytek.pufsmanagement.adaptadores.ProductsAdapter;
import com.indytek.pufsmanagement.objects.Producto;

import java.util.ArrayList;
/*
Actividad que muestra los productos
 */
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
        productos.add(new Producto(1,"prod","https://cdn.computerhoy.com/sites/navi.axelspringer.es/public/media/image/2020/08/hamburguesa-2028707.jpg",12));
        productos.add(new Producto(2,"prod","https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/hamburguesa-1590595900.jpg",12));
        productos.add(new Producto(3,"prod","https://sevilla.abc.es/gurme/wp-content/uploads/sites/24/2014/10/hamburguesas-960x540.jpg",12));
        productos.add(new Producto(4,"prod","https://media.revistagq.com/photos/5f08621564f52a842c7f9a83/master/pass/hamburguesa%20the%20fitzgerald.jpg",12));
        adaptador.addData(productos);
    }


}
