package com.example.tepukapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tepukapps.model.Shipping;

public class DetailsHistory extends AppCompatActivity {
    public static final String DATA = "data";

    ImageView mainImageView;
    TextView title, harga, kode,status,address,kurir;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_history);
        init();
        getData();
    }

    private void getData() {
        Shipping shipping = getIntent().getParcelableExtra(DATA);
        status.setText(shipping.getStatus());
        kode.setText(shipping.getPayment().getCodePayment());
        harga.setText(shipping.getPayment().getAmmountPayment());
        address.setText(shipping.getUser().getAddress());
    }

    private void init() {
        status = findViewById(R.id.status);
        address = findViewById(R.id.address);
        kurir = findViewById(R.id.kurir);
        harga = findViewById(R.id.harga);
        kode = findViewById(R.id.kode);
    }


}