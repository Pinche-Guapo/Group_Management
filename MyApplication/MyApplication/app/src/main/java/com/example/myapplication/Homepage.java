package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.myapplication.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Homepage extends AppCompatActivity {
    private TextView tvId;
    Button group_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // Find the TextView in the layout by its ID
        tvId = findViewById(R.id.tv_id);

        // Get the ID value from the intent extras
        String id = getIntent().getStringExtra("id");

        // Display the ID value in the TextView
        tvId.setText("Welcome, user with ID " + id);

        group_btn = findViewById(R.id.group_btn);
        group_btn.setOnClickListener(v -> {
                String StudentID = id;

            Response.Listener<String> responseListener = response -> {
                try {
                    Log.d("SubmitQueryHelp", response);
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");

                    if (success) {
                        String meeting_id = jsonResponse.getString("meeting_id");
                        String meeting_name = jsonResponse.getString("meeting_name");
                        String date = jsonResponse.getString("date");
                        String time_slot_id = jsonResponse.getString("time_slot_id");

                        Intent intent = new Intent(Homepage.this, GroupPage.class);

                        intent.putExtra("meeting_id", meeting_id);
                        intent.putExtra("meeting_name", meeting_name);
                        intent.putExtra("date", date);
                        intent.putExtra("time_slot_id", time_slot_id);
                        intent.putExtra("StudentID", StudentID);

                        Homepage.this.startActivity(intent);
                    } else {
                        String meeting_id = "";
                        String meeting_name = "";
                        String date = "";
                        String time_slot_id = "";

                        Intent intent = new Intent(Homepage.this, GroupPage.class);

                        intent.putExtra("meeting_id", meeting_id);
                        intent.putExtra("meeting_name", meeting_name);
                        intent.putExtra("date", date);
                        intent.putExtra("time_slot_id", time_slot_id);
                        intent.putExtra("StudentID", StudentID);

                        Homepage.this.startActivity(intent);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            };

            QueryRequest queryRequest = new QueryRequest(StudentID, getString(R.string.url) + "group_page.php", responseListener);
            RequestQueue queue = Volley.newRequestQueue(Homepage.this);
            queue.add(queryRequest);
        });
    }
}