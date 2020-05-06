package com.example.tepukapps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProfileAct extends AppCompatActivity {
    Button btnOut;
    private SharedPreferences preferences;
    private TextView name,userName,address,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        btnOut = findViewById(R.id.btn_out);
        name = findViewById(R.id.nama_lengkap);
        userName = findViewById(R.id.txt_username);
        address = findViewById(R.id.txt_address);
        phone = findViewById(R.id.txt_phone);
        preferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        name.setText(preferences.getString("name",""));
        userName.setText(preferences.getString("username",""));
        address.setText(preferences.getString("address",""));
        phone.setText(preferences.getString("phonenumber",""));
        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
    }
    private void logout() {
        Log.d("kahla", "cantik");
        StringRequest request = new StringRequest(Request.Method.GET, Constant.LOGOUT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")){
                        preferences.edit().remove("id").apply();
                        preferences.edit().remove("name").apply();
                        preferences.edit().remove("email").apply();
                        preferences.edit().remove("address").apply();
                        preferences.edit().remove("phonenumber").apply();
                        preferences.edit().remove("username").apply();
                        preferences.edit().remove("password").apply();
                        preferences.edit().remove("isLoggedIn").apply();
                        Intent intentLogout = new Intent(ProfileAct.this, GetStarted.class);
                        intentLogout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intentLogout);
                        Toast.makeText(ProfileAct.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                    }else {
                        preferences.edit().remove("id").apply();
                        preferences.edit().remove("name").apply();
                        preferences.edit().remove("email").apply();
                        preferences.edit().remove("address").apply();
                        preferences.edit().remove("phonenumber").apply();
                        preferences.edit().remove("username").apply();
                        preferences.edit().remove("password").apply();
                        preferences.edit().remove("isLoggedIn").apply();
                        Intent intentLogout = new Intent(ProfileAct.this, GetStarted.class);
                        intentLogout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intentLogout);
                        Toast.makeText(ProfileAct.this, object.getString("message"), Toast.LENGTH_SHORT).show();
                        Log.d("kahla", "gagal");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(ProfileAct.this, "LUL", Toast.LENGTH_SHORT).show();
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
                Log.d("ojan", String.valueOf(map));
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(ProfileAct.this));
        queue.add(request);

    }
}
