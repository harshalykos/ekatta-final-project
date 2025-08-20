package com.example.project1;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class DetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView titleView, addressView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail); // create this layout

        imageView = findViewById(R.id.detail_image);
        titleView = findViewById(R.id.detail_title);
        addressView = findViewById(R.id.detail_address);


        SitesListItem item = getIntent().getParcelableExtra("siteData");

        if (item != null) {
            imageView.setImageResource(item.getImageRes());
            titleView.setText(item.getTitle());
            addressView.setText(item.getAddress());

        }

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        TabLayout tb = findViewById(R.id.tb);
        TextView text = new TextView(DetailActivity.this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.House_fragment, new Specification())
                .commit();

        tb.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch(position){
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
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }
    private void loadLayout(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.House_fragment, fragment)
                .commit();
    }
}
