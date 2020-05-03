package com.example.tepukapps;

import androidx.appcompat.app.AppCompatActivity;

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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {

    Button btn_continue;
    LinearLayout btn_back;
    TextInputEditText username, password, email;
    TextInputLayout layoutUsername,layoutPassword,layoutEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        btn_continue = findViewById(R.id.btn_continue);
        btn_back = findViewById(R.id.btn_back);

        username=findViewById(R.id.xusername);
        password=findViewById(R.id.xpassword);
        email=findViewById(R.id.email);
        layoutEmail = findViewById(R.id.layoutEmail);
        layoutPassword = findViewById(R.id.layoutPassword);
        layoutUsername = findViewById(R.id.layoutUsername);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent back = new Intent(Signup.this, GetStarted.class);
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

        username.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!username.getText().toString().isEmpty()){
                    layoutUsername.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(password.getText().toString().length() > 7){
                    layoutPassword.setErrorEnabled(false);
                }
                if(!password.getText().toString().isEmpty()){
                    layoutPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!email.getText().toString().isEmpty()){
                    layoutEmail.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
    private boolean validate(){
        if(username.getText().toString().isEmpty()){
            layoutUsername.setErrorEnabled(true);
            layoutUsername.setError("Username is Require");
            return false;
        }
        if(email.getText().toString().isEmpty()){
            layoutEmail.setErrorEnabled(true);
            layoutEmail.setError("Email is Require");
            return false;
        }
        if(password.getText().toString().length() < 8 ){
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Password minimum 8 characters");
            return false;
        }
        if(password.getText().toString().isEmpty()){
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Password is Require");
            return false;
        }
        return true;
    }
    private void register(){
        StringRequest request = new StringRequest(Request.Method.POST,Constant.REGISTER1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {//success
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")){
                        JSONObject user = object.getJSONObject("user");
                        //make shared preferences
                        SharedPreferences userPref = getSharedPreferences("user",MODE_PRIVATE);
                        SharedPreferences.Editor editor = userPref.edit();
                        editor.putString("token",object.getString("token"));
                        editor.putString("name",user.getString("name"));
                        editor.putString("email",user.getString("email"));
                        editor.putString("address",user.getString("address"));
                        editor.putString("phonenumber",user.getString("phonenumber"));
                        editor.apply();
                        //if success
                        Intent go = new Intent(Signup.this, Signup2.class);
                        startActivity(go);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    error();
                }
            }
        }, new Response.ErrorListener() {//error
            @Override
            public void onErrorResponse(VolleyError error) {
                error();
                error.printStackTrace();
            }
        }){
            //add parameters
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> map = new HashMap<>();
                map.put("username",username.getText().toString().trim());
                map.put("password",password.getText().toString());
                map.put("email",email.getText().toString().trim());
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);

    }
    private void error(){
        Toast.makeText(Signup.this, "Login Failed", Toast.LENGTH_SHORT).show();
        layoutUsername.setErrorEnabled(true);
        layoutUsername.setError("Username already taken");

    }
}
