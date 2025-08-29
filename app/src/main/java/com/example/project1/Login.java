package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    DBHelper dbHelper;

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
                Toast.makeText(Login.this, "Mobile Number Verified", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Login.this, OTPVerify.class);
                intent.putExtra("mobileNumber", mobNo);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Login.this, "No registered mobile number found! Please register first.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
