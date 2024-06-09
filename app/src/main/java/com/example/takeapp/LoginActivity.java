package com.example.takeapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);

        EditText emailEditText = findViewById(R.id.EmailText);
        EditText passwordEditText = findViewById(R.id.PasswordText);
        Button enterButton = findViewById(R.id.EnterButton);
        Button createAccountButton = findViewById(R.id.CreateButton);

        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailEditText.getText().toString().trim();
                String inputPassword = passwordEditText.getText().toString().trim();

                Cursor cursor = dbHelper.getUser(email);
                if (cursor != null && cursor.moveToFirst()) {
                    int passwordIndex = cursor.getColumnIndex("password");
                    if (passwordIndex != -1) {
                        String storedPassword = cursor.getString(passwordIndex);

                        if (storedPassword.equals(inputPassword)) {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid credentials", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Password column not found", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, CreateAccActivity.class);
                startActivity(intent);
            }
        });
    }
}
