package com.example.tepukapps;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tepukapps.model.Pupuk;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PupukAdapter extends RecyclerView.Adapter<PupukAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Pupuk> pupuks;

    public PupukAdapter(Context context, ArrayList<Pupuk> pupuks) {
        this.context = context;
        this.pupuks = pupuks;
    }

    @NonNull
    @Override
    public PupukAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pupuk_item,parent,false);
        return new PupukAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PupukAdapter.ViewHolder holder, int position) {
        Pupuk pupuk = pupuks.get(position);
        Picasso.get().load(Constant.URL+"storage/pupuk/"+pupuk.getPhoto()).into(holder.fotoPupuk);
        holder.namaPupuk.setText(pupuk.getName());
        holder.hargaPupuk.setText(Integer.toString(pupuk.getPrice()));

        final String getNamaPupuk = pupuk.getName();
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent detail = new Intent(context, DetailAct.class);
                detail.putExtra("nama_pupuk", getNamaPupuk);
                context.startActivity(detail);
            }
        });

    }

    @Override
    public int getItemCount() {
        return pupuks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView namaPupuk ;
        TextView hargaPupuk;
        ImageView fotoPupuk;
        Button btnLihat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            namaPupuk = itemView.findViewById(R.id.namaPupuk);
            hargaPupuk = itemView.findViewById(R.id.hargaPupuk);
            fotoPupuk = itemView.findViewById(R.id.imagePupuk);
            btnLihat = itemView.findViewById(R.id.btnLihat);

            btnLihat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detail = new Intent(context, DetailAct.class);
                    context.startActivity(detail);
                }
            });


        }

    }
}
