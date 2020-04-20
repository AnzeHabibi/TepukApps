package com.example.tepukapps;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class GetStarted extends AppCompatActivity {

    Button btn_signin;
    Button btn_signup;
    Animation ttb, btt;
    ImageView icon;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);




        btn_signin= findViewById(R.id.btn_signin);
        icon= findViewById(R.id.icon);
        textView=findViewById(R.id.textView);
        btn_signup= findViewById(R.id.btn_signup);

        //run anim
        btn_signin.startAnimation(btt);
        btn_signup.startAnimation(btt);
        icon.startAnimation(ttb);
        textView.startAnimation(ttb);

        btn_signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotosign = new Intent(GetStarted.this, Signin.class);
                startActivity(gotosign);
            }
        });

        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotosignup = new Intent(GetStarted.this, Signup.class);
                startActivity(gotosignup);
            }
        });
    }
}
