package com.example.myapplication;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.AuthFailureError;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import androidx.appcompat.app.AppCompatActivity;

public class SigninStudent extends AppCompatActivity {

    EditText etEmail;
    EditText etPassword;
    Button signInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_student);

        etEmail = findViewById(R.id.etID);
        etPassword = findViewById(R.id.password);
        signInButton = findViewById(R.id.s_signin);////////change the variable name

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = etEmail.getText().toString();
                String password = etPassword.getText().toString();
                Intent intent = new Intent(SigninStudent.this, Homepage.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }
}
