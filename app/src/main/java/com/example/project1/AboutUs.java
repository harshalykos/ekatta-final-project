package com.example.project1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

public class AboutUs extends Fragment {

    AppCompatButton visitSiteBtn, callUsBtn, emailUsBtn, locBtn;
    TextView descriptionTextView, seeMoreTextView;
    boolean isExpanded = false;

    public AboutUs() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate layout
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);

        // Initialize Buttons
        visitSiteBtn = view.findViewById(R.id.visitsite);
        callUsBtn = view.findViewById(R.id.callus);
        emailUsBtn = view.findViewById(R.id.emailus);
        locBtn = view.findViewById(R.id.loc);

        // Initialize Description TextViews
        descriptionTextView = view.findViewById(R.id.description);
        seeMoreTextView = view.findViewById(R.id.see_more);

        // Website Button
        visitSiteBtn.setOnClickListener(v -> {
            String url = "https://ekatta.in/";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);
        });

        // Call Button
        callUsBtn.setOnClickListener(v -> {
            String phoneNumber = "9409294950";
            Intent intent = new Intent(Intent.ACTION_DIAL);
            intent.setData(Uri.parse("tel:" + phoneNumber));
            startActivity(intent);
        });

        // Email Button
        emailUsBtn.setOnClickListener(v -> {
            String email = "Hrd.ekatta@gmail.com.  ";
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:" + email));
            intent.putExtra(Intent.EXTRA_SUBJECT, "Inquiry from App");
            intent.putExtra(Intent.EXTRA_TEXT, "Hello, I would like to know more about...");
            startActivity(intent);
        });

        // Location Button
        locBtn.setOnClickListener(v -> {
            String geoUri = "https://www.google.com/maps/place/Ekatta+Innovators+LLP/data=!4m2!3m1!1s0x0:0x54c0933f689ece29?sa=X&ved=1t:2428&ictx=111";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
            intent.setPackage("com.google.android.apps.maps");
            startActivity(intent);
        });

        // See More / See Less logic
        seeMoreTextView.setOnClickListener(v -> {
            if (isExpanded) {
                // Collapse
                descriptionTextView.setMaxLines(3);
                descriptionTextView.setEllipsize(TextUtils.TruncateAt.END);
                seeMoreTextView.setText("See More");
                isExpanded = false;
            } else {
                // Expand
                descriptionTextView.setMaxLines(Integer.MAX_VALUE);
                descriptionTextView.setEllipsize(null);
                seeMoreTextView.setText("See Less");
                isExpanded = true;
            }
        });


        return view;
    }
}
