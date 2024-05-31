package com.example.ajeer_map.controller;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ajeer_map.R;
import com.example.ajeer_map.model.DatabaseHelper;
import com.example.ajeer_map.model.User;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextPhoneNumber;
    private EditText editTextPassword;
    private Button buttonLogin;
    private TextView textViewRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewRegister = findViewById(R.id.textViewRegister);

        buttonLogin.setOnClickListener(v -> {
            // Retrieve the values entered in the phone number and password fields
            String phoneNumber = editTextPhoneNumber.getText().toString();
            String password = editTextPassword.getText().toString();

            // Check if the fields are empty
            if (phoneNumber.isEmpty() || password.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Check user credentials
            DatabaseHelper dbHelper = new DatabaseHelper(this);
            User user = dbHelper.getUser(phoneNumber);

            if (user != null) {
                // Display user data in logs
                Log.d("LoginActivity", "User data retrieved:");
                Log.d("LoginActivity", "Name: " + user.getName());
                Log.d("LoginActivity", "Phone number: " + user.getPhoneNumber());
                Log.d("LoginActivity", "Job: " + user.getJob());

                if (password.equals(user.getPassword())) {
                    Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
                    intent.putExtra("user", user);
                    Log.d("LoginActivity", "Intent created, starting activity...");
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(getApplicationContext(), "User not found", Toast.LENGTH_SHORT).show();
            }
        });

        textViewRegister.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}
