package com.example.tepukapps;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tepukapps.model.Order;
import com.example.tepukapps.model.Pupuk;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.Context.MODE_PRIVATE;


public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Order> orders;
    private int total ,pupuk;
    private SharedPreferences userPref;

    public OrderAdapter(Context context, ArrayList<Order> orders) {
        this.context = context;
        this.orders = orders;
        for (int i = 0;i<orders.size();i++){
            Order order = orders.get(i);
            int qty = order.getTotal();
            total += qty;
            Log.d("kahla", String.valueOf(total));
        }
        SharedPreferences orderPref = context.getSharedPreferences("order",MODE_PRIVATE);
        SharedPreferences.Editor editor = orderPref.edit();
        editor.putInt("totalPupuk",orders.size());
        editor.putInt("totalPayment",total);
        editor.apply();

    }

    @NonNull
    @Override
    public OrderAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item,parent,false);
        return new OrderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.ViewHolder holder, final int position) {
        final Order order = orders.get(position);
        Log.d("test2", order.getPupukOrder().getName());
        Picasso.get().load(Constant.URL+"storage/pupuk/"+order.getPupukOrder().getPhoto()).into(holder.imageView);
        holder.textJudul.setText("Pupuk "+order.getPupukOrder().getName());
        int qty = order.getQuantity();
        pupuk += qty;
        int harga = order.getPupukOrder().getPrice();
        total += harga;
        holder.textHarga.setText("Harga : " + harga);
        holder.textQty.setText("Jumlah Dibeli :" + (qty));
        holder.textTotal.setText("Rp. " + (order.getTotal()));
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPref = context.getSharedPreferences("user", MODE_PRIVATE);
                StringRequest request = new StringRequest(Request.Method.POST, Constant.DELETE_ORDER, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject object = new JSONObject(response);
                            if (object.getBoolean("success")){
                            }else {
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
                        String token = userPref.getString("token","");
                        HashMap<String,String> map = new HashMap<>();
                        map.put("Authorization","Bearer "+token);
                        Log.d("ojan", String.valueOf(map));
                        return map;
                    }

                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        HashMap<String,String> map = new HashMap<>();
                        map.put("id", String.valueOf(order.getId()));
                        return map;
                    }

                };
                RequestQueue queue = Volley.newRequestQueue(context);
                queue.add(request);
                removeAt(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,btnDelete;
        TextView textJudul,textHarga,textTotal,textQty;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            imageView = itemView.findViewById(R.id.imageCart);
            textJudul = itemView.findViewById(R.id.textCart);
            textHarga = itemView.findViewById(R.id.hargaCart);
            textTotal = itemView.findViewById(R.id.totalCart);
            textQty = itemView.findViewById(R.id.qtyCart);
        }
    }
    public void removeAt(int position) {
        orders.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, orders.size());
    }

}
