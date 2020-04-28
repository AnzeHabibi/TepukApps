package com.example.tepukapps.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tepukapps.Constant;
import com.example.tepukapps.PupukAdapter;
import com.example.tepukapps.R;
import com.example.tepukapps.model.Pupuk;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class OrganikFragment extends Fragment {
    private RecyclerView recyclerView;
    private View view;
    private ArrayList<Pupuk> arrayList;
    private PupukAdapter adapter;


    public OrganikFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view  = inflater.inflate(R.layout.fragment_organik, container, false);
        init();
        return view;
    }

    private void init() {
        recyclerView = view.findViewById(R.id.rvOrganik);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        getPupuk();
    }

    private void getPupuk() {
        arrayList = new ArrayList<>();

        StringRequest request = new StringRequest(Request.Method.GET, Constant.ORGANIK, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")){
                        JSONArray array = new JSONArray(object.getString("pupuk"));
                        for(int i = 0 ;i<array.length();i++){
                            JSONObject pupukObject = array.getJSONObject(i);

                            Pupuk pupuk = new Pupuk();
                            pupuk.setId(pupukObject.getInt("id"));
                            pupuk.setName(pupukObject.getString("nama_pupuk"));
                            pupuk.setCategory(pupukObject.getString("jenis_pupuk"));
                            pupuk.setDescription(pupukObject.getString("deskripsi_pupuk"));
                            pupuk.setComposition(pupukObject.getString("komposisi_pupuk"));
                            pupuk.setPrice(pupukObject.getInt("harga_pupuk"));
                            pupuk.setPhoto(pupukObject.getString("foto_pupuk"));
                            arrayList.add(pupuk);
                        }
                        adapter = new PupukAdapter(getContext(),arrayList);
                        recyclerView.setAdapter(adapter);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue queue = Volley.newRequestQueue(getContext());
        queue.add(request);
    }
}
