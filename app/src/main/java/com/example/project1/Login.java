package com.example.project1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    DBHelper dbHelper;
    private static final String PREF_NAME = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        EditText phone = findViewById(R.id.login_number);
        Button loginOTP = findViewById(R.id.login_otp);

        dbHelper = new DBHelper(this);

        loginOTP.setOnClickListener(v -> {
            String mobNo = phone.getText().toString().trim();

            if (mobNo.isEmpty()) {
                Toast.makeText(Login.this, "Please enter your mobile number", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean exists = dbHelper.checkUserExists(mobNo);

            if (exists) {
                // ✅ Save logged in mobile
                SharedPreferences prefs = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("loggedInMobile", mobNo);
                editor.apply();

                Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();

                // ✅ Go to profile
                Intent intent = new Intent(Login.this, OTPVerify.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Login.this, "No registered mobile number found! Please register first.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
