package com.example.tepukapps;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tepukapps.model.Order;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Order> orders;
    private int total ,pupuk;

    public OrderAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.orders = orders;
        for (int i = 0;i<orders.size();i++){
            Order order = orders.get(i);
            int qty = order.getTotal();
            total += qty;
            Log.d("kahla", String.valueOf(total));
        }
        SharedPreferences userPref = context.getSharedPreferences("order",MODE_PRIVATE);
        SharedPreferences.Editor editor = userPref.edit();
        editor.putInt("totalPupuk",orders.size());
        editor.putInt("totalPayment",total);
        editor.apply();

    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item,parent,false);
        return new OrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, int position) {
        Order order = orders.get(position);
        Log.d("test2", order.getPupukOrder().getName());
        Picasso.get().load(Constant.URL+"storage/pupuk/"+order.getPupukOrder().getPhoto()).into(holder.imageView);
        holder.textJudul.setText("Pupuk "+order.getPupukOrder().getName());
        int qty = order.getQuantity();
        pupuk += qty;
        int harga = order.getPupukOrder().getPrice();
        total += harga;
        holder.textHarga.setText("Harga : " + harga);
        holder.textQty.setText("Jumlah Dibeli :" + (qty));
        holder.textTotal.setText("Rp. " + (order.getTotal()));

    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textJudul,textHarga,textTotal,textQty;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageCart);
            textJudul = itemView.findViewById(R.id.textCart);
            textHarga = itemView.findViewById(R.id.hargaCart);
            textTotal = itemView.findViewById(R.id.totalCart);
            textQty = itemView.findViewById(R.id.qtyCart);
        }
    }
}
