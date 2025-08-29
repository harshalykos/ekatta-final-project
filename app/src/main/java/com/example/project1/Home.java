package com.example.project1;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private ImageSlider imageSlider;
    private RecyclerView recyclerView;
    private Adapters adapter;
    private ArrayList<SitesListItem> itemList;
    private FloatingActionButton floatingActionButton;

    private SearchView searchView;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // SearchView setup
        searchView = view.findViewById(R.id.search_view);
        if (searchView != null) {
            int id = searchView.getContext().getResources()
                    .getIdentifier("android:id/search_src_text", null, null);
            EditText searchEditText = searchView.findViewById(id);
            if (searchEditText != null) {
                searchEditText.setHintTextColor(Color.GRAY);
                searchEditText.setTextColor(Color.BLACK);
            }

            // Open SearchPopupActivity when SearchView is clicked (expanded)
            searchView.setOnSearchClickListener(v -> {
                Intent intent = new Intent(requireContext(), SearchPopupActivity.class);
                startActivity(intent);
                // Collapse SearchView immediately after opening popup
                searchView.onActionViewCollapsed();
            });

            // Also open popup on submit (optional)
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Intent intent = new Intent(requireContext(), SearchPopupActivity.class);
                    intent.putExtra("query", query);
                    startActivity(intent);
                    // Collapse SearchView after submit
                    searchView.onActionViewCollapsed();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }

        // Image slider setup
        imageSlider = view.findViewById(R.id.image_slider);
        List<SlideModel> slideModels = new ArrayList<>();
        slideModels.add(new SlideModel(R.drawable.image1, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image2, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image3, ScaleTypes.FIT));
        slideModels.add(new SlideModel(R.drawable.image4, ScaleTypes.FIT));
        imageSlider.setImageList(slideModels, ScaleTypes.FIT);

        imageSlider.setItemClickListener(position -> {
            int imageResId = 0;
            if (position == 0) imageResId = R.drawable.image1;
            else if (position == 1) imageResId = R.drawable.image2;
            else if (position == 2) imageResId = R.drawable.image3;
            else if (position == 3) imageResId = R.drawable.image4;

            if (imageResId != 0) {
                showImagePopup(imageResId);
            }
        });

        // RecyclerView setup
        recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        itemList = new ArrayList<>();

        List<Integer> project1Images = List.of(R.drawable.main_buil, R.drawable.b1, R.drawable.b2, R.drawable.b3);
        List<Integer> project2Images = List.of(R.drawable.b2, R.drawable.main_buil, R.drawable.b3, R.drawable.b1);
        List<Integer> project3Images = List.of(R.drawable.b2, R.drawable.main_buil, R.drawable.b1, R.drawable.b3);

        itemList.add(new SitesListItem(project1Images, R.drawable.location, R.drawable.building, R.drawable.measurement,
                "Project -1", "Cidco N2 Aurangabad, Maharashtra - 431001",
                "2 BHK - 3 BHK", "1250 Sq.Ft. To 1672 Sq.Ft (Carpet)"));

        itemList.add(new SitesListItem(project2Images, R.drawable.location, R.drawable.building, R.drawable.measurement,
                "Project -2", "Cidco N2 Aurangabad, Maharashtra - 431001",
                "2 BHK - 3 BHK", "1550 Sq.Ft. To 1682 Sq.Ft (Carpet)"));

        itemList.add(new SitesListItem(project3Images, R.drawable.location, R.drawable.building, R.drawable.measurement,
                "Project -3", "Cidco N2 Aurangabad, Maharashtra - 431001",
                "2 BHK - 3 BHK", "1550 Sq.Ft. To 1682 Sq.Ft (Carpet)"));

        adapter = new Adapters(getContext(), itemList);
        adapter.setOnItemClickListener(item -> {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("siteItem", (Parcelable) item);
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        // Floating Action Button
        floatingActionButton = view.findViewById(R.id.floating_button);
        floatingActionButton.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), Chat_1.class);
            startActivity(intent);
        });

        return view;
    }

    private void showImagePopup(int imageResId) {
        android.app.Dialog dialog = new android.app.Dialog(requireContext());
        dialog.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.image_popup);

        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
        }

        ImageView imageView = dialog.findViewById(R.id.fullscreen_image);
        imageView.setImageResource(imageResId);
        imageView.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }
}
