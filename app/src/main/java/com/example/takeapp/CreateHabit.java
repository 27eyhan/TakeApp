package com.example.takeapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class CreateHabit extends AppCompatActivity {
    private ImageButton bike, game, read, sleep, watch, food;
    private TextView cycling, eating, sleeping, reading, watching, gaming;

    private Bitmap selectedImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_habit);

        bike = findViewById(R.id.bikebtn);
        game = findViewById(R.id.gamingbtn);
        read = findViewById(R.id.readbtn);
        sleep = findViewById(R.id.sleepbtn);
        watch = findViewById(R.id.watchbtn);
        food = findViewById(R.id.foodbtn);

        cycling = findViewById(R.id.cyclingtextview);
        eating = findViewById(R.id.eatingtextview);
        sleeping = findViewById(R.id.sleepingtextview);
        reading = findViewById(R.id.readingtextview);
        watching = findViewById(R.id.watchingtextview);
        gaming = findViewById(R.id.gamingtextview);

        ImageView homeView = findViewById(R.id.home);
        homeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateHabit.this, HomeActivity.class);
                startActivity(intent);
            }
        });

        ImageView profileView = findViewById(R.id.profile);
        profileView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateHabit.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        bike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the image from the ImageButton
                Drawable drawable = bike.getDrawable();
                if (drawable instanceof BitmapDrawable) {
                    selectedImage = ((BitmapDrawable) drawable).getBitmap();
                    String cyclingText = cycling.getText().toString();
                    Intent intent = new Intent(CreateHabit.this, Schedule.class);
                    intent.putExtra("image", selectedImage);
                    intent.putExtra("text", cyclingText);
                    startActivity(intent);
                }
            }
        });
        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the image from the ImageButton
                Drawable drawable = game.getDrawable();
                if (drawable instanceof BitmapDrawable) {
                    selectedImage = ((BitmapDrawable) drawable).getBitmap();
                    String gamingText = gaming.getText().toString();
                    Intent intent = new Intent(CreateHabit.this, Schedule.class);
                    intent.putExtra("image", selectedImage);
                    intent.putExtra("text", gamingText);
                    startActivity(intent);
                }
            }
        });
        read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the image from the ImageButton
                Drawable drawable = read.getDrawable();
                if (drawable instanceof BitmapDrawable) {
                    selectedImage = ((BitmapDrawable) drawable).getBitmap();
                    String readingText = reading.getText().toString();
                    Intent intent = new Intent(CreateHabit.this, Schedule.class);
                    intent.putExtra("image", selectedImage);
                    intent.putExtra("text", readingText);
                    startActivity(intent);
                }
            }
        });
        sleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the image from the ImageButton
                Drawable drawable = sleep.getDrawable();
                if (drawable instanceof BitmapDrawable) {
                    selectedImage = ((BitmapDrawable) drawable).getBitmap();
                    String sleepingText = sleeping.getText().toString();
                    Intent intent = new Intent(CreateHabit.this, Schedule.class);
                    intent.putExtra("image", selectedImage);
                    intent.putExtra("text", sleepingText);
                    startActivity(intent);
                }
            }
        });
        watch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the image from the ImageButton
                Drawable drawable = watch.getDrawable();
                if (drawable instanceof BitmapDrawable) {
                    selectedImage = ((BitmapDrawable) drawable).getBitmap();
                    String watchingText = watching.getText().toString();
                    Intent intent = new Intent(CreateHabit.this, Schedule.class);
                    intent.putExtra("image", selectedImage);
                    intent.putExtra("text", watchingText);
                    startActivity(intent);
                }
            }
        });
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retrieve the image from the ImageButton
                Drawable drawable = food.getDrawable();
                if (drawable instanceof BitmapDrawable) {
                    selectedImage = ((BitmapDrawable) drawable).getBitmap();
                    String eatingText = eating.getText().toString();
                    Intent intent = new Intent(CreateHabit.this, Schedule.class);
                    intent.putExtra("image", selectedImage);
                    intent.putExtra("text", eatingText);
                    startActivity(intent);
                }
            }
        });
    }
}
