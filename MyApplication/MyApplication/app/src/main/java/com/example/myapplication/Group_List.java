package com.example.myapplication;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class Group_List extends AppCompatActivity {

    EditText studentIdEditText;
    EditText meetingIdEditText;
    Button submitBtn;

    TableLayout groupTableLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_list);

        groupTableLayout = findViewById(R.id.groupTableLayout);
        studentIdEditText = findViewById(R.id.studentIdEditText);
        meetingIdEditText = findViewById(R.id.meetingIdEditText);
        submitBtn = findViewById(R.id.group);

        String url = getString(R.string.url) + "group_list.php";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean success = jsonObject.getBoolean("success");

                            if (success) {
                                JSONArray jsonArray = jsonObject.getJSONArray("groups");

                                // Add table headers
                                TableRow headerRow = new TableRow(Group_List.this);
                                headerRow.addView(createTextView("Group Name"));
                                headerRow.addView(createTextView("Meeting ID"));
                                headerRow.addView(createTextView("Date"));
                                headerRow.addView(createTextView("Start Time"));
                                groupTableLayout.addView(headerRow);

                                // Add table rows for each group
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject group = jsonArray.getJSONObject(i);
                                    String groupName = group.getString("group_name");
                                    String meetingId = group.getString("meeting_id");
                                    String date = group.getString("date");
                                    String startTime = group.getString("start_time");

                                    TableRow tableRow = new TableRow(Group_List.this);
                                    tableRow.addView(createTextView(groupName));
                                    tableRow.addView(createTextView(meetingId));
                                    tableRow.addView(createTextView(date));
                                    tableRow.addView(createTextView(startTime));
                                    groupTableLayout.addView(tableRow);
                                }
                            } else {
                                Toast.makeText(Group_List.this, "No groups found.", Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(Group_List.this, "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(Group_List.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        Volley.newRequestQueue(this).add(stringRequest);

    }

    private TextView createTextView(String text) {
        TextView textView = new TextView(this);
        textView.setText(text);
        textView.setPadding(16, 16, 16, 16);
        textView.setTextSize(18);
        return textView;
    }

    public void submit_btn(View view) {
        String studentID = studentIdEditText.getText().toString();
        String meetingID = meetingIdEditText.getText().toString();
        String url = getString(R.string.url) + "join_group.php";
        if(!studentID.equals("") && !meetingID.equals("")){
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.d("res", response);
                    if (response.equals("success")) {
                        Toast.makeText(Group_List.this, "You enrolled into the class.", Toast.LENGTH_SHORT).show();
                        ///////////////////////////////change the destination
                        Intent intent = new Intent(Group_List.this, GroupPage.class);
                        startActivity(intent);
                        finish();
                    } else if (response.equals("failure")) {
                        Toast.makeText(Group_List.this, "Invalid IDs", Toast.LENGTH_SHORT).show();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Group_List.this, error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> data = new HashMap<>();
                    data.put("student_id", studentID);
                    data.put("meet_id", meetingID);
                    return data;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }else{
            Toast.makeText(this, "Fields can not be empty!", Toast.LENGTH_SHORT).show();
        }
    }

}

