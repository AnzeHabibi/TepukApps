package com.example.tepukapps;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tepukapps.model.Shipping;

public class TrackingAct extends AppCompatActivity {
    public static final String DATA ="data";
    private View connect1,connect2,status2,status3;
    private TextView nomerOrder, estimasi;
    private SharedPreferences preferences;
    private String status ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        init();
        getData();
    }

    private void getData() {
        Shipping shipping = getIntent().getParcelableExtra(DATA);
        if (shipping != null) {
            estimasi.setText(shipping.getEstimate());
            nomerOrder.setText(shipping.getPayment().getCodePayment());
            status = shipping.getStatus();
            if (status.equals("kirim")){
                connect1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                status2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }else if (status.equals("terima")){
                connect1.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                status2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                connect2.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                status3.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
            }
        }
    }

    private void init() {
        preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        connect1 = findViewById(R.id.connect1);
        connect2 = findViewById(R.id.connect2);
        status2 = findViewById(R.id.status2);
        status3 = findViewById(R.id.status3);
        estimasi = findViewById(R.id.estimasi);
        nomerOrder = findViewById(R.id.nomororder1);
    }
}
