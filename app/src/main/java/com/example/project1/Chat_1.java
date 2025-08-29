package com.example.project1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class Chat_1 extends AppCompatActivity {

    private CardView col1, col2;
    private CardView selectedCrd = null;
    FloatingActionButton floatingActionButtonC;
    ImageButton bb;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat1);

        // Initialize views
        col1 = findViewById(R.id.col_1);
        col2 = findViewById(R.id.col_2);
        floatingActionButtonC = findViewById(R.id.floating_button_chat);
        bb = findViewById(R.id.back_from_chat);

        // Load initial fragment
        loadFragment(new Chat_A());

        // Set click listeners for CardViews
        col1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCard(col1);
                loadFragment(new Chat_A());
            }
        });

        col2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectCard(col2);
                loadFragment(new Chat_B());
            }
        });

        // Set click listener for FloatingActionButton
        floatingActionButtonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat_1.this, DashScreen.class);
                intent.putExtra("fragment_to_load", "ChatUser1");
                startActivity(intent);
            }
        });

        bb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat_1.this, DashScreen.class);
                startActivity(intent);
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.chat_fragment, fragment)
                .addToBackStack(null) // optional, allows user to go back
                .commit();
    }

    private void selectCard(CardView card) {
        int defaultColor = ContextCompat.getColor(this, R.color.white);
        int selectedColor = ContextCompat.getColor(this, R.color.blue);

        if (selectedCrd != null && selectedCrd != card) {
            selectedCrd.setCardBackgroundColor(defaultColor);
        }

        card.setCardBackgroundColor(selectedColor);
        selectedCrd = card;
    }
}
