package com.example.project1;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class Chat_A extends Fragment {

    RecyclerView recyclerView;
    CardAdapter adapter;
    List<CardItem> cardList;

    public Chat_A() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_chat__a, container, false);

        recyclerView = view.findViewById(R.id.Chat_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false));

        cardList = new ArrayList<>();
        cardList.add(new CardItem("20-04-2022", "202201001", "Electricity", "pending",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                R.drawable.eclips, R.drawable.pip));
        cardList.add(new CardItem("22-08-2025", "202201001", "computer", "completed",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum is simply dummy text of the printing and typesetting industry.",
                R.drawable.eclips, R.drawable.pip));

        // Add more dummy items if needed
        // cardList.add(...);

        adapter = new CardAdapter(getContext(), cardList);
        recyclerView.setAdapter(adapter);

        return view;
    }
}
