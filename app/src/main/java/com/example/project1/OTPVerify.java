package com.example.project1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class OTPVerify extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otpverify);

        TextView num = findViewById(R.id.textView);
        TextView num2 = findViewById(R.id.textView1);
        ImageButton back = findViewById(R.id.back_from_otp);

        String loginMobile = getIntent().getStringExtra("mobileNumber");
        String registerMobile = getIntent().getStringExtra("mobileno");

        if (registerMobile != null) {
            num2.setText("Enter OTP sent to " + registerMobile);
            num.setText("");
        } else if (loginMobile != null) {
            num.setText("Enter OTP sent to " + loginMobile);
            num2.setText("");
        } else {
            num.setText("No mobile number provided");
            num2.setText("");
        }

        back.setOnClickListener(v -> {
            Intent intentBack = new Intent(OTPVerify.this, MainActivity.class);
            startActivity(intentBack);
            finish();
        });

        EditText otp1 = findViewById(R.id.otp_digit_1);
        EditText otp2 = findViewById(R.id.otp_digit_2);
        EditText otp3 = findViewById(R.id.otp_digit_3);
        EditText otp4 = findViewById(R.id.otp_digit_4);
        Button verify = findViewById(R.id.ver_otp);

        addTextWatcher(otp1, otp2);
        addTextWatcher(otp2, otp3);
        addTextWatcher(otp3, otp4);
        addTextWatcher(otp4, null);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String otp = otp1.getText().toString() +
                        otp2.getText().toString() +
                        otp3.getText().toString() +
                        otp4.getText().toString();

                String pass = "7878";
                if (otp.length() == 4 && pass.equals(otp)) {
                    Intent intent = new Intent(OTPVerify.this, SuccessOTP.class);
                    startActivity(intent);
                    finish();
                } else {
                    new cn.pedant.SweetAlert.SweetAlertDialog(OTPVerify.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Invalid OTP")
                            .setContentText("Please enter all 4 digits.")
                            .show();
                }
            }
        });
    }

    private void addTextWatcher(EditText current, EditText next) {
        current.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 1 && next != null) {
                    next.requestFocus();
                }
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
    }

}