package com.example.project1;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Registration extends AppCompatActivity {
    private static final String PREF_NAME = "MyPrefs";
    static final String KEY_NUMBER = "Mobile_number";
    private static final String KEY_USERNAME = "Username";
    private static final String KEY_EMAIL = "Email";
    private static final String KEY_DOB = "Date_birth";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registration);

        EditText user = findViewById(R.id.user);
        EditText phone = findViewById(R.id.userphone);
        EditText email = findViewById(R.id.userEmail);
        EditText dob = findViewById(R.id.userDoB);
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        Button generateOtp = findViewById(R.id.otpgenerate);
        ImageButton back = findViewById(R.id.back);

        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        String savedNumber = sharedPreferences.getString(KEY_NUMBER, null);
        if (savedNumber == null) {
            // If user already registered, skip to profile directly
            Intent intent = new Intent(Registration.this, Userprofile.class);
            startActivity(intent);
            finish();
        }

        dob.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(Registration.this,
                    (view, year1, month1, dayOfMonth) -> {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year1, month1, dayOfMonth);
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                        dob.setText(sdf.format(selectedDate.getTime()));
                    }, year, month, day);
            datePickerDialog.show();
        });

        generateOtp.setOnClickListener(v -> {
            String name = user.getText().toString().trim();
            String number = phone.getText().toString().trim();
            String emailId = email.getText().toString().trim();
            String date = dob.getText().toString().trim();
            int selectedGenderId = radioGroup.getCheckedRadioButtonId();

            if (name.isEmpty() || number.isEmpty() || emailId.isEmpty() || date.isEmpty() || selectedGenderId == -1) {
                Toast.makeText(Registration.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            } else {
                RadioButton selectedGender = findViewById(selectedGenderId);
                String gender = selectedGender.getText().toString();

                // Save both number and username in SharedPreferences
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NUMBER, number);
                editor.putString(KEY_USERNAME, name);
                editor.putString(KEY_EMAIL,emailId);
                editor.putString(KEY_DOB,date);
                editor.apply();

                Toast.makeText(Registration.this, "Employee Registered", Toast.LENGTH_SHORT).show();

                Intent intentOtp = new Intent(Registration.this, OTPVerify.class);
                intentOtp.putExtra("mobileno", number);
                intentOtp.putExtra("username", name);
                intentOtp.putExtra("email",emailId);
                intentOtp.putExtra("dob",date);
                startActivity(intentOtp);
                finish();
            }
        });

        back.setOnClickListener(v -> {
            Intent intentBack = new Intent(Registration.this, MainActivity.class);
            startActivity(intentBack);
            finish();
        });
    }
}
