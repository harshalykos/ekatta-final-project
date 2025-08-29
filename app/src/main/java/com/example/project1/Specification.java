package com.example.project1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

public class Specification extends Fragment {

    Button enqu;

    public Specification() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_specification, container, false);

        // Initialize button
        enqu = view.findViewById(R.id.Enquriy); // Make sure this ID exists in your XML

        // Set onClick to show bottom sheet
        enqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Enquiry_bottom_sheet enquiryBottomSheet = new Enquiry_bottom_sheet();
                enquiryBottomSheet.show(getParentFragmentManager(), "EnquiryBottomSheet");
            }
        });

        return view;
    }
}
