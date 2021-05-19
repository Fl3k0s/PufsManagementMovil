package com.indytek.pufsmanagement.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.indytek.pufsmanagement.R;
import com.indytek.pufsmanagement.objects.Pedido;
import com.indytek.pufsmanagement.objects.Producto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.var;

/*
Adaptador del listado de pedidos
 */
public class PedidosAdapter extends RecyclerView.Adapter<PedidosAdapter.ViewHolder> {

    private List<Pedido> mPedidos;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public PedidosAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        mPedidos = new ArrayList<>();
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.pedidos_adapter, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pedido prod = mPedidos.get(position);
        // declaramos la lista de productos
        var lista = "";
        for (Producto p: prod.getProducts())
            lista += p.getNombre() + ", ";

        // quitamos los ultimos caracteres que tienen para dejarlo bien
        lista = lista.substring(0, lista.length()-2) + ".";

        //establecemos el tamaño maximo de la imagen
        holder.price.setText(prod.getPrecio() + "");
        holder.dateOrderer.setText( prod.getDateOrdered().getDayOfMonth() + "-" + prod.getDateOrdered().getMonthValue() + "-"  + prod.getDateOrdered().getYear());
        holder.listProducts.setText(lista);
        holder.anotherTime.setVisibility(View.VISIBLE);
        if (prod.getDateOrdered().isBefore(LocalDateTime.now().plusMinutes(10)))
            holder.cancel.setVisibility(View.INVISIBLE);
        else holder.cancel.setVisibility(View.VISIBLE);
    }




    // total number of rows
    @Override
    public int getItemCount() {
        return mPedidos.size();
    }

    public void addData(ArrayList<Pedido> info) {
        mPedidos.addAll(info);
        notifyDataSetChanged();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        Button cancel, anotherTime;
        TextView listProducts, price, dateOrderer;


        ViewHolder(View itemView) {
            super(itemView);
            cancel = itemView.findViewById(R.id.cancel);
            anotherTime = itemView.findViewById(R.id.pedirDeNuevo);
            listProducts = itemView.findViewById(R.id.listProducts);
            dateOrderer = itemView.findViewById(R.id.dateOrderer);
            price = itemView.findViewById(R.id.precio);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Pedido getItem(int id) {
        return mPedidos.get(id);
    }

    // allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
}