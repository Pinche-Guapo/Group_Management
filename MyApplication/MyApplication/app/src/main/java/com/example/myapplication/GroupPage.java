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

public class GroupPage extends AppCompatActivity {

    Button group_btn, deleteButton, material_l;
    private TextView tvEnrolledGroup, tvMeetingName, tvMeetingID, tvDate, tvTimeSlot, tvStudentID, tvMeetingDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_page);

        // Find the TextViews in the layout by their IDs
        tvEnrolledGroup = findViewById(R.id.tv_Enrolled_group);
        tvMeetingName = findViewById(R.id.tv_meeting_name);
        tvMeetingID = findViewById(R.id.tv_meeting_id);
        tvDate = findViewById(R.id.tv_date);
        tvTimeSlot = findViewById(R.id.tv_time_slot);
        tvStudentID = findViewById(R.id.tv_student_id);
        tvMeetingDetails = findViewById(R.id.tv_meeting_details);

        // Get the meeting details values from the intent extras
        Intent intent = getIntent();
        String meetingName = intent.getStringExtra("meeting_name");
        String meetingID = intent.getStringExtra("meeting_id");
        String date = intent.getStringExtra("date");
        String timeSlotID = intent.getStringExtra("time_slot_id");
        String StudentID = intent.getStringExtra("StudentID");
        String meet_id = meetingID;

        // Display the meeting details in the TextViews
        tvEnrolledGroup.setText("Enrolled Group");
        tvMeetingName.setText("Meeting Name: " + meetingName);
        tvMeetingID.setText("Meeting ID: " + meetingID);
        tvDate.setText("Date: " + date);
        tvTimeSlot.setText("Time Slot ID: " + timeSlotID);
        tvStudentID.setText("Student ID: " + StudentID);
        tvMeetingDetails.setText("Meeting Details:");

        deleteButton = findViewById(R.id.delete_group);

//        material_l = findViewById(R.id.material_btn);
//        material_l.setOnClickListener(v -> {
//
//            Response.Listener<JSONObject> responseListener = response -> {
//                try {
//                    Log.d("SubmitQueryHelp", response.toString());
//
//                    boolean success = response.getBoolean("success");
//
//                    if (success) {
//                        JSONObject material = response.getJSONObject("material");
//                        String meeting_name = material.getString("meeting_name");
//                        String title = material.getString("title");
//                        String author = material.getString("author");
//                        String type = material.getString("type");
//                        String url = material.getString("url");
//                        String notes = material.getString("notes");
//                        String assigned_date = material.getString("assigned_date");
//
//                        Intent intent0 = new Intent(GroupPage.this, MaterialPage.class);
//
//                        intent0.putExtra("meeting_name", meeting_name);
//                        intent0.putExtra("title", title);
//                        intent0.putExtra("author", author);
//                        intent0.putExtra("type", type);
//                        intent0.putExtra("url", url);
//                        intent0.putExtra("notes", notes);
//                        intent0.putExtra("assigned_date", assigned_date);
//
//                        GroupPage.this.startActivity(intent0);
//                    } else {
//
//                        String meeting_name = "";
//                        String title = "";
//                        String author = "";
//                        String type = "";
//                        String url = "";
//                        String notes = "";
//                        String assigned_date = "";
//
//                        Intent intent0 = new Intent(GroupPage.this, MaterialPage.class);
//
//                        intent0.putExtra("meeting_name", meeting_name);
//                        intent0.putExtra("title", title);
//                        intent0.putExtra("author", author);
//                        intent0.putExtra("type", type);
//                        intent0.putExtra("url", url);
//                        intent0.putExtra("notes", notes);
//                        intent0.putExtra("assigned_date", assigned_date);
//
//                        GroupPage.this.startActivity(intent0);
//
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            };
//
//            MaterialRequest materialRequest = new MaterialRequest(meetingID, getString(R.string.url) + "material_list.php", responseListener);
//            RequestQueue queue = Volley.newRequestQueue(GroupPage.this);
//            queue.add(materialRequest);
//        });


        group_btn = findViewById(R.id.group_list);
        group_btn.setOnClickListener(v -> {
            Intent intent1 = new Intent(GroupPage.this, Group_List.class);
            startActivity(intent1);
        });

    }

    public void f_delete_group(View view) {
        Intent intent = getIntent();
        String meetingID = intent.getStringExtra("meeting_id");
        String studentID = intent.getStringExtra("StudentID");

        // Define the URL of the PHP script as a constant
        final String URL_DELETE_ENROLL = getString(R.string.url) + "delete_enroll.php";

        // Show a confirmation dialog before deleting the enrollment
        new AlertDialog.Builder(this)
                .setTitle("Delete Enrollment")
                .setMessage("Are you sure you want to delete this enrollment?")
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Send the delete request to the server
                        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DELETE_ENROLL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                Log.d("delete_enroll", response);
                                if (response.equals("success")) {
                                    // Show a success message and go back to the previous activity
                                    Toast.makeText(GroupPage.this, "Enrollment deleted", Toast.LENGTH_SHORT).show();
                                    finish();
                                } else {
                                    // Show an error message
                                    Toast.makeText(GroupPage.this, "Failed to delete enrollment", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                // Show an error message
                                Toast.makeText(GroupPage.this, "Failed to communicate with the server", Toast.LENGTH_SHORT).show();
                            }
                        }) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                // Set the POST parameters
                                Map<String, String> params = new HashMap<>();
                                params.put("student_id", studentID);
                                params.put("meeting_id", meetingID);
                                return params;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
                        requestQueue.add(stringRequest);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }


}


