package com.example.ajeer_map.controller;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ajeer_map.R;
import com.example.ajeer_map.model.DatabaseHelper;
import com.example.ajeer_map.model.User;

import java.io.ByteArrayOutputStream;

public class RegisterActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE=1;
    private EditText editTextName;
    private EditText editTextJob;
    private EditText editTextNewPhoneNumber;
    private EditText editTextNewPassword;
    private ImageView imageViewPhoto;
    private Button buttonTakePhoto;
    private Button buttonRegister;
    private byte[] identityCardPhoto;
    private DatabaseHelper databaseHelper;
    protected void onCreate(Bundle savedInstancesState) {
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_register);
        editTextName = findViewById(R.id.editTextName);
        editTextJob = findViewById(R.id.editTextJob);
        editTextNewPhoneNumber = findViewById(R.id.editTextNewPhoneNumber);
        editTextNewPassword = findViewById(R.id.editTextNewPassword);
        imageViewPhoto = findViewById(R.id.imageViewPhoto);
        buttonTakePhoto = findViewById(R.id.buttonTakePhoto);
        buttonRegister = findViewById(R.id.buttonRegister);
        databaseHelper = new DatabaseHelper(this);
        buttonTakePhoto.setOnClickListener(v -> dispatchTakePictureIntent());
        buttonRegister.setOnClickListener(v -> {
            String name = editTextName.getText().toString();
            String job = editTextJob.getText().toString();
            String phoneNumber = editTextNewPhoneNumber.getText().toString();
            String password = editTextNewPassword.getText().toString();
            User newuser = new User(phoneNumber, password, name, job, identityCardPhoto);
            databaseHelper.addUser(newuser);
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
        private void dispatchTakePictureIntent() {
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);

            }
        }
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
                super.onActivityResult(requestCode, resultCode, data);
                if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                    Bundle extras = data.getExtras();
                    Bitmap imageBitmap = (Bitmap) extras.get("data");
                    imageViewPhoto.setImageBitmap(imageBitmap);

                    // Convert Bitmap to byte array
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();
                    imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                    identityCardPhoto = stream.toByteArray();
                }
            }
    }



