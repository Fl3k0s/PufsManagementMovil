package com.indytek.pufsmanagement;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.indytek.pufsmanagement.objects.Producto;

public class VistaPreviaActivity extends AppCompatActivity {

    public static Producto producto = new Producto();

    Button cancel, add;
    TextView productoName, precio, rango;
    ImageView productoImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista_previa);

        //inicializamos las variables
        productoImg = findViewById(R.id.imagenProducto);
        cancel = findViewById(R.id.calcelProducto);
        add = findViewById(R.id.aniadirProducto);
        productoName = findViewById(R.id.nombreProducto);
        precio = findViewById(R.id.precioProducto);
        rango = findViewById(R.id.rangoProducto);

        //con esto lo que hacemos es poner en el ImageButton la ruta de la foto en internet
        Glide.with(getBaseContext()).load(producto.getUrl_product()).into(productoImg);
        //le damos tamaño a la imagen del producto




        //ponemos los textos correspondientes
        productoName.setText(producto.getNombre());
        precio.setText(producto.getPrecio()+ "€");
        rango.setText(producto.getTipo().toString());

        Intent intentCarrito = new Intent(VistaPreviaActivity.this, Carrito.class);

        //acciones de los botones
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                producto = new Producto();
                finish();
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Carrito.productos.add(producto);
                startActivity(intentCarrito);
            }
        });
    }
}