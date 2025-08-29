package com.example.project1;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class SearchPopupActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_popup); // Make sure this layout exists

        // Optional: receive the query string
        String query = getIntent().getStringExtra("query");
        if (query != null) {
            // Handle the search query if needed
        }
    }
}
