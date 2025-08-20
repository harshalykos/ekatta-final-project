package com.example.project1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.tabs.TabLayout;

public class Notification extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_notification);

        @SuppressLint({"MissingInflatedId", "LocalSuppress"})
        ImageButton imageButton = findViewById(R.id.back_from_notification);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Notification.this, DashScreen.class);
                startActivity(intent);
            }
        });

        TabLayout tabLayout = findViewById(R.id.tablyt);
        TextView text = new TextView(Notification.this);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.Notification_container, new General_notifiction())
                .commit();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                switch(position){
                    case 0:
                        loadLayout(new General_notifiction());
                        break;
                    case 1:
                        loadLayout(new Society_notification());
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                TabLayout.Tab tab1 = tabLayout.getTabAt(0);
                text.setTextColor(Color.BLACK);
                text.setText("General");
                text.setGravity(Gravity.CENTER);
                tab1.setCustomView(text);
            }
        });
    }

        private void loadLayout(Fragment fragment){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.Notification_container, fragment)
                    .commit();
        }
}