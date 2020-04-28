package com.example.tepukapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailAct extends AppCompatActivity {

    Button btn_continue;
    LinearLayout btn_back;
    ImageView img_pupuk;
    TextView txt_harga, txt_stok, txt_jenis, txt_komposisi, txt_desc, title_pupuk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        btn_continue = findViewById(R.id.btn_continue);
        txt_harga=findViewById(R.id.txt_harga);
        txt_desc=findViewById(R.id.txt_desc);
        txt_jenis=findViewById(R.id.txt_jenis);
        txt_komposisi=findViewById(R.id.txt_komposisi);
        txt_stok=findViewById(R.id.txt_stok);
        title_pupuk=findViewById(R.id.title_pupuk);
        img_pupuk=findViewById(R.id.img_pupuk);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(DetailAct.this, GetStarted.class);
                startActivity(back);
            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent go = new Intent(DetailAct.this, PaymentAct.class);
                startActivity(go);
            }
        });
    }
}
