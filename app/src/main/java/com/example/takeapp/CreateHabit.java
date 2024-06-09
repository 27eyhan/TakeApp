package com.example.takeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateHabit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit);

        ImageView homeView = findViewById(R.id.home);
        homeView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateHabit.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        ImageView profileView = findViewById(R.id.profile);
        profileView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateHabit.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}
