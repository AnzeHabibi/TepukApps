package com.example.tepukapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
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
import com.example.tepukapps.model.Pupuk;
import com.squareup.picasso.Picasso;

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
    int id,qty,harga;
    private SharedPreferences preferences;
    String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        init();
        getData();



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
               create();
            }
        });
    }

    private void getData() {
        Pupuk pupuk = getIntent().getParcelableExtra(DATA);
        id = pupuk.getId();
        Picasso.get().load(Constant.URL+"storage/pupuk/"+pupuk.getPhoto()).into(img_pupuk);
        title_pupuk.setText(pupuk.getName());
        txt_jenis.setText(pupuk.getCategory());
        txt_desc.setText(pupuk.getDescription());
        txt_komposisi.setText(pupuk.getComposition());
        harga = pupuk.getPrice();
        txt_harga.setText("Rp."+harga+"/kg");
        qty = 2;
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
                    Log.d("ojan", "IM HERE");
                    if (object.getBoolean("success")){
                        Log.d("ojan", "IM HERE");
                        Toast.makeText(DetailAct.this, "Item ditambahkan ke Cart", Toast.LENGTH_SHORT).show();
                        finish();
                    }else {
                        message =  object.getString("message");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(DetailAct.this,"error : " + message, Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(DetailAct.this, "Error", Toast.LENGTH_SHORT).show();

            }
        }){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = preferences.getString("token","");
                HashMap<String,String> map = new HashMap<>();
                map.put("Authorization","Bearer "+token);
                Log.d("ojan", String.valueOf(map));
                return map;
            }

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String, String> map = new HashMap<>();
                map.put("pupuk_id", String.valueOf(id));
                map.put("order_qty", String.valueOf(qty));
                map.put("harga_pupuk", String.valueOf(harga));
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
}
