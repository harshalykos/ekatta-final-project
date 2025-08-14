package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

public class DashScreen extends AppCompatActivity {

    DrawerLayout drawerLayout;
    ImageButton imageButton;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dash_screen);

        drawerLayout = findViewById(R.id.drawer_layout);
        imageButton = findViewById(R.id.buttonexit); // Main layout button to open drawer
        navigationView = findViewById(R.id.navigation);

        Toolbar toolbar = findViewById(R.id.toolbarnoty);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {   // it is to invisible the project name
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        loadFragment(new Home(), 0);

        // Open drawer when main ImageButton is clicked
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if (itemId == R.id.home) {
                    loadFragment(new Home(), 0);
                } else if (itemId == R.id.profile) {
                    startActivity(new Intent(DashScreen.this, Userprofile.class));
                } else if (itemId == R.id.society) {
                    loadFragment(new Society(), 1);
                } else if (itemId == R.id.about) {
                    loadFragment(new AboutUs(), 1);
                } else if (itemId == R.id.funds) {
                    loadFragment(new Funds(), 1);
                } else if (itemId == R.id.exit) {
                    startActivity(new Intent(DashScreen.this, MainActivity.class));
                }
                drawerLayout.closeDrawer(GravityCompat.START); // Close drawer after selection
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.notify) {
            startActivity(new Intent(DashScreen.this, Notification.class));
        } else {
            Toast.makeText(this, "No Action Performed", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    private void loadFragment(Fragment fragment, int flag) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (flag == 0) {
            ft.add(R.id.container, new Home());
        } else {
            ft.replace(R.id.container, fragment);
        }
        ft.commit();
    }
}
