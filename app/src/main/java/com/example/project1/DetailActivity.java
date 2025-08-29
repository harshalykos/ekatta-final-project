package com.example.project1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class DetailActivity extends AppCompatActivity {

    private ImageView previewImage;
    private TextView projectName, address;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail); // ✅ correct layout

        // Views
        previewImage = findViewById(R.id.detail_image);
        projectName = findViewById(R.id.detail_title);
        address = findViewById(R.id.detail_address);

        // Get data from intent
        SitesListItem siteItem = getIntent().getParcelableExtra("siteItem");

        if (siteItem != null) {
            // Load first image from list safely
            if (siteItem.getImageList() != null && !siteItem.getImageList().isEmpty()) {
                previewImage.setImageResource(siteItem.getImageList().get(0));
            }
            projectName.setText(siteItem.getTitle());
            address.setText(siteItem.getAddress());
        }

        // Setup Tabs
        TabLayout tb = findViewById(R.id.tb);

        // Default Fragment
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.House_fragment, new Specification())
                .commit();

        // Handle Tab Selection
        tb.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch (position) {
                    case 0:
                        loadLayout(new Specification());
                        break;
                    case 1:
                        loadLayout(new Ameture());
                        break;
                    case 2:
                        loadLayout(new Floor_plan());
                        break;
                    case 3:
                        loadLayout(new Broucher());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {}

            @Override
            public void onTabReselected(TabLayout.Tab tab) {}
        });
    }

    private void loadLayout(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.House_fragment, fragment)
                .commit();
    }
}
