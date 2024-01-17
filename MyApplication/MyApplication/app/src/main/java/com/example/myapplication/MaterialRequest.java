package com.example.myapplication;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MaterialRequest extends StringRequest {

    private Map<String, String> params;

    private static Response.ErrorListener err = error -> Log.d("please","Error listener response: " + error.getMessage());

    public MaterialRequest(String meetingID, String url, Response.Listener<String> listener) {
        super(Method.POST, url, listener, err);
        params = new HashMap<>();
        params.put("meeting_id", meetingID);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
