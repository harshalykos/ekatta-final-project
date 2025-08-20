package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    private DrawerLayout drawerLayout;
    private ImageButton imageButton;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dash_screen);

        drawerLayout = findViewById(R.id.drawer_layout);
        imageButton = findViewById(R.id.buttonexit);
        navigationView = findViewById(R.id.navigation);

        Toolbar toolbar = findViewById(R.id.toolbarnoty);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        }

        // Load default fragment
        loadFragment(new Home(), false);

        // Open drawer on menu button click
        imageButton.setOnClickListener(v -> drawerLayout.openDrawer(GravityCompat.START));

        // Navigation menu item click handling
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            int itemId = menuItem.getItemId();

            if (itemId == R.id.home) {
                loadFragment(new Home(), true);
            } else if (itemId == R.id.profile) { // Make sure R.id.profile matches your XML menu item
                Intent intent = new Intent(DashScreen.this, Userprofile.class);
                startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            } else if (itemId == R.id.society) {
                loadFragment(new Society(), true);
            } else if (itemId == R.id.about) {
                loadFragment(new AboutUs(), true);
            } else if (itemId == R.id.funds) {
                loadFragment(new Funds(), true);
            } else if (itemId == R.id.exit) {
                startActivity(new Intent(DashScreen.this, MainActivity.class));
                finish();
            }else if (itemId == R.id.chatt) {
                startActivity(new Intent(DashScreen.this, ChatUser.class));
                finish();
            }

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
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

    private void loadFragment(Fragment fragment, boolean replace) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (replace) {
            ft.replace(R.id.container, fragment);
        } else {
            ft.add(R.id.container, fragment);
        }
        ft.commit();
    }
}
