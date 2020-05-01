package com.example.tepukapps.model;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tepukapps.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class List_pupuk_track_adapter extends RecyclerView.Adapter<List_pupuk_track_adapter.ListViewHolder>{
    @NonNull

    private ArrayList<Pupuk_track> listPupuk;
    public List_pupuk_track_adapter(ArrayList<Pupuk_track> list) {
        this.listPupuk = list;
    }

    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        TextView tvName, tvDetail;
        ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_photo);
            tvName = itemView.findViewById(R.id.tv_item_name);
            tvDetail = itemView.findViewById(R.id.tv_item_detail);
        }
    }

}
