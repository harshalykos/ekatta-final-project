package com.example.project1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class Unpaid_funds extends Fragment {

    private RecyclerView recyclerView;
    private Unpaid_Adapter adapter;
    private List<Unpaid_model> ufundList;

    ImageView imageButton;


    public Unpaid_funds() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_unpaid_funds, container, false);

        recyclerView = view.findViewById(R.id.unpaid_Fund_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        imageButton = view.findViewById(R.id.unpaid_filter);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterBottomSheet bottomSheet = new FilterBottomSheet();
                bottomSheet.show(getParentFragmentManager(), bottomSheet.getTag());
            }
        });

        // Sample data
        ufundList = new ArrayList<>();
        ufundList.add(new Unpaid_model(
                R.drawable.user,
                "John Doe",
                "1234567890",
                "Society Maintenance",
                "Wing - A",
                "Flat - 23",
                R.drawable.unpaid,
                "Unpaid"
        ));

        ufundList.add(new Unpaid_model(
                R.drawable.user,
                "Jane Smith",
                "9876543210",
                "Society Maintenance",
                "Wing - B",
                "Flat - 12",
                R.drawable.unpaid,
                "Unpaid"
        ));
        ufundList.add(new Unpaid_model(
                R.drawable.user,
                "Jane Smith",
                "9876543210",
                "Society Maintenance",
                "Wing - B",
                "Flat - 12",
                R.drawable.unpaid,
                "Unpaid"
        ));
        ufundList.add(new Unpaid_model(
                R.drawable.user,
                "Jane Smith",
                "9876543210",
                "Society Maintenance",
                "Wing - B",
                "Flat - 12",
                R.drawable.unpaid,
                "Unpaid"
        ));
        ufundList.add(new Unpaid_model(
                R.drawable.user,
                "Jane Smith",
                "9876543210",
                "Society Maintenance",
                "Wing - B",
                "Flat - 12",
                R.drawable.unpaid,
                "Unpaid"
        ));
        ufundList.add(new Unpaid_model(
                R.drawable.user,
                "Jane Smith",
                "9876543210",
                "Society Maintenance",
                "Wing - B",
                "Flat - 12",
                R.drawable.unpaid,
                "Unpaid"
        ));
        ufundList.add(new Unpaid_model(
                R.drawable.user,
                "Jane Smith",
                "9876543210",
                "Society Maintenance",
                "Wing - B",
                "Flat - 12",
                R.drawable.unpaid,
                "Unpaid"
        ));
        ufundList.add(new Unpaid_model(
                R.drawable.user,
                "Jane Smith",
                "9876543210",
                "Society Maintenance",
                "Wing - B",
                "Flat - 12",
                R.drawable.unpaid,
                "Unpaid"
        ));

        // Set adapter
        adapter = new Unpaid_Adapter(getContext(), ufundList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
