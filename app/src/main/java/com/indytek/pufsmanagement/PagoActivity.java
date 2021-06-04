package com.indytek.pufsmanagement;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.indytek.pufsmanagement.identificacion.PollClient;
import com.indytek.pufsmanagement.objects.MetodoDePago;
import com.indytek.pufsmanagement.objects.Pedido;
import com.indytek.pufsmanagement.objects.Producto;
import com.indytek.pufsmanagement.objects.Tipo;

import java.time.LocalDateTime;

import lombok.var;

public class PagoActivity extends AppCompatActivity {

    TextView precioTotal, leDevuelven;
    Spinner metodoDePago;
    EditText cambio;
    Button pagar;
    MetodoDePago pago;
    PollClient apiClient;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pago);

        apiClient = new PollClient();

        precioTotal = findViewById(R.id.precioTotal);
        leDevuelven = findViewById(R.id.cambioARecibir);
        metodoDePago = findViewById(R.id.metodoPago);
        cambio = findViewById(R.id.cuantoPaga);
        pagar = findViewById(R.id.finish);

        ArrayAdapter<CharSequence> metodosDePago = ArrayAdapter.createFromResource(this,
                R.array.metodoDePago,android.R.layout.simple_spinner_dropdown_item);

        metodoDePago.setAdapter(metodosDePago);

        var preciof = Carrito.productos.stream().map(Producto::getPrecio)
                .reduce((a,b)-> a + b).orElse(0f);
        precioTotal.setText(preciof + "€");

        metodoDePago.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (metodoDePago.getSelectedItem().toString().equalsIgnoreCase("visa")){
                    cambio.setText(preciof + "");
                    cambio.setFocusable(false);
                    pago = MetodoDePago.VISA;
                    leDevuelven.setText(preciof - Float.parseFloat(cambio.getText().toString()) + "€");

                }else {
                    //
                    cambio.setText("0");
                    cambio.setFocusableInTouchMode(true);
                    leDevuelven.setText(preciof - Float.parseFloat(cambio.getText().toString()) + "€");
                    pago = MetodoDePago.EFECTIVO;
                    cambio.setText("");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                if (metodoDePago.getSelectedItem().toString().equalsIgnoreCase("visa")){
                    cambio.setText(preciof+"");
                    cambio.setFocusable(false);
                    leDevuelven.setText(preciof - Float.parseFloat(cambio.getText().toString()) + "€");
                }else {
                    cambio.setText("0");
                    cambio.setFocusableInTouchMode(true);
                    leDevuelven.setText(preciof - Float.parseFloat(cambio.getText().toString()) + "€");
                    cambio.setText("");
                }
            }
        });




        cambio.setText("0");
        leDevuelven.setText(preciof - Float.parseFloat(cambio.getText().toString()) + "€");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("¿Seguro?");
        builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // Acción a realizar
                dialog.dismiss();
                Intent i= new Intent(getApplicationContext(),MainActivity.class);


                //TODO: Incorporar la creacion del pedido y mandarlo a la API
                Pedido pedido = Pedido.builder().id(0)
                        .username(Perfil.usuario.getUsername())
                        .dateOrdered(null)
                        .android(true)
                        .precio(preciof)
                        .pay(Float.parseFloat(cambio.getText().toString()))
                        .exchange(preciof - Float.parseFloat(cambio.getText().toString()))
                        .notes("")
                        .payMethod(pago)
                        .products(Carrito.productos)
                        .build();
                Carrito.productos.clear();
                hacerPedido(pedido);

                startActivity(i);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }


    public void hacerPedido(Pedido pedido){
        apiClient.getNuevoPedido(pedido).observe(this, new Observer<Pedido>() {
            @Override
            public void onChanged(Pedido pedido) {
                System.out.println(pedido);
            }
        });
    }
}