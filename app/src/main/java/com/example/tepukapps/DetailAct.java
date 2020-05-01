package com.example.tepukapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.tepukapps.model.Pupuk;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DetailAct extends AppCompatActivity {
    public static final String DATA ="data";

    Button btn_continue;
    LinearLayout btn_back;
    ImageView img_pupuk;
    TextView txt_harga, txt_stok, txt_jenis, txt_komposisi, txt_desc, title_pupuk;
    private SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Pupuk pupuk = getIntent().getParcelableExtra(DATA);
        init();


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

    private void init() {
        preferences = getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE);
        btn_continue = findViewById(R.id.btn_buy);
        btn_back = findViewById(R.id.btn_back_home);
        txt_harga=findViewById(R.id.txt_harga);
        txt_desc=findViewById(R.id.txt_desc);
        txt_jenis=findViewById(R.id.txt_jenis);
        txt_komposisi=findViewById(R.id.txt_komposisi);
        txt_stok=findViewById(R.id.txt_stok);
        title_pupuk=findViewById(R.id.title_pupuk);
        img_pupuk=findViewById(R.id.img_pupuk);
    }

    private void create(){
        StringRequest request = new StringRequest(Request.Method.POST,Constant.CREATE_ORDER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = preferences.getString("token","");
                HashMap<String,String> map = new HashMap<>();
                map.put("Authorization","Bearer "+token);
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return super.getParams();
            }
        };
    }
}
