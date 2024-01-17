package com.example.myapplication;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
public class MaterialPage extends AppCompatActivity {

    private TextView tvMaterialPage, tvMeetingName, tvTitle, tvAuthor, tvType, tvUrl, tvNotes, tvAssigned;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_page);

        // Find the TextViews in the layout by their IDs
        tvMaterialPage = findViewById(R.id.tv_material_page);
        tvMeetingName = findViewById(R.id.tv_meeting_name);
        tvTitle = findViewById(R.id.tv_title);
        tvAuthor = findViewById(R.id.tv_author);
        tvType = findViewById(R.id.tv_type);
        tvUrl = findViewById(R.id.tv_url);
        tvNotes = findViewById(R.id.tv_notes);
        tvAssigned = findViewById(R.id.tv_assigned_date);

        // Get the meeting details values from the intent extras
        Intent intent0 = getIntent();
        String meetingName = intent0.getStringExtra("meeting_name");
        String title = intent0.getStringExtra("title");
        String author = intent0.getStringExtra("author");
        String type = intent0.getStringExtra("type");
        String url = intent0.getStringExtra("url");
        String notes = intent0.getStringExtra("notes");
        String assigned = intent0.getStringExtra("assigned");


        // Display the meeting details in the TextViews
        tvMaterialPage.setText("Material Page");
        tvMeetingName.setText("Meeting Name: " + meetingName);
        tvTitle.setText("Title: " + title);
        tvAuthor.setText("Author: " + author);
        tvType.setText("Type: " + type);
        tvUrl.setText("Url: " + url);
        tvNotes.setText("Notes: " + notes);
        tvAssigned.setText("Assigned Dates: " + assigned);


    }
}
