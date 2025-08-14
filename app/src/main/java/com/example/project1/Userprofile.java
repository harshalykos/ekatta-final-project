package com.example.project1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Userprofile extends AppCompatActivity {

    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_USERNAME = "Username";
    private static final String KEY_NUMBER = "Number";
    private static final String KEY_EMAIL = "EMAIL";
    private static final String KEY_DOB = "date";

    TextView tvNameHeader, tvName, tvPhone, tvEmail, tvDob;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_userprofile);

        // Back button setup
        ImageButton imageButton = findViewById(R.id.back_from_profile);
        imageButton.setOnClickListener(v -> {
            Intent intent = new Intent(Userprofile.this, DashScreen.class);
            startActivity(intent);
            finish();
        });

        // Bind views
        tvNameHeader = findViewById(R.id.register_name); // For header
        tvName = findViewById(R.id.register_name); // For profile details
        tvPhone = findViewById(R.id.edit_mobile_number);
        tvEmail = findViewById(R.id.edituserEmail);
        tvDob = findViewById(R.id.edituserDoB);

        // First try to get from Intent
        String username = getIntent().getStringExtra("username");
        String userphone = getIntent().getStringExtra("mobile");
        String useremail = getIntent().getStringExtra("email");
        String userdob = getIntent().getStringExtra("dob");

        if (isNullOrEmpty(username) || isNullOrEmpty(userphone) ||
                isNullOrEmpty(useremail) || isNullOrEmpty(userdob)) {

            SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            if (isNullOrEmpty(username)) {
                username = sharedPreferences.getString(KEY_USERNAME, "User");
            }
            if (isNullOrEmpty(userphone)) {
                userphone = sharedPreferences.getString(KEY_NUMBER, "number");
            }
            if (isNullOrEmpty(useremail)) {
                useremail = sharedPreferences.getString(KEY_EMAIL, "email@example.com");
            }
            if (isNullOrEmpty(userdob)) {
                userdob = sharedPreferences.getString(KEY_DOB, "01/01/2000");
            }
        }
        tvNameHeader.setText(username);
        tvName.setText(username);
        tvPhone.setText(userphone);
        tvEmail.setText(useremail);
        tvDob.setText(userdob);
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
