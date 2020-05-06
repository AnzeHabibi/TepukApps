package com.example.tepukapps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tepukapps.fragment.CartFragment;
import com.example.tepukapps.fragment.DetailFragment;
import com.example.tepukapps.model.Order;
import com.example.tepukapps.model.Payment;
import com.example.tepukapps.model.Pupuk;
import com.example.tepukapps.model.Shipping;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DetailsHistory extends AppCompatActivity {
    public static final String DATA = "data";
    private SharedPreferences userPref,historyPref;
    ImageView mainImageView;
    TextView title, harga, kode,status,address,kurir;
    private RecyclerView recyclerView ;
    private DetailAdapter adapter;
    private ArrayList<Order> arrayList;
    private int id,harga1;
    private String address1,kode1;
    private Shipping shipping;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_history);
        getSupportFragmentManager().beginTransaction().add(R.id.frameHistory,new DetailFragment()).commit();
        userPref = getSharedPreferences("user", Context.MODE_PRIVATE);
        historyPref = getSharedPreferences("history",Context.MODE_PRIVATE);
        shipping = getIntent().getParcelableExtra(DATA);
        address1 = userPref.getString("address","");
        kode1 = historyPref.getString("kode","");
        harga1 = historyPref.getInt("harga",0);
        id = historyPref.getInt("id",0);
        Log.d("kahla", String.valueOf(id));
        init();
        getData();
    }

    private void getData() {
        status.setText(shipping.getStatus());
        kode.setText(kode1);
        harga.setText("Rp."+ harga1);
        address.setText(address1);
    }

    private void init() {
        status = findViewById(R.id.status);
        address = findViewById(R.id.address);
        kurir = findViewById(R.id.kurir);
        harga = findViewById(R.id.harga);
        kode = findViewById(R.id.kode);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        SharedPreferences historyPref = getSharedPreferences("history", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = historyPref.edit();
        editor.remove("id").apply();
        editor.apply();
    }
}