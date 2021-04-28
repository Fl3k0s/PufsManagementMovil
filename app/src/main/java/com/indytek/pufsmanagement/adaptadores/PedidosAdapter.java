package com.indytek.pufsmanagement.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.indytek.pufsmanagement.R;
import com.indytek.pufsmanagement.objects.Pedido;
import com.indytek.pufsmanagement.objects.Producto;

import java.util.ArrayList;
import java.util.List;
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
        View view = mInflater.inflate(R.layout.products_adapter_activity, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Pedido prod = mPedidos.get(position);

        //establecemos el tamaño maximo de la imagen
        holder.productos.setMaxHeight(120);
        holder.productos.setMaxWidth(120);

        //con esto lo que hacemos es poner en el ImageButton la ruta de la foto en internet
        //Glide.with(holder.productos.getContext()).load(prod.getImagen()).into(holder.productos);

        //le ponemos el nombre del producto que corresponda
        //holder.txtProductos.setText(prod.getNombre());
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
        ImageButton productos;
        TextView txtProductos;


        ViewHolder(View itemView) {
            super(itemView);
            productos = itemView.findViewById(R.id.imgProd);
            txtProductos = itemView.findViewById(R.id.txtProd);
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