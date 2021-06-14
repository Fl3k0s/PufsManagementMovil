package com.indytek.pufsmanagement.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.indytek.pufsmanagement.Carrito;
import com.indytek.pufsmanagement.R;
import com.indytek.pufsmanagement.objects.Producto;

import java.util.ArrayList;
import java.util.List;

public class CarritoAdapter extends RecyclerView.Adapter<CarritoAdapter.ViewHolder> {

    private List<Producto> mProductos;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public CarritoAdapter(Context context) {
        this.mInflater = LayoutInflater.from(context);
        mProductos = new ArrayList<>();
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.carrito_adapter, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Producto prod = mProductos.get(position);


        //establecemos el tamaño maximo de la imagen
        holder.productoImg.setMaxHeight(50);
        holder.productoImg.setMaxWidth(50);

        //con esto lo que hacemos es poner en el ImageButton la ruta de la foto en internet
        Glide.with(holder.productoImg.getContext()).load(prod.getUrl_product()).into(holder.productoImg);

        //le ponemos el nombre del producto que corresponda
        holder.productoTxt.setText(prod.getNombre());
        holder.productoPrecio.setText(prod.getPrecio()+ "€");
        holder.p = prod;

        //TODO habilitar el onClick cuando pongamos el boton de descartar
        //holder.setOnClickListeners();
    }





    // total number of rows
    @Override
    public int getItemCount() {
        return mProductos.size();
    }

    public void addData(ArrayList<Producto> info) {
        mProductos.addAll(info);
        notifyDataSetChanged();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView productoImg;
        TextView productoTxt, productoPrecio;
        Context context;
        Producto p;
        ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            productoImg = itemView.findViewById(R.id.productoImg);
            productoTxt = itemView.findViewById(R.id.nombreProd);
            productoPrecio = itemView.findViewById(R.id.precioProd);
        }



        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    Producto getItem(int id) {
        return mProductos.get(id);
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