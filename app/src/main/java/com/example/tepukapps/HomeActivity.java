package com.example.tepukapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tepukapps.fragment.ActivityFragment;
import com.example.tepukapps.fragment.HistoryFragment;
import com.example.tepukapps.fragment.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class HomeActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private long backPressedTime;
    private Toast backToast;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        Loading Loading = new Loading(HomeActivity.this);
        getSupportFragmentManager().beginTransaction().add(R.id.frameLayout,new HomeFragment()).commit();
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFrag = null;
            switch (item.getItemId()){
                case R.id.home:
                    selectedFrag = new HomeFragment();
                    break;
                case R.id.activity:
                    selectedFrag = new ActivityFragment();
                    break;
                case R.id.history:
                    selectedFrag = new HistoryFragment();
                    break;
            }
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, selectedFrag).commit();
            return true;
        }
    };
    @Override
    public void onBackPressed() {
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel();
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
            super.onBackPressed();
            return;
        } else {
            {
                backToast = Toast.makeText(getBaseContext(), "Tekan 2x untuk keluar", Toast.LENGTH_SHORT);
                backToast.show();
            }
        }

        backPressedTime = System.currentTimeMillis();
    }


}
