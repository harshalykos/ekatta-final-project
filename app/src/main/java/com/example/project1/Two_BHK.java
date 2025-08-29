package com.example.project1;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.google.android.material.tabs.TabLayout;

public class Two_BHK extends Fragment {

    private boolean is3DView = false;


    public Two_BHK() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two__b_h_k, container, false);

        TabLayout tabLayout = view.findViewById(R.id.D_tablyt);

        TextView text = new TextView(requireContext());
        text.setText("Hello Two BHK");

        if (savedInstanceState == null) {
            loadLayout(new Details());
        }

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        loadLayout(new Details());
                        break;
                    case 1:
                        loadLayout(new Photos());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button view3D = view.findViewById(R.id.btnView3D);
        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageView floorImage = view.findViewById(R.id.floorImage);

        floorImage.setImageResource(R.drawable.floor_plan);

        view3D.setOnClickListener(v -> {
            if (is3DView) {
                floorImage.setImageResource(R.drawable.floor_plan);
                view3D.setText("View 3D");
                is3DView = false;
            } else {
                floorImage.setImageResource(R.drawable.floor_plan_3d);
                view3D.setText("View 2D");
                is3DView = true;
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        Button btn = view.findViewById(R.id.ee);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Enquiry_bottom_sheet enquiryBottomSheet = new Enquiry_bottom_sheet();
                enquiryBottomSheet.show(getParentFragmentManager(), "EnquiryBottomSheet");
            }
        });
        ImageView[] images = {
                view.findViewById(R.id.i1),
                view.findViewById(R.id.i2),
                view.findViewById(R.id.i3),
                view.findViewById(R.id.i4),
                view.findViewById(R.id.i5)
        };

        for (ImageView imgView : images) {
            imgView.setOnClickListener(v -> {
                Drawable drawable = imgView.getDrawable();
                showImageDialog(drawable);
            });
        }

        return view;
    }

    private void loadLayout(Fragment fragment) {
        // Correct way inside a Fragment → use getChildFragmentManager()
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.main_container, fragment);
        transaction.commit();
    }
    // 🔥 New method: popup image for slider
    private void showImageDialog(Drawable drawable) {
        Dialog dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        View view = getLayoutInflater().inflate(R.layout.dialog_image, null);
        dialog.setContentView(view);

        ImageView dialogImg = view.findViewById(R.id.dialogImageView);

        dialogImg.setImageDrawable(drawable);
        dialog.show();
    }
}
