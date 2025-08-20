package com.example.project1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    private static final String PREF_NAME = "MyPrefs";    // same as Userprofile
    private static final String KEY_NUMBER = "Number";     // must match Userprofile key

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        EditText phone = findViewById(R.id.login_number);
        Button loginOTP = findViewById(R.id.login_otp);

        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        loginOTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mobNo = phone.getText().toString().trim();
                String savedNumber = sharedPreferences.getString(KEY_NUMBER, null);

                if (mobNo.isEmpty()) {
                    Toast.makeText(Login.this, "Please enter your mobile number", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (savedNumber == null) {
                    Toast.makeText(Login.this, "No registered mobile number found! Please register first.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (mobNo.equals(savedNumber)) {
                    Toast.makeText(Login.this, "Mobile Number Verified", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, OTPVerify.class);
                    intent.putExtra("mobileNumber", mobNo);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Invalid Mobile Number", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
