package com.example.tepukapps;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tepukapps.model.Payment;
import com.example.tepukapps.model.Shipping;

public class TrackingAct extends AppCompatActivity {
    public static final String DATA ="data";
    private View connect1,connect2,status2,status3;
    private TextView nomerOrder, estimasi;
    private SharedPreferences preferences,trackPref;
    private String status ;
    private ImageView btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        trackPref = getSharedPreferences("track",Context.MODE_PRIVATE);
        init();
        getData();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrackingAct.this,HomeActivity.class));

            }
        });
    }

    private void getData() {
        Shipping shipping = getIntent().getParcelableExtra(DATA);
        String code = trackPref.getString("code","");
        if (shipping != null) {
            estimasi.setText(shipping.getEstimate()+" Hari");
            nomerOrder.setText(code);
            status = shipping.getStatus();
            if (status.equals("kirim")){
                connect1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                status2.setBackground(getResources().getDrawable(R.drawable.status2));
            }else if (status.equals("terima")){
                connect1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                status2.setBackground(getResources().getDrawable(R.drawable.status2));
                connect2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                status3.setBackground(getResources().getDrawable(R.drawable.status2));
            }
        }
    }

    private void init() {
        btnBack = findViewById(R.id.backBtn);
        preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        connect1 = findViewById(R.id.connect1);
        connect2 = findViewById(R.id.connect2);
        status2 = findViewById(R.id.status2);
        status3 = findViewById(R.id.status3);
        estimasi = findViewById(R.id.estimasi1);
        nomerOrder = findViewById(R.id.nomororder1);
    }
}
