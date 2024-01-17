package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button student;

    Button admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        student = findViewById(R.id.student);
        admin = findViewById(R.id.admin);

        Button group;
        group = findViewById(R.id.group);
        group.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, Group_List.class);
            startActivity(intent);
        });

    }

    public void signInStudent(View view) {
        Intent intent = new Intent(MainActivity.this, SigninStudent.class);
        startActivity(intent);
    }

//    public void groupList(View view) {
//        Intent intent = new Intent(MainActivity.this, Group_List.class);
//        startActivity(intent);
//    }
    public void signInAdmin(View view) {
        Intent intent = new Intent(MainActivity.this, SigninStudent.class);
        startActivity(intent);
    }
}