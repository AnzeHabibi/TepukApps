package com.example.tepukapps.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatDialogFragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.tepukapps.Constant;
import com.example.tepukapps.GetStarted;
import com.example.tepukapps.PaymentAct;
import com.example.tepukapps.ProfileAct;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class SessionDialog extends AppCompatDialogFragment {
    private Context context;
    private SharedPreferences orderPref;
    private SharedPreferences paymentPref;
    private SharedPreferences userPref;

    public SessionDialog( Context context) {
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        userPref = getActivity().getSharedPreferences("user",Context.MODE_PRIVATE);

        builder.setMessage("Session is running out time , Please Log in again!");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               logout();
            }
        });


        return builder.create();
    }
    private void logout() {
        Log.d("kahla", "cantik");
        StringRequest request = new StringRequest(Request.Method.GET, Constant.LOGOUT, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    if (object.getBoolean("success")){
                        userPref.edit().remove("id").apply();
                        userPref.edit().remove("name").apply();
                        userPref.edit().remove("email").apply();
                        userPref.edit().remove("address").apply();
                        userPref.edit().remove("phonenumber").apply();
                        userPref.edit().remove("username").apply();
                        userPref.edit().remove("password").apply();
                        userPref.edit().remove("isLoggedIn").apply();
                        Intent intentLogout = new Intent(context, GetStarted.class);
                        intentLogout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intentLogout);
                    }else {
                        userPref.edit().remove("id").apply();
                        userPref.edit().remove("name").apply();
                        userPref.edit().remove("email").apply();
                        userPref.edit().remove("address").apply();
                        userPref.edit().remove("phonenumber").apply();
                        userPref.edit().remove("username").apply();
                        userPref.edit().remove("password").apply();
                        userPref.edit().remove("isLoggedIn").apply();
                        Intent intentLogout = new Intent(context, GetStarted.class);
                        intentLogout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intentLogout);
                        Log.d("kahla", "cantik");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    userPref.edit().remove("id").apply();
                    userPref.edit().remove("name").apply();
                    userPref.edit().remove("email").apply();
                    userPref.edit().remove("address").apply();
                    userPref.edit().remove("phonenumber").apply();
                    userPref.edit().remove("username").apply();
                    userPref.edit().remove("password").apply();
                    userPref.edit().remove("isLoggedIn").apply();
                    Intent intentLogout = new Intent(context, GetStarted.class);
                    intentLogout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    context.startActivity(intentLogout);
                    Log.d("kahla", "cantik");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                userPref.edit().remove("id").apply();
                userPref.edit().remove("name").apply();
                userPref.edit().remove("email").apply();
                userPref.edit().remove("address").apply();
                userPref.edit().remove("phonenumber").apply();
                userPref.edit().remove("username").apply();
                userPref.edit().remove("password").apply();
                userPref.edit().remove("isLoggedIn").apply();
                Intent intentLogout = new Intent(context, GetStarted.class);
                intentLogout.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                context.startActivity(intentLogout);
                Log.d("kahla", "cantik");
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
        };

        RequestQueue queue = Volley.newRequestQueue(Objects.requireNonNull(context));
        queue.add(request);

    }
}
