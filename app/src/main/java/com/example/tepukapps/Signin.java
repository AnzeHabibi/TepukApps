package com.example.tepukapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

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
                    Intent gotodetail = new Intent(Signin.this, DetailAct.class);
                    startActivity(gotodetail);
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
}
