package com.example.project1;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private ImageSlider imageSlider;
    private RecyclerView recyclerView;
    private Adapters adapter;
    private ArrayList<SitesListItem> itemList;

    FloatingActionButton floatingActionButton;
    private List<SlideModel> slideModels;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // 🔍 SearchView
        SearchView searchView = view.findViewById(R.id.search_view);
        if (searchView != null) {
            int id = searchView.getContext()
                    .getResources()
                    .getIdentifier("android:id/search_src_text", null, null);
            EditText searchEditText = searchView.findViewById(id);
            if (searchEditText != null) {
                searchEditText.setHintTextColor(Color.BLACK);
                searchEditText.setTextColor(Color.BLACK);
            }

            searchView.setOnQueryTextFocusChangeListener((v, hasFocus) -> {
                if (hasFocus) {
                    openSearchPopup();
                }
            });
        }

        // 🖼️ Image Slider
        imageSlider = view.findViewById(R.id.image_slider);
        slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.image1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image4, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels);

        // 📝 RecyclerView
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        itemList = new ArrayList<>();
        itemList.add(new SitesListItem(R.drawable.image_a, R.drawable.location,
                R.drawable.building, R.drawable.measurement,
                "Project -1", "cidco n2 aurangabad , maharastra - 431001",
                "2 BHK - 3 BHK", "1250 Sq.Ft. To 1672 Sq.Ft (Carpet)"));

        itemList.add(new SitesListItem(R.drawable.images_b2, R.drawable.location,
                R.drawable.building, R.drawable.measurement,
                "Project -2", "cidco n2 aurangabad , maharastra - 431001",
                "2 BHK - 3 BHK", "1550 Sq.Ft. To 1682 Sq.Ft (Carpet)"));

        itemList.add(new SitesListItem(R.drawable.images_c, R.drawable.location,
                R.drawable.building, R.drawable.measurement,
                "Project -3", "cidco n2 aurangabad , maharastra - 431001",
                "2 BHK - 3 BHK", "1550 Sq.Ft. To 1682 Sq.Ft (Carpet)"));

        adapter = new Adapters(getContext(), itemList);
        recyclerView.setAdapter(adapter);

        // ✅ FAB click: open Chat_1 activity
        floatingActionButton = view.findViewById(R.id.floating_button);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Chat_1.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void openSearchPopup() {
        Dialog dialog = new Dialog(requireContext());
        dialog.setContentView(R.layout.search_popup);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }
}
