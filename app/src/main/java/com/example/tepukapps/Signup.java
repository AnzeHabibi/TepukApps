package com.example.tepukapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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
                    Intent go = new Intent(Signup.this, SignUp2.class);
                    startActivity(go);
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
}
