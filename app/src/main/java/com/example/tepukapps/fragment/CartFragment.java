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
import com.example.tepukapps.model.Order;
import com.example.tepukapps.model.Pupuk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class CartFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    private ProgressBar progressBar;
    private ArrayList<Order> orders;
    private OrderAdapter adapter;
    private SharedPreferences preferences;

    public CartFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        preferences = getActivity().getSharedPreferences("user",MODE_PRIVATE);
        init();
        return view;
    }

    private void init() {
        recyclerView = view.findViewById(R.id.rvCart);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        getOrder();
    }

    private void getOrder() {
        orders = new ArrayList<>();
        StringRequest request = new StringRequest(Request.Method.GET, Constant.GET_ORDER, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")){
                        Log.d("test", "IM HERE 1");
                        JSONArray array = new JSONArray(object.getString("orders"));

                        for (int i = 0 ; i<array.length();i++){
                            JSONObject orderO  = array.getJSONObject(i);
                            JSONObject pupukO = orderO.getJSONObject("pupuk");

                            Pupuk pupukOrder = new Pupuk();
                            pupukOrder.setId(pupukO.getInt("id"));
                            pupukOrder.setName(pupukO.getString("nama_pupuk"));
                            pupukOrder.setPrice(pupukO.getInt("harga_pupuk"));
                            pupukOrder.setPhoto(pupukO.getString("foto_pupuk"));

                            Order order = new Order();
                            order.setId(orderO.getInt("id"));
                            order.setPupukOrder(pupukOrder);
                            order.setDate(orderO.getString("created_at"));
                            order.setQuantity(orderO.getInt("order_qty"));
                            order.setTotal(orderO.getInt("total"));
                            order.setStatus(orderO.getString("status"));

                            orders.add(order);
                            Log.d("test", (order.getTotal())+"data "  + i );
                        }
                    }else {
                    }
                    adapter = new OrderAdapter(getContext(),orders);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("isLoggedIn",false);
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
