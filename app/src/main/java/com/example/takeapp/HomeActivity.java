package com.example.takeapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    private TextView[] textViewArray;
    private TextView[] textView_1Array;

    private HorizontalScrollView scrollV, scrollV2;
    private SharedPreferences preferences; // Declare SharedPreferences object here
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        preferences = getSharedPreferences("MyPreferences", MODE_PRIVATE);
        int selectedDate = preferences.getInt("selectedDate", -1);


        // Get the username from Intent extras
        String username = getIntent().getStringExtra("username");
        String selectedDateStr = getIntent().getStringExtra("selectedDate");

        // Parse selected date as integer
        try {
            selectedDate = Integer.parseInt(selectedDateStr);
        } catch (NumberFormatException e) {
            // Handle parsing error
            e.printStackTrace();
        }

        int selectedTime = getIntent().getIntExtra("selectedTime", -1);
        scrollV = findViewById(R.id.scrollView1);
        scrollV2 = findViewById(R.id.scrollView1_1);
        textViewArray = new TextView[]{
                findViewById(R.id.textView3),
                findViewById(R.id.textView4),
                findViewById(R.id.textView5),
                findViewById(R.id.textView6),
                findViewById(R.id.textView7),
                findViewById(R.id.textView8),
                findViewById(R.id.textView9),
                findViewById(R.id.textView10),
                findViewById(R.id.textView11)
        };

        textView_1Array = new TextView[]{
                findViewById(R.id.textView3_1),
                findViewById(R.id.textView4_1),
                findViewById(R.id.textView5_1),
                findViewById(R.id.textView6_1),
                findViewById(R.id.textView7_1),
                findViewById(R.id.textView8_1),
                findViewById(R.id.textView9_1),
                findViewById(R.id.textView10_1),
                findViewById(R.id.textView11_1)
        };

        if (selectedDate >= 1 && selectedDate <= 9) {
            // Calculate index based on selectedDate (subtract 1 for array index)
            int index = selectedDate - 1;

            // Make the corresponding TextViews visible and others invisible
            for (int i = 0; i < textViewArray.length; i++) {
                if (i == index) {
                    textViewArray[i].setVisibility(View.INVISIBLE);
                    textView_1Array[i].setVisibility(View.VISIBLE);
                } else {
                    textViewArray[i].setVisibility(View.VISIBLE);
                    textView_1Array[i].setVisibility(View.INVISIBLE);
                }
            }
            // Make scrollV2 visible
            scrollV2.setVisibility(View.VISIBLE);
        } else {
            // Handle invalid selectedDate
            // Make scrollV2 invisible
            scrollV2.setVisibility(View.INVISIBLE);
        }

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
        scrollV.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // Synchronize scrolling of scrollV with scrollV2
                scrollV2.scrollTo(scrollX, scrollY);
            }
        });
        scrollV2.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                // Synchronize scrolling of scrollV with scrollV2
                scrollV.scrollTo(scrollX, scrollY);
            }
        });
    }
}
