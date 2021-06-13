package com.indytek.pufsmanagement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.indytek.pufsmanagement.adaptadores.CarritoAdapter;
import com.indytek.pufsmanagement.objects.Producto;

import java.lang.reflect.Array;
import java.util.ArrayList;

import lombok.var;

/*
Actividad del carrito de compra
 */
public class Carrito extends AppCompatActivity {

    public static ArrayList<Producto> productos = new ArrayList<>();
    Button more;
    TextView txt, precio;
    CarritoAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        RecyclerView recyclerView = findViewById(R.id.listOfCarrito);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptador = new CarritoAdapter(this);
        recyclerView.setAdapter(adaptador);
        txt = findViewById(R.id.empty);
        precio = findViewById(R.id.textPrecioCarrito);
        if (productos.isEmpty()){
            txt.setText("Aun no hay productos");
            precio.setText(0 + "");
        }
        else{
            txt.setText("");
            cargarCarrito();
            var preciof = productos.stream().map(Producto::getPrecio)
                    .reduce((a,b)-> a + b).orElse(0f);
            precio.setText(preciof + "");
        }



        more = findViewById(R.id.buttonAddCarrito);
        final Intent i = new Intent(Carrito.this, NewMainActivity.class);
        final Intent finishIOrder = new Intent(Carrito.this, PagoActivity.class);
        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(i);
            }
        });

        findViewById(R.id.buttonCancelCarrito).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                productos.clear();
                startActivity(i);
            }
        });

        findViewById(R.id.buttonPagarCarrito).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(finishIOrder);
            }
        });
    }

    public void cargarCarrito(){
            adaptador.addData(productos);
    }
}