package com.example.project1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.Calendar;

public class Funds extends Fragment {

    private TextView totalFundTextView;
    private EditText addFundEditText;
    private Button addFundButton;

    // Expense-related views
    private EditText fundUserEditText, costFundEditText, dateEditText;
    private Button fundExpenseButton;

    private int totalFund = 24000;

    public Funds() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_funds, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize views
        totalFundTextView = view.findViewById(R.id.balance);
        addFundEditText = view.findViewById(R.id.add_fund);
        addFundButton = view.findViewById(R.id.fundadded);

        // Initialize expense fields
        fundUserEditText = view.findViewById(R.id.funduser);
        costFundEditText = view.findViewById(R.id.costfund);
        dateEditText = view.findViewById(R.id.user);
        fundExpenseButton = view.findViewById(R.id.fundadded_exp);

        updateTotalFundText();

        // 🔹 Date picker for the Date EditText
        dateEditText.setFocusable(false); // Prevent keyboard from showing up
        dateEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        // ➕ Add Funds button logic
        addFundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = addFundEditText.getText().toString().trim();

                if (TextUtils.isEmpty(input)) {
                    Toast.makeText(getActivity(), "Field cannot be empty", Toast.LENGTH_SHORT).show();
                    return;
                }
                try {
                    int addedAmount = Integer.parseInt(input);
                    totalFund += addedAmount;
                    updateTotalFundText();
                    addFundEditText.setText("");
                    Toast.makeText(getActivity(), "Funds added successfully!", Toast.LENGTH_SHORT).show();
                } catch (NumberFormatException e) {
                    Toast.makeText(getActivity(), "Please enter a valid number", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // ➖ Expense button logic
        fundExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fundUsedFor = fundUserEditText.getText().toString().trim();
                String cost = costFundEditText.getText().toString().trim();
                String date = dateEditText.getText().toString().trim();

                if (TextUtils.isEmpty(fundUsedFor) || TextUtils.isEmpty(cost) || TextUtils.isEmpty(date)) {
                    Toast.makeText(getActivity(), "Please fill all expense fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Navigate to AfterFund Activity
                Intent intent = new Intent(getActivity(), AfterFund.class);
                intent.putExtra("fundUsedFor", fundUsedFor);
                intent.putExtra("cost", cost);
                intent.putExtra("date", date);
                startActivity(intent);
            }
        });
    }

    private void showDatePickerDialog() {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getActivity(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
                        // Note: Month is 0-based, so add 1
                        String selectedDate = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                        dateEditText.setText(selectedDate);
                    }
                },
                year, month, day
        );

        datePickerDialog.show();
    }

    private void updateTotalFundText() {
        totalFundTextView.setText(totalFund + "/-");
    }
}
