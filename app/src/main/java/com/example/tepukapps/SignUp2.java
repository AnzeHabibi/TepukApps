package com.example.tepukapps;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import androidx.appcompat.app.AppCompatActivity;

public class SignUp2 extends AppCompatActivity {

    Button btn_continue;
    LinearLayout btn_back;
    TextInputEditText name, address, phone;
    TextInputLayout layoutName,layoutAddress,layoutPhone;

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
                    Intent go = new Intent(SignUp2.this, DetailAct.class);
                    startActivity(go);
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
                if (!phone.getText().toString().isEmpty()){
                    layoutPhone.setErrorEnabled(false);
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
}
