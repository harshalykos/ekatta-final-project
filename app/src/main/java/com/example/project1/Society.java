package com.example.project1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
public class Society extends Fragment {
    private RecyclerView recyclerView;
    private Society_Adapter adapter1;
    private ArrayList<Society_list_item> item;
    private ImageView filter;
    private TextView view_history2;

    public Society() {
        // Required empty public constructor
    }

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
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
        filter = view.findViewById(R.id.filter);
        view_history2 = view.findViewById(R.id.viewhistory2); // ⬅️ Make sure this ID matches your layout

        // Filter Bottom Sheet Click
        filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterBottomSheet bottomSheet = new FilterBottomSheet();
                bottomSheet.show(getParentFragmentManager(), bottomSheet.getTag());
            }
        });

//         Navigate to View_History fragment
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getActivity(), DashScreen.class);
//                intent.putExtra("fragment_to_load", "View_history");
//                startActivity(intent);
//            }
//        });
//        view_history.setOnClickListener(v -> {
//            Fragment viewHistoryFragment = new View_history();
//            requireActivity().getSupportFragmentManager()
//                    .beginTransaction()
//                    .replace(R.id.container, viewHistoryFragment)
//                    .addToBackStack(null)
//                    .commit();
//        })
        view_history2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireContext(), DashScreen.class);
                intent.putExtra("fragment_to_load", "View_history"); // Pass data to DashScreen
                startActivity(intent);
            }
        });
        return view;
    }
}
