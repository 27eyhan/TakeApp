package com.example.takeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class AccInfoActivity extends AppCompatActivity {

    private TextView fnameTextView, lnameTextView, emailTextView, userIDTextView, AccDateCreatedTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_acc_info);

        fnameTextView = findViewById(R.id.fnameEditText);
        lnameTextView = findViewById(R.id.lnameEditText);
        emailTextView = findViewById(R.id.EmailEditText);
        userIDTextView = findViewById(R.id.habitID);
        AccDateCreatedTextView = findViewById(R.id.AccDateText);

        ImageView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AccInfoActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        // Assuming you have the user's email stored in a variable, get it from intent extras
        String userEmail = getIntent().getStringExtra("user_email");

        // Fetch user info from database based on email
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        /*User user = dbHelper.getUserInfoByEmail(userEmail);

        if (user != null) {
            fnameTextView.setText(user.getFirstName());
            lnameTextView.setText(user.getLastName());
            emailTextView.setText(user.getEmail());
            userID.setText(user.getUserID());
            AccDateCreatedTextView.setText(user.CreationDate));
        }*/
    }
}
