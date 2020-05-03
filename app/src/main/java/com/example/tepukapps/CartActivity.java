package com.example.tepukapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.tepukapps.dialog.CartDialog;
import com.example.tepukapps.fragment.CartFragment;
import com.example.tepukapps.model.Order;

import java.util.ArrayList;
import java.util.Objects;

public class CartActivity extends AppCompatActivity {
    private Button btnCheckout;
    private TextView tvTotal;
    private int total ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        getSupportFragmentManager().beginTransaction().add(R.id.frameCart,new CartFragment()).commit();
        SharedPreferences orderPref = Objects.requireNonNull(this.getSharedPreferences("order",MODE_PRIVATE));
        total = orderPref.getInt("totalPayment",0);

        btnCheckout = findViewById(R.id.btnCheckout);
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });

    }


    private void openDialog() {
        CartDialog cartDialog = new CartDialog(this);
        cartDialog.show(getSupportFragmentManager(),"cartDialog");
    }
}
