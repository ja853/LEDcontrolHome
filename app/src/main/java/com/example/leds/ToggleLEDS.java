package com.example.leds;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ToggleLEDS {

    public void toggle(Context fromActivity){
        CharSequence text = "Button Pressed";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(fromActivity, text, duration);
        toast.show();

        Log.d("TAG", "toggle: HERE ");

        String baseURL = "http://192.168.0.80:5000/";
        String request = "toggle";
        String JsonUrl = baseURL + request;
        JsonObjectRequest jsObjRequest = new JsonObjectRequest(
                Request.Method.GET, JsonUrl, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = new JSONObject(response.toString());
                            ArrayList<String> results = new ArrayList<>();


                        } catch (JSONException e) {
                            Log.d("ERROR", "onResponse: "+e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("ERROR", "Error in receiving JSON Response:\n" + error.toString());
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Content-Type", "application/x-www-form-urlencoded");
                return params;
            }
        };
        MySingleton.getInstance(fromActivity).addToRequestQueue(jsObjRequest);
    }


}

