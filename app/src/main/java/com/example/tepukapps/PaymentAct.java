package com.example.tepukapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PaymentAct extends AppCompatActivity {
    LinearLayout btn_back_detail;
    Button btn_payment, btn_pls, btn_min;
    TextView jmlpupuk, totalPupuk,totalHarga;
    private SharedPreferences userPref,paymentPref;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        btn_pls = findViewById(R.id.btn_pls);
        btn_min = findViewById(R.id.btn_min);
        btn_payment = findViewById(R.id.btn_payment);
        btn_back_detail = findViewById(R.id.btn_back_detail);
        jmlpupuk = findViewById(R.id.jml_pupuk);
        totalPupuk = findViewById(R.id.totalPupuk);
        totalHarga = findViewById(R.id.totalHarga);

        SharedPreferences orderPred = getSharedPreferences("order", Context.MODE_PRIVATE);
        totalHarga.setText("RP."+ orderPred.getInt("totalPayment",0));
        totalPupuk.setText(String.valueOf(orderPred.getInt("totalPupuk",0)));



        btn_back_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_payment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payment();

            }
        });

    }

    private void payment() {
        userPref = getSharedPreferences("user", Context.MODE_PRIVATE);
        paymentPref = getSharedPreferences("payment",Context.MODE_PRIVATE);


        StringRequest request = new StringRequest(Request.Method.POST, Constant.CREATE_PAYMENT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")) {
                        SharedPreferences.Editor editor = paymentPref.edit();
                        JSONObject payment = new JSONObject(object.getString("payments"));
                        editor.putInt("id",payment.getInt("id"));
                        editor.apply();
                        Toast.makeText(PaymentAct.this, "Checkout Berhasil", Toast.LENGTH_SHORT).show();
                        Intent godetail = new Intent(PaymentAct.this, SuccesBuy.class);
                        startActivity(godetail);
                    } else {
                        Toast.makeText(PaymentAct.this, "Cart Anda Kosong", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = userPref.getString("token", "");
                HashMap<String, String> map = new HashMap<>();
                map.put("Authorization", "Bearer " + token);
                Log.d("ojan", String.valueOf(map));
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
