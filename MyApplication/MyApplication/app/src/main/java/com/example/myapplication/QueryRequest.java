package com.example.myapplication;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class QueryRequest extends StringRequest {
    private Map<String, String> args;
    private static Response.ErrorListener err = error -> Log.d("please","Error listener response: " + error.getMessage());

    public QueryRequest(String StudentID, String url, Response.Listener<String> listener){
        super(Method.POST, url, listener, err);
        args = new HashMap<String, String>();
        args.put("user_id", StudentID);
    }

    @Override
    protected Map<String, String> getParams() {
        return args;
    }

}