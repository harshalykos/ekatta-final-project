package com.example.project1;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.List;

public class Photos extends Fragment {
    private ImageSlider imageSliderBuilding;
    private List<SlideModel> slideModelsbuilding;

    public Photos() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photos, container, false);

        imageSliderBuilding = view.findViewById(R.id.image_slider_building);
        slideModelsbuilding = new ArrayList<>();
        slideModelsbuilding.add(new SlideModel(R.drawable.main_buil, ScaleTypes.FIT));
        slideModelsbuilding.add(new SlideModel(R.drawable.i2, ScaleTypes.FIT));
        slideModelsbuilding.add(new SlideModel(R.drawable.i3, ScaleTypes.FIT));
        imageSliderBuilding.setImageList(slideModelsbuilding);
        return view;
    }
}