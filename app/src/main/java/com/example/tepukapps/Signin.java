package com.example.tepukapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class Signin extends AppCompatActivity {

    TextView btn_new_account;
    Button btn_signin;
    TextInputEditText textUsername, textPassword;
    TextInputLayout layoutUsername,layoutPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        btn_new_account = findViewById(R.id.btn_new_account);
        textUsername = findViewById(R.id.xusername);
        layoutUsername = findViewById(R.id.layoutUsername);
        btn_signin = findViewById(R.id.btn_signin);
        textPassword = findViewById(R.id.xpassword);
        layoutPassword = findViewById(R.id.layoutPassword);

        btn_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotosignup = new Intent(Signin.this, Signup.class );
                startActivity(gotosignup);
            }
        });

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    login();

                }
            }
        });

        textUsername.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!textUsername.getText().toString().isEmpty()){
                    layoutUsername.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        textPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(textPassword.getText().toString().length() > 7){
                    layoutPassword.setErrorEnabled(false);
                }
                if(!textPassword.getText().toString().isEmpty()){
                    layoutPassword.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


    private boolean validate(){
        if(textUsername.getText().toString().isEmpty()){
            layoutUsername.setErrorEnabled(true);
            layoutUsername.setError("Username is Require");
            return false;
        }
        if(textPassword.getText().toString().length() < 8 ){
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Password minimum 8 characters");
            return false;
        }
        if(textPassword.getText().toString().isEmpty()){
            layoutPassword.setErrorEnabled(true);
            layoutPassword.setError("Password is Require");
            return false;
        }
        return true;
    }
    private void login(){
        StringRequest request = new StringRequest(Request.Method.POST,Constant.LOGIN, new Response.Listener<String>() {
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
                        editor.putBoolean("isLoggedIn",true);
                        editor.apply();
                        startActivity(new Intent(Signin.this,HomeActivity.class));
                        Toast.makeText(Signin.this, "Login Success", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        error();
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
                map.put("username",textUsername.getText().toString().trim());
                map.put("password",textPassword.getText().toString());
                return map;
            }
        };

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(request);
    }
    private void error(){
        Toast.makeText(Signin.this, "Login Failed", Toast.LENGTH_SHORT).show();
        layoutUsername.setErrorEnabled(true);
        layoutUsername.setError("Username invalid");
        layoutPassword.setErrorEnabled(true);
        layoutPassword.setError("Password invalid");
    }
}
