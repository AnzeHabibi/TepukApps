package com.example.tepukapps;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailsHistory extends AppCompatActivity {

    ImageView mainImageView;
    TextView title, deskripsi, harga, kode;

    String data1, data2, data3, data4;
    int myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_history);

        mainImageView = findViewById(R.id.mainImageView);
        title = findViewById(R.id.title);
        deskripsi = findViewById(R.id.deskripsi);
        harga = findViewById(R.id.harga);
        kode = findViewById(R.id.kode);


        getData();
        setData();
    }
    private void setData(){
        title.setText(data1);
        deskripsi.setText(data2);
        harga.setText(data3);
        kode.setText(data4);
        mainImageView.setImageResource(myImage);
    }

    private void getData(){
        if(getIntent().hasExtra("myImage") && getIntent().hasExtra("data1") && getIntent().hasExtra("data2")
                && getIntent().hasExtra("data3") && getIntent().hasExtra("data4")){
            data1 = getIntent().getStringExtra("data1");
            data2 = getIntent().getStringExtra("data2");
            data3 = getIntent().getStringExtra("data3");
            data4 = getIntent().getStringExtra("data4");
            myImage = getIntent().getIntExtra("myImage", 1);
        }else {
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }


}