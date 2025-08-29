package com.example.project1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Paid_funds extends Fragment {

    private RecyclerView recyclerView;
    private Fund_Adapter adapter;
    private List<Fund_model_class> fundList;
    ImageView imageButtona;

    public Paid_funds() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_paid_funds, container, false);

        recyclerView = view.findViewById(R.id.Paid_Fund_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        imageButtona = view.findViewById(R.id.paid_filter);
        imageButtona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterBottomSheet bottomSheet = new FilterBottomSheet();
                bottomSheet.show(getParentFragmentManager(), bottomSheet.getTag());
            }
        });


        // Initialize List
        fundList = new ArrayList<>();
        fundList.add(new Fund_model_class(
                R.drawable.user,
                "John Doe",
                "1234567890",
                "Society Maintenance",
                "Wing - A",
                "Flat - 23",
                R.drawable.paid,
                "Paid"
        ));

        fundList.add(new Fund_model_class(
                R.drawable.user,
                "Jane Smith",
                "9876543210",
                "Society Maintenance",
                "Wing - B",
                "Flat - 12",
                R.drawable.paid,
                "Unpaid"
        ));
        fundList.add(new Fund_model_class(
                R.drawable.user,
                "Jane Smith",
                "9876543210",
                "Society Maintenance",
                "Wing - B",
                "Flat - 12",
                R.drawable.paid,
                "Unpaid"
        ));
        fundList.add(new Fund_model_class(
                R.drawable.user,
                "Jane Smith",
                "9876543210",
                "Society Maintenance",
                "Wing - B",
                "Flat - 12",
                R.drawable.paid,
                "Unpaid"
        ));
        fundList.add(new Fund_model_class(
                R.drawable.user,
                "Jane Smith",
                "9876543210",
                "Society Maintenance",
                "Wing - B",
                "Flat - 12",
                R.drawable.paid,
                "Unpaid"
        ));
        fundList.add(new Fund_model_class(
                R.drawable.user,
                "Jane Smith",
                "9876543210",
                "Society Maintenance",
                "Wing - B",
                "Flat - 12",
                R.drawable.paid,
                "Unpaid"
        ));
        fundList.add(new Fund_model_class(
                R.drawable.user,
                "Jane Smith",
                "9876543210",
                "Society Maintenance",
                "Wing - B",
                "Flat - 12",
                R.drawable.paid,
                "Unpaid"
        ));

        // Set Adapter
        adapter = new Fund_Adapter(getContext(), fundList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
