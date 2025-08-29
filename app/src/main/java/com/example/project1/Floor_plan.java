package com.example.project1;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Floor_plan extends Fragment {

    TextView textDescription;
    TextView textToggle;

    public Floor_plan() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_floor_plan, container, false);

        textDescription = view.findViewById(R.id.text_description);
        textToggle = view.findViewById(R.id.text_toggle);

        // Toggle See More / See Less
        textToggle.setOnClickListener(new View.OnClickListener() {
            boolean isExpanded = false;

            @Override
            public void onClick(View v) {
                if (isExpanded) {
                    textDescription.setMaxLines(3);
                    textDescription.setEllipsize(TextUtils.TruncateAt.END);
                    textToggle.setText("See More");
                    isExpanded = false;
                } else {
                    textDescription.setMaxLines(Integer.MAX_VALUE);
                    textDescription.setEllipsize(null);
                    textToggle.setText("See Less");
                    isExpanded = true;
                }
            }
        });

        Button TWO_BHK = view.findViewById(R.id.btn2BHK);
        Button THREE_BHK = view.findViewById(R.id.btn3BHK);

        // Open TWO_BHK fragment inside DashScreen
        TWO_BHK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), DashScreen.class);
                intent.putExtra("fragment_to_load", "TWO_BHK");
                startActivity(intent);
            }
        });
        // Open THREE_BHK fragment inside DashScreen
        THREE_BHK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), DashScreen.class);
                intent.putExtra("fragment_to_load", "THREE_BHK");
                startActivity(intent);
            }
        });

        return view;
    }
}
