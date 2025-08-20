package com.example.project1;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class Userprofile extends AppCompatActivity {

    private static final String PREF_NAME = "MyPrefs";
    private static final String KEY_USERNAME = "Username";
    private static final String KEY_NUMBER = "Number";
    private static final String KEY_EMAIL = "EMAIL";
    private static final String KEY_DOB = "date";

    TextView tvNameHeader, tvName, tvPhone, tvEmail, tvDob;
    ImageView edituser, editphone, editemail, editdob, backButton;
    Button save;

    String un, upo, ue, ud;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

        // This is the fix: using ImageView instead of ImageButton
        backButton = findViewById(R.id.back_from_profile);
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(Userprofile.this, DashScreen.class));
            finish();
        });

        tvNameHeader = findViewById(R.id.register_name);
        tvName = findViewById(R.id.editusername);
        tvPhone = findViewById(R.id.edit_mobile_number);
        tvEmail = findViewById(R.id.edituserEmail);
        tvDob = findViewById(R.id.edituserDoB);

        edituser = findViewById(R.id.eduser);
        editphone = findViewById(R.id.edphone);
        editemail = findViewById(R.id.edemail);
        editdob = findViewById(R.id.edob);
        save = findViewById(R.id.save_profile_changes);

        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        un = getIntent().getStringExtra("username");
        if (isNullOrEmpty(un)) {
            un = sharedPreferences.getString(KEY_USERNAME, "User");
        }

        upo = getIntent().getStringExtra("mobile");
        if (isNullOrEmpty(upo)) {
            upo = sharedPreferences.getString(KEY_NUMBER, "0000000000");
        }

        ue = getIntent().getStringExtra("email");
        if (isNullOrEmpty(ue)) {
            ue = sharedPreferences.getString(KEY_EMAIL, "email@example.com");
        }

        ud = getIntent().getStringExtra("dob");
        if (isNullOrEmpty(ud)) {
            ud = sharedPreferences.getString(KEY_DOB, "01/01/2000");
        }

        tvNameHeader.setText(un);
        tvName.setText(un);
        tvPhone.setText(upo);
        tvEmail.setText(ue);
        tvDob.setText(ud);

        edituser.setOnClickListener(v -> showEditDialog("Edit Username", un, newValue -> {
            un = newValue;
            tvName.setText(un);
            tvNameHeader.setText(un);
        }));

        editphone.setOnClickListener(v -> showEditDialog("Edit Phone Number", upo, newValue -> {
            upo = newValue;
            tvPhone.setText(upo);
        }));

        editemail.setOnClickListener(v -> showEditDialog("Edit Email", ue, newValue -> {
            ue = newValue;
            tvEmail.setText(ue);
        }));

        editdob.setOnClickListener(v -> showDatePicker());

        save.setOnClickListener(v -> {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(KEY_USERNAME, un);
            editor.putString(KEY_NUMBER, upo);
            editor.putString(KEY_EMAIL, ue);
            editor.putString(KEY_DOB, ud);
            editor.apply();

            Toast.makeText(Userprofile.this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
        });
    }

    private boolean isNullOrEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    private void showEditDialog(String title, String currentValue, OnValueSetListener listener) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);

        final EditText input = new EditText(this);
        input.setText(currentValue);
        builder.setView(input);

        builder.setPositiveButton("OK", (dialog, which) -> {
            String newValue = input.getText().toString().trim();
            if (!newValue.isEmpty()) {
                listener.onValueSet(newValue);
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        builder.show();
    }

    private void showDatePicker() {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                (view, year, month, dayOfMonth) -> {
                    ud = String.format("%02d/%02d/%04d", dayOfMonth, month + 1, year);
                    tvDob.setText(ud);
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    interface OnValueSetListener {
        void onValueSet(String newValue);
    }
}
