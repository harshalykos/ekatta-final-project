package com.example.project1;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Userprofile extends AppCompatActivity {

    private static final String PREF_NAME = "MyPrefs";

    TextView tvNameHeader, tvName, tvPhone, tvEmail, tvDob;
    ImageView edituser, editphone, editemail, editdob, backButton ,editpro;
    Button save;

    String un, upo, ue, ud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);

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
        String mobile = sharedPreferences.getString("loggedInMobile", null);

        if (mobile != null) {
            DBHelper dbHelper = new DBHelper(this);
            Cursor cursor = dbHelper.getUserByNumber(mobile);
            if (cursor.moveToFirst()) {
                un = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_NAME));
                upo = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_NUMBER));
                ue = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_EMAIL));
                ud = cursor.getString(cursor.getColumnIndexOrThrow(DBHelper.COL_DOB));
            }
            cursor.close();
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

        editdob.setOnClickListener(v -> showEditDialog("Edit DOB", ud, newValue -> {
            ud = newValue;
            tvDob.setText(ud);
        }));

        save.setOnClickListener(v -> {
            // Update DB
            DBHelper dbHelper = new DBHelper(this);
            ContentValues values = new ContentValues();
            values.put(DBHelper.COL_NAME, un);
            values.put(DBHelper.COL_NUMBER, upo);
            values.put(DBHelper.COL_EMAIL, ue);
            values.put(DBHelper.COL_DOB, ud);
            dbHelper.getWritableDatabase().update(DBHelper.TABLE_NAME, values, DBHelper.COL_NUMBER + "=?", new String[]{upo});

            Toast.makeText(Userprofile.this, "Profile updated successfully!", Toast.LENGTH_SHORT).show();
        });
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

    interface OnValueSetListener {
        void onValueSet(String newValue);
    }
}
