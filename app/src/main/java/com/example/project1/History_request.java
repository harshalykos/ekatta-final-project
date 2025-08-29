package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class History_request extends Fragment {

    private RecyclerView recyclerView;
    private ReqAdapter adapter;
    private List<ReqModel> requestList;

    public History_request() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history_request, container, false);

        recyclerView = view.findViewById(R.id.history_req_Fund_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        requestList = new ArrayList<>();
        requestList.add(new ReqModel("Fund request for society maintenance", "RS. 36274.00", "23/12/22", "2 Days Ago", R.drawable.cc));
        requestList.add(new ReqModel("Security System Upgrade", "RS. 15000.00", "20/12/22", "5 Days Ago", R.drawable.cc));
        requestList.add(new ReqModel("Garden Maintenance", "RS. 9500.00", "18/12/22", "7 Days Ago", R.drawable.cc));

        adapter = new ReqAdapter(requestList);
        recyclerView.setAdapter(adapter);

        AppCompatButton go = view.findViewById(R.id.paidbtn);
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DashScreen.class);
                intent.putExtra("fragment_to_load", "Fund2");
                startActivity(intent);
            }
        });

        return view;
    }
}
