package com.example.tepukapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class History extends AppCompatActivity {

    RecyclerView recyclerView;

    String s1[], s2[], s3[], s4[];
    int images[] = {R.drawable.gandasilb,R.drawable.gandasild,R.drawable.kcl,R.drawable.ponska,R.drawable.sp36,R.drawable.tsp,R.drawable.urea,R.drawable.za};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerView);
        s1 = getResources().getStringArray(R.array.macam_pupuk);
        s2 = getResources().getStringArray(R.array.deskripsi);
        s3 = getResources().getStringArray(R.array.harga);
        s4 = getResources().getStringArray(R.array.kode);
        HistoryAdapter myAdapter = new HistoryAdapter(this, s1, s2, s3, s4, images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
