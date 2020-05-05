package com.example.tepukapps.fragment;


import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tepukapps.Constant;
import com.example.tepukapps.OrderAdapter;
import com.example.tepukapps.R;
import com.example.tepukapps.ShippingAdapter;
import com.example.tepukapps.model.Order;
import com.example.tepukapps.model.Payment;
import com.example.tepukapps.model.Pupuk;
import com.example.tepukapps.model.Shipping;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActivityFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    private ProgressBar progressBar;
    private ArrayList<Shipping> shippings;
    private ShippingAdapter adapter;
    private SharedPreferences preferences;



    public ActivityFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  = inflater.inflate(R.layout.fragment_activity, container, false);
        preferences = getActivity().getSharedPreferences("user",MODE_PRIVATE);
        init();
        return view;
    }
    private void init() {
        recyclerView = view.findViewById(R.id.rvCart);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getShipping();
    }

    private void getShipping() {
        shippings = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.GET, Constant.GET_SHIPPING, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")){
                        JSONArray array = new JSONArray(object.getString("shipping"));
                        for (int i = 0;i<array.length();i++){
                            JSONObject shipping = array.getJSONObject(i);
                            JSONObject payment = shipping.getJSONObject("payment");

                            Payment payment1 = new Payment();
                            payment1.setId(payment.getInt("id"));
                            payment1.setCodePayment(payment.getString("payment_code"));

                            Shipping shipping1 = new Shipping();
                            shipping1.setId(shipping.getInt("id"));
                            shipping1.setPayment(payment1);
                            shipping1.setStatus(shipping.getString("shipping_status"));
                            shipping1.setEstimate(shipping.getInt("shipping_time"));
                            shipping1.setDate(shipping.getString("created_at"));
                            shipping1.setKurir(shipping.getString("shipping_kurir"));
                            shippings.add(shipping1);
                        }
                        adapter = new ShippingAdapter(shippings, getContext());
                        recyclerView.setAdapter(adapter);
                    }else {
                        Log.d("kahla", "cantik");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
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
        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }

}
