package com.example.tepukapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tepukapps.model.Shipping;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {
    private ArrayList<Shipping> shippings;
    private Context context;
    private String status;
    private SharedPreferences historyPref;

    public HistoryAdapter(ArrayList<Shipping> shippings, Context context) {
        this.shippings = shippings;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.history_row, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final Shipping shipping = shippings.get(position);
        historyPref = context.getSharedPreferences("history",Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = historyPref.edit();
        final String kode = shipping.getPayment().getCodePayment();
        final int harga = shipping.getPayment().getAmmountPayment();
        final int id = shipping.getPayment().getId();

        status = shipping.getStatus();
        holder.status.setText(status);
        holder.kodePupuk.setText(kode);
        holder.hargaPupuk.setText("RP."+harga);
        if (!status.equals("terima")){
            holder.bgStatus.setBackgroundColor(Color.RED);
        }else {
            holder.bgStatus.setBackgroundColor(Color.GREEN);
        }
        holder.datePupuk.setText(shipping.getDate());
        holder.cvPupuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editor.putString("kode",kode);
                editor.putInt("harga",harga);
                editor.putInt("id",id);
                editor.apply();
                Intent intent = new Intent(context,DetailsHistory.class);
                intent.putExtra(DetailsHistory.DATA,shippings.get(position));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return shippings.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView kodePupuk, hargaPupuk,datePupuk,status;
        CardView cvPupuk;
        ImageView bgStatus;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bgStatus = itemView.findViewById(R.id.bgStatus);
            status = itemView.findViewById(R.id.tvStatus);
            kodePupuk = itemView.findViewById(R.id.kodePupuk);
            hargaPupuk = itemView.findViewById(R.id.totalHarga);
            datePupuk = itemView.findViewById(R.id.datePupuk);
            cvPupuk = itemView.findViewById(R.id.cvHistory);
        }
    }
}
