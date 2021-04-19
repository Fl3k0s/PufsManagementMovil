package com.indytek.pufsmanagement.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.indytek.pufsmanagement.R;
import com.indytek.pufsmanagement.objects.Producto;

import java.util.ArrayList;
import java.util.List;

public class Adaptador extends RecyclerView.Adapter<Adaptador.ViewHolder> {

    private List<Producto> mProductos;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    Adaptador(Context context) {
        this.mInflater = LayoutInflater.from(context);
        //mData = new ArrayList<String>();
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.adaptador_activity, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        
        //holder.tvInfo.setText(info);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mProductos.size();
    }

    public void addData(ArrayList<String> info, ArrayList<ImageButton>buttons) {

        notifyDataSetChanged();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageButton[] productos = new ImageButton[2];
        TextView[] txtProductos = new TextView[2];


        ViewHolder(View itemView) {
            super(itemView);
            productos[0] = itemView.findViewById(R.id.prod1);
            productos[1] = itemView.findViewById(R.id.prod2);
            txtProductos[0] = itemView.findViewById(R.id.txtProd1);
            txtProductos[1] = itemView.findViewById(R.id.txtProd2);
            itemView.setOnClickListener(this);
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