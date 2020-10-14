package com.pratlabs.androidwificar;
import android.content.Context;
import android.util.Log;
import android.view.View;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;


public class sendreq {


    public void posst(final String url, final String par, final View v,final Context ctx) {
        RequestQueue queue = Volley.newRequestQueue(ctx);

        StringRequest sr = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("HttpClient", "success! response: " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Snackbar.make(v, "error: Connect to car's wifi first", Snackbar.LENGTH_LONG).show();
                    }
                })
        {
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("State",par);
                return params;
            }

        };
        queue.add(sr);
    }

}
