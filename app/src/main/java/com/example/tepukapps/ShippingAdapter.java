package com.example.tepukapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tepukapps.model.Shipping;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ShippingAdapter extends RecyclerView.Adapter<ShippingAdapter.ListViewHolder>{

    private ArrayList<Shipping> shippings;
    private Context context;
    private SharedPreferences trackPref;

    public ShippingAdapter(ArrayList<Shipping> shippings, Context context) {
        this.shippings = shippings;
        this.context = context;

    }

    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.tracking_item, parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, final int position) {
        trackPref = context.getApplicationContext().getSharedPreferences("track",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = trackPref.edit();
        Shipping shipping = shippings.get(position);
        if (!shipping.getStatus().equals("hapus")){
            final String code = shipping.getPayment().getCodePayment();
            holder.kodePemesanan.setText(code);
            holder.ekspedisiShipping.setText(shipping.getKurir());
            holder.datePemesanan.setText(shipping.getDate());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    editor.putString("code", code);
                    editor.apply();
                    Intent intent = new Intent(context, TrackingAct.class);
                    intent.putExtra(TrackingAct.DATA, shippings.get(position));
                    context.startActivity(intent);
                }
            });
        }else {
            removeAt(position);
        }

    }

    @Override
    public int getItemCount() {
        return shippings.size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        TextView kodePemesanan, datePemesanan ,ekspedisiShipping;
        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            ekspedisiShipping = itemView.findViewById(R.id.ekspedisiShipping);
            kodePemesanan = itemView.findViewById(R.id.kodePemesanan);
            datePemesanan = itemView.findViewById(R.id.tanggalPemesanan);
        }
    }
    public void removeAt(int position) {
        shippings.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, shippings.size());
    }

}
