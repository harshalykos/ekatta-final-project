package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;

public class General_notifiction extends Fragment {

    public General_notifiction() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_general_notifiction, container, false);

        FrameLayout frame1 = view.findViewById(R.id.frame1);
        FrameLayout frame2 = view.findViewById(R.id.frame2);
        FrameLayout frame3 = view.findViewById(R.id.frame3);
        FrameLayout frame4 = view.findViewById(R.id.frame4);

        View.OnClickListener frameClickListener = v -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            // Optionally pass data with intent.putExtra("key", "value");
            startActivity(intent);
        };

        frame1.setOnClickListener(frameClickListener);
        frame2.setOnClickListener(frameClickListener);
        frame3.setOnClickListener(frameClickListener);
        frame4.setOnClickListener(frameClickListener);

        return view;
    }
}
