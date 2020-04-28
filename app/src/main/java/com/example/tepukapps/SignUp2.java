package com.example.tepukapps;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SignUp2 extends AppCompatActivity {

    Button btn_continue;
    LinearLayout btn_back;
    TextInputEditText name, address, phone;
    TextInputLayout layoutName,layoutAddress,layoutPhone;
    SharedPreferences userPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);

        btn_continue = findViewById(R.id.btn_continue);
        btn_back = findViewById(R.id.btn_back);

        name=findViewById(R.id.name);
        address=findViewById(R.id.address);
        phone=findViewById(R.id.phone);
        layoutName = findViewById(R.id.layoutName);
        layoutAddress = findViewById(R.id.layoutAddress);
        layoutPhone = findViewById(R.id.layoutPhone);
        userPref = getSharedPreferences("user",MODE_PRIVATE);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(SignUp2.this, Signup.class);
                startActivity(back);
            }
        });

        btn_continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    register();
                }
            }
        });

        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!name.getText().toString().isEmpty()){
                    layoutName.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        address.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!address.getText().toString().isEmpty()){
                    layoutAddress.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        phone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(phone.getText().toString().length() != 7){
                    layoutPhone.setErrorEnabled(false);
                }
                if(!phone.getText().toString().isEmpty()){
                    layoutPhone.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private boolean validate(){
        if(name.getText().toString().isEmpty()){
            layoutName.setErrorEnabled(true);
            layoutPhone.setError("Name is Require");
            return false;
        }
        if(address.getText().toString().isEmpty()){
            layoutAddress.setErrorEnabled(true);
            layoutAddress.setError("Address is Require");
            return false;
        }
        if(phone.getText().toString().length() != 12 ){
            layoutPhone.setErrorEnabled(true);
            layoutPhone.setError("Phone number must be 12 digits");
            return false;
        }
        if(phone.getText().toString().isEmpty()){
            layoutPhone.setErrorEnabled(true);
            layoutPhone.setError("Password is Require");
            return false;
        }
        return true;
    }

    private void register(){
        StringRequest request = new StringRequest(Request.Method.POST,Constant.REGISTER2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//success
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")){

                        //if success
                        Toast.makeText(SignUp2.this, "Register Success", Toast.LENGTH_SHORT).show();
                        Intent go = new Intent(SignUp2.this, Signin.class);
                        startActivity(go);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {//error
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
            //add token


            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                String token = userPref.getString("token","");
                HashMap<String,String> map = new HashMap<>();
                map.put("Authorization","Bearer"+token);
                return map;
            }

            //add parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("name",name.getText().toString().trim());
                map.put("address",address.getText().toString());
                map.put("phonenumber",phone.getText().toString().trim());
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

    }
}
