package com.example.project1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

public class Fund2 extends Fragment {

    public Fund2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fund2, container, false);

        TabLayout tabLayout = view.findViewById(R.id.fund_tab_lyt);
        TextView text = new TextView(requireContext());

        getChildFragmentManager().beginTransaction()
                .replace(R.id.fund_container, new Paid_funds())
                .commit();

        // Tab selection listener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        loadFragment(new Paid_funds());
                        break;
                    case 1:
                        loadFragment(new Unpaid_funds());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // no action
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                // no action
            }
        });

        return view;
    }

    // Corrected helper method
    private void loadFragment(Fragment fragment) {
        getChildFragmentManager().beginTransaction()
                .replace(R.id.fund_container, fragment)
                .commit();
    }
}
