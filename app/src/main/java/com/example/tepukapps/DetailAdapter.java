package com.example.tepukapps;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tepukapps.model.History;
import com.example.tepukapps.model.Order;
import com.example.tepukapps.model.Payment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {
    private ArrayList<History>histories;
    private Context context;
    private int total ,pupuk;


    public DetailAdapter(ArrayList<History> histories, Context context) {
        this.histories = histories;
        this.context = context;
    }

    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false);
        return new DetailAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, int position) {
        History order = histories.get(position);
        Log.d("test2", order.getPupukOrder().getName());
        Picasso.get().load(Constant.URL+"storage/pupuk/"+order.getPupukOrder().getPhoto()).into(holder.imageView);
        holder.judul.setText("Pupuk "+order.getPupukOrder().getName());
        int qty = order.getQuantity();
        pupuk += qty;
        int harga = order.getPupukOrder().getPrice();
        total += harga;
        holder.harga.setText("Harga : " + harga);
        holder.qty.setText("Jumlah Dibeli :" + (qty));
        holder.total.setText("Rp. " + (order.getTotal()));
        Log.d("kahla", "im adapter");
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView judul,harga,qty,total;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageCart1);
            judul = itemView.findViewById(R.id.textCart1);
            harga = itemView.findViewById(R.id.hargaCart1);
            qty = itemView.findViewById(R.id.qtyCart1);
            total = itemView.findViewById(R.id.totalCart1);
        }
    }
}
