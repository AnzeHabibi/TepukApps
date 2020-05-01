package com.example.tepukapps.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tepukapps.CartActivity;
import com.example.tepukapps.Constant;
import com.example.tepukapps.GetStarted;
import com.example.tepukapps.R;
import com.example.tepukapps.Signin;
import com.example.tepukapps.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageClickListener;
import com.synnapps.carouselview.ImageListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;


public class HomeFragment extends Fragment {
    private View view;
    private int[] mImages = new int[]{
            R.drawable.test1 ,R.drawable.test2,R.drawable.test3
    };
    private String[] mImagesTitle = new String[]{
            "test 1","test 2", "test 3"
    };
    private CarouselView carouselView;

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    private ImageView logout,cartButton;
    private SharedPreferences preferences,order;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        carouselView = view.findViewById(R.id.carouselView);
        logout = view.findViewById(R.id.logout);
        cartButton = view.findViewById(R.id.cartButton);
        carouselView.setPageCount(mImages.length);
        preferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        order = getActivity().getSharedPreferences("order",Context.MODE_PRIVATE);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(mImages[position]);
            }
        });
        carouselView.setImageClickListener(new ImageClickListener() {
            @Override
            public void onClick(int position) {
                Toast.makeText(getContext(), mImagesTitle[position], Toast.LENGTH_SHORT).show();
            }
        });
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CartActivity.class);
                startActivity(intent);
            }
        });
        return  view;
    }

    private void logout() {
        Log.d("kahla", "cantik");
        StringRequest request = new StringRequest(Request.Method.GET, Constant.LOGOUT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.d("kahla", "cantik");
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
                        order.edit().remove("totalPaymeny").apply();
                        order.edit().remove("repeat").apply();
                        Intent intentLogout = new Intent(getActivity(), GetStarted.class);
                        intentLogout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intentLogout);
                        Toast.makeText(getActivity(), object.getString("message"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "LUL", Toast.LENGTH_SHORT).show();
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

        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(getActivity()));
        queue.add(request);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        tabLayout = getActivity().findViewById(R.id.tabLayout);
        viewPager = getActivity().findViewById(R.id.viewPager);
        adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.AddFragment(new OrganikFragment(),"Organik");
        adapter.AddFragment(new AnorganikFragment(),"Anorganik");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


    }
}
