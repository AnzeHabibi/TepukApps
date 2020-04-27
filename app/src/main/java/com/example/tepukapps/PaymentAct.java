package com.example.tepukapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PaymentAct extends AppCompatActivity {
    LinearLayout btn_back_detail;
    Button btn_payment, btn_pls, btn_min;
    TextView jmlpupuk, txttotalharga;
    Integer valueJumlahpupuk = 1;
    Double valuetotalharga = 0.0;
    Double hargapupuk = 44.000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        btn_pls=findViewById(R.id.btn_pls);
        btn_min=findViewById(R.id.btn_min);
        btn_payment=findViewById(R.id.btn_payment);
        btn_back_detail=findViewById(R.id.btn_back_detail);
        jmlpupuk=findViewById(R.id.jml_pupuk);
        txttotalharga=findViewById(R.id.totalharga);

        jmlpupuk.setText(valueJumlahpupuk.toString());
        txttotalharga.setText("Rp."+ valuetotalharga+"");
        btn_min.animate().alpha(1).setDuration(300).start();
        btn_min.setEnabled(false);

        btn_back_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent godetail = new Intent(PaymentAct.this, DetailAct.class);
                startActivity(godetail);
            }
        });

        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent godetail = new Intent(PaymentAct.this, SuccesBuy.class);
                startActivity(godetail);
            }
        });


        btn_pls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueJumlahpupuk-=1;
                jmlpupuk.setText(valueJumlahpupuk.toString());
                if (valueJumlahpupuk>1){
                    btn_min.animate().alpha(1).setDuration(300).start();
                    btn_min.setEnabled(true);
                }
                valuetotalharga = valueJumlahpupuk*hargapupuk;
                txttotalharga.setText("Rp."+valuetotalharga+"");
                if(valueJumlahpupuk>9){
                    btn_pls.animate().alpha(0).setDuration(300).start();
                    btn_pls.setEnabled(false);

                }
            }
        });


        btn_min.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueJumlahpupuk-=1;
                jmlpupuk.setText(valueJumlahpupuk.toString());
                if (valueJumlahpupuk<2){
                    btn_min.animate().alpha(1).setDuration(300).start();
                    btn_min.setEnabled(false);
                }
                valuetotalharga = valueJumlahpupuk*hargapupuk;
                txttotalharga.setText("Rp."+valuetotalharga+"");
                if(valueJumlahpupuk<10){
                    btn_pls.animate().alpha(0).setDuration(300).start();
                    btn_pls.setEnabled(true);

                }

            }

        });
    }
}
