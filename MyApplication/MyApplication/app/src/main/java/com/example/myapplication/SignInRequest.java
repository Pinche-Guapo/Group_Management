package com.example.myapplication;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SignInRequest extends StringRequest {

    private Map<String, String> args;

    private static Response.ErrorListener err = error -> Log.d("please","Error listener response: " + error.getMessage());

    public SignInRequest(String email, String password, String url, Response.Listener<String> listener) {
        super(Method.POST, url, listener, err);
        args = new HashMap<>();
        args.put("email", email);
        args.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return args;
    }

}
