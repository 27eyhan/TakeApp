package com.example.takeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);

        // Get the username from Intent extras
        String username = getIntent().getStringExtra("username");

        // Find the TextView and set the username
        TextView helloUserTextView = findViewById(R.id.textViewUser);
        helloUserTextView.setText("Hello, " + username);

        ImageView addHabit = findViewById(R.id.add);
        addHabit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, CreateHabit.class);
                startActivity(intent);
            }
        });
        ImageView homeView = findViewById(R.id.home);
        homeView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        ImageView profileView = findViewById(R.id.profile);
        profileView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
