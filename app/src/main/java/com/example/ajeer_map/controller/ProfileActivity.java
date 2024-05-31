package com.example.ajeer_map.controller;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ajeer_map.R;
import com.example.ajeer_map.model.User;

public class ProfileActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        User user = (User) getIntent().getSerializableExtra("user");

        if (user != null) {
            Log.d("ProfileActivity", "Nom : " + user.getName());
            Log.d("ProfileActivity", "Numéro de téléphone : " + user.getPhoneNumber());
            Log.d("ProfileActivity", "Travail : " + user.getJob());

            // Afficher les données de l'utilisateur dans les vues appropriées
            TextView textViewName = findViewById(R.id.textViewName);
            textViewName.setText("Nom : " + user.getName());

            TextView textViewJob = findViewById(R.id.textViewJob);
            textViewJob.setText("Travail : " + user.getJob());

            TextView textViewPhoneNumber = findViewById(R.id.textViewPhoneNumber);
            textViewPhoneNumber.setText("Numéro de téléphone : " + user.getPhoneNumber());
        } else {
            Log.d("ProfileActivity", "Aucune donnée d'utilisateur reçue");
        }
    }
}
