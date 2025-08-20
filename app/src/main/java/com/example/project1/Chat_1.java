package com.example.project1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chat1);

        // Initialize CardViews (must exist in activity_chat1.xml)
        col1 = findViewById(R.id.col_1);
        col2 = findViewById(R.id.col_2);

        // Load initial fragment
        loadFragment(new Chat_A());

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


        floatingActionButtonC=findViewById(R.id.floating_button_chat);
        floatingActionButtonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat_1.this, ChatUser.class);
                startActivity(intent);
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.chat_fragment, fragment)
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
