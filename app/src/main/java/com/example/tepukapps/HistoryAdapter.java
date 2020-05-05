package com.example.tepukapps;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
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
        status = shipping.getStatus();
        holder.status.setText(status);
        holder.kodePupuk.setText(shipping.getPayment().getCodePayment());
        holder.hargaPupuk.setText(shipping.getPayment().getAmmountPayment());
        if (!status.equals("terima")){
            holder.bgStatus.setBackgroundColor(Color.RED);
        }
        holder.datePupuk.setText(shipping.getDate());
        holder.cvPupuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,DetailsHistory.class);
                intent.putExtra(DetailsHistory.DATA,shippings.get(position));
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
            hargaPupuk = itemView.findViewById(R.id.hargaPupuk);
            hargaPupuk = itemView.findViewById(R.id.totalPupuk);
            datePupuk = itemView.findViewById(R.id.datePupuk);
            cvPupuk = itemView.findViewById(R.id.cvHistory);
        }
    }
}
