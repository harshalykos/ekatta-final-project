package com.example.project1;

import android.app.DatePickerDialog;
import android.content.Intent;
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

    DBHelper dbHelper;

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

        dbHelper = new DBHelper(this);

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

            boolean inserted = dbHelper.insertUser(name, number, emailId, date, gender);

            if (inserted) {
                Toast.makeText(Registration.this, "User Registered Successfully", Toast.LENGTH_SHORT).show();
                Intent intentOtp = new Intent(Registration.this, Login.class);
                startActivity(intentOtp);
                finish();
            } else {
                Toast.makeText(Registration.this, "User already registered with this number", Toast.LENGTH_SHORT).show();
            }
        });

        back.setOnClickListener(v -> {
            startActivity(new Intent(Registration.this, MainActivity.class));
            finish();
        });
    }
}
