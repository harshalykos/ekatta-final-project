package com.example.project1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class History extends Fragment {

    private RecyclerView recyclerView;
    private HistoryAdapter adapter;
    private ArrayList<HistoryItem> historyList;

    public History() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        recyclerView = view.findViewById(R.id.history_Fund_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Dummy data
        historyList = new ArrayList<>();
        historyList.add(new HistoryItem("Society Maintenance", "23/12/22", "2 Days Ago", "Rs 10,000", R.drawable.recept));
        historyList.add(new HistoryItem("Repairing", "21/12/22", "4 Days Ago", "Rs 5,500", R.drawable.recept));
        historyList.add(new HistoryItem("Electricity", "18/12/22", "1 Week Ago", "Rs 3,200", R.drawable.recept));

        adapter = new HistoryAdapter(historyList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
