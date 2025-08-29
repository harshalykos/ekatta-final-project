package com.example.project1;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

public class FilterBottomSheet extends BottomSheetDialogFragment {

    private TextView wing1, wing2, wing3;
    private TextView w1, w2, w3, w4, w5, w6;

    private final List<TextView> wingButtons = new ArrayList<>();
    private final List<TextView> flatButtons = new ArrayList<>();

    public FilterBottomSheet() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bottom_sheet_filter, container, false);

        // Wing filters
        wing1 = view.findViewById(R.id.wing1);
        wing2 = view.findViewById(R.id.wing2);
        wing3 = view.findViewById(R.id.wing3);

        // Flat filters
        w1 = view.findViewById(R.id.w1);
        w2 = view.findViewById(R.id.w2);
        w3 = view.findViewById(R.id.w3);
        w4 = view.findViewById(R.id.w4);
        w5 = view.findViewById(R.id.w5);
        w6 = view.findViewById(R.id.w6);

        // Group filters
        wingButtons.add(wing1);
        wingButtons.add(wing2);
        wingButtons.add(wing3);

        flatButtons.add(w1);
        flatButtons.add(w2);
        flatButtons.add(w3);
        flatButtons.add(w4);
        flatButtons.add(w5);
        flatButtons.add(w6);

        // Setup click handlers
        setupSingleSelectGroup(wingButtons);
        setupSingleSelectGroup(flatButtons);

        return view;
    }

    private void setupSingleSelectGroup(List<TextView> buttons) {
        for (TextView button : buttons) {
            button.setOnClickListener(v -> {
                for (TextView b : buttons) {
                    b.setBackgroundResource(R.drawable.b); // unselected background
                    b.setTextColor(Color.parseColor("#2D4464"));
                }

                button.setBackgroundResource(R.drawable.bg); // selected background
                button.setTextColor(Color.WHITE);
            });
        }
    }
}
