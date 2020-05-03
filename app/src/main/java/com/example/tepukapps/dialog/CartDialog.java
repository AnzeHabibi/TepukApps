package com.example.tepukapps.dialog;

import android.app.Activity;
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
import com.example.tepukapps.CartActivity;
import com.example.tepukapps.Constant;
import com.example.tepukapps.PaymentAct;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class CartDialog extends AppCompatDialogFragment {
    private Context context;
    private SharedPreferences orderPref;
    private SharedPreferences userPref;

    public CartDialog( Context context) {
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        orderPref = getActivity().getSharedPreferences("order", Context.MODE_PRIVATE);
        String harga = String.valueOf(orderPref.getInt("totalPayment",0));
        builder.setTitle("Payment Total");
        builder.setMessage("Total Belanja anda sebesar Rp." + harga + "\n Apakah anda ingin melanjutkan?");
        builder.setNegativeButton("tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        builder.setPositiveButton("ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              context.startActivity(new Intent(context,PaymentAct.class));
            }
        });


        return builder.create();
    }
}
