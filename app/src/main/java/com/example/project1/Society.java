package com.example.project1;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class Society extends Fragment {

    private RecyclerView recyclerView;
    private Society_Adapter adapter1;
    private ArrayList<Society_list_item> item;
    ImageView filter;

    public Society() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_society, container, false);

        recyclerView = view.findViewById(R.id.recycler_view_society);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        item = new ArrayList<>();
        item.add(new Society_list_item(R.drawable.dp, "Shivam Kumar", "73979 10090"));
        item.add(new Society_list_item(R.drawable.dp, "Raj Bindra", "73979 10090"));
        item.add(new Society_list_item(R.drawable.dp, "Ram kumar", "73989 10090"));
        item.add(new Society_list_item(R.drawable.dp, "Parth Sharma", "73979 10890"));
        item.add(new Society_list_item(R.drawable.dp, "Harsh Jain", "73979 10090"));
        item.add(new Society_list_item(R.drawable.dp, "Himanshu Shah", "73899 10090"));
        item.add(new Society_list_item(R.drawable.dp, "Shyam Sharma", "73979 10090"));

        adapter1 = new Society_Adapter(getContext(), item);
        recyclerView.setAdapter(adapter1);

        filter = view.findViewById(R.id.filter);  // Correctly initialize filter

        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterBottomSheet bottomSheet = new FilterBottomSheet();
                bottomSheet.show(getParentFragmentManager(), bottomSheet.getTag());
            }
        });
        return view;
    }

}
