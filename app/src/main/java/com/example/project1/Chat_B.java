package com.example.project1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.io.IOException;

public class Chat_B extends Fragment {

    Button btnPlumbing, btnElectricity, btnWaterproofing, btnPaint, btnOther;
    ImageView uploadIcon;
    TextView uploadText;

    private static final int CAMERA_PERMISSION = 200;

    // Launchers for Camera and Gallery
    private ActivityResultLauncher<Intent> cameraLauncher;
    private ActivityResultLauncher<Intent> galleryLauncher;

    public Chat_B() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat__b, container, false);

        // Initialize Views
        btnPlumbing = view.findViewById(R.id.btnPlumbing);
        btnElectricity = view.findViewById(R.id.btnElectricity);
        btnWaterproofing = view.findViewById(R.id.btnWaterproofing);
        btnPaint = view.findViewById(R.id.btnPaint);
        btnOther = view.findViewById(R.id.btnOther);

        uploadIcon = view.findViewById(R.id.uploadIcon);
        uploadText = view.findViewById(R.id.uploadText);

        // Setup Activity Result Launchers
        cameraLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                        Bundle extras = result.getData().getExtras();
                        if (extras != null) {
                            Bitmap photo = (Bitmap) extras.get("data");
                            uploadIcon.setImageBitmap(photo);
                        }
                    }
                });

        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                        Uri selectedImage = result.getData().getData();
                        try {
                            Bitmap bitmap = MediaStore.Images.Media.getBitmap(requireActivity().getContentResolver(), selectedImage);
                            uploadIcon.setImageBitmap(bitmap);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });

        // Category Button Clicks
        View.OnClickListener buttonClickListener = v -> {
            resetButtonColors();
            Button clicked = (Button) v;
            clicked.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), android.R.color.holo_blue_dark));
            clicked.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white));
        };

        btnPlumbing.setOnClickListener(buttonClickListener);
        btnElectricity.setOnClickListener(buttonClickListener);
        btnWaterproofing.setOnClickListener(buttonClickListener);
        btnPaint.setOnClickListener(buttonClickListener);
        btnOther.setOnClickListener(buttonClickListener);

        // Camera Click
        uploadIcon.setOnClickListener(v -> openCamera());

        // Gallery Click
        uploadText.setOnClickListener(v -> openGallery());

        return view;
    }

    // Reset all button colors to default light blue
    private void resetButtonColors() {
        int textColor = ContextCompat.getColor(requireContext(), R.color.black);

        Button[] buttons = {btnPlumbing, btnElectricity, btnWaterproofing, btnPaint, btnOther};
        for (Button b : buttons) {
            b.setBackgroundTintList(ContextCompat.getColorStateList(requireContext(), R.color.blue));
            b.setTextColor(textColor);
        }
    }

    // Open Camera
    private void openCamera() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION);
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            cameraLauncher.launch(intent);
        }
    }

    // Open Gallery
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(intent);
    }
}
