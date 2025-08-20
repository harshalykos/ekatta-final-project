package com.example.project1;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Registration extends AppCompatActivity {

    private static final String PREF_NAME = "MyPrefs";
    static final String KEY_NUMBER = "Number";
    static final String KEY_USERNAME = "Username";
    static final String KEY_EMAIL = "EMAIL";
    static final String KEY_DOB = "date";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        // If already registered, go directly to profile
        if (savedNumber == null) {
            startActivity(new Intent(Registration.this, Userprofile.class));
            return;
        }

        dob.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    Registration.this,
                    (view, year, month, dayOfMonth) -> {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        String dateStr = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                                .format(selectedDate.getTime());
                        dob.setText(dateStr);
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
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
            }

            RadioButton selectedGender = findViewById(selectedGenderId);
            String gender = selectedGender.getText().toString();

            // Save registration data
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_NUMBER, number);
            editor.putString(KEY_USERNAME, name);
            editor.putString(KEY_EMAIL, emailId);
            editor.putString(KEY_DOB, date);
            editor.apply();

            Toast.makeText(Registration.this, "Employee Registered", Toast.LENGTH_SHORT).show();

            Intent intentOtp = new Intent(Registration.this, OTPVerify.class);
            intentOtp.putExtra("mobileno", number);
            intentOtp.putExtra("username", name);
            intentOtp.putExtra("email", emailId);
            intentOtp.putExtra("dob", date);
            startActivity(intentOtp);
            finish();
        });

        back.setOnClickListener(v -> {
            startActivity(new Intent(Registration.this, MainActivity.class));
            finish();
        });
    }
}
