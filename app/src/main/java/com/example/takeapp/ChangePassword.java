package com.example.takeapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChangePassword extends AppCompatActivity {

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_password);

        EditText newPass = findViewById(R.id.newPass);
        EditText confirmNewPass = findViewById(R.id.ConfirmnewPass);
        Button changePassButton = findViewById(R.id.changePassBtn);

        dbHelper = new DatabaseHelper(this);


        changePassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String NewPassword = newPass.getText().toString().trim();
                String confirmNewPassword = confirmNewPass.getText().toString().trim();
                String email = getIntent().getStringExtra("email");

                TextView viewEmail = findViewById(R.id.showUserEmail);
                viewEmail.setText(email);

                if(NewPassword.isEmpty() || confirmNewPassword.isEmpty()) {
                    Toast.makeText(ChangePassword.this, "Please Enter Your New Password", Toast.LENGTH_SHORT).show();
                }else if(!confirmNewPassword.equals(NewPassword)){
                    Toast.makeText(ChangePassword.this,"Your Password do not match!", Toast.LENGTH_SHORT).show();
                }else{
                    dbHelper.updatePassword(email, NewPassword);
                    Toast.makeText(ChangePassword.this,"Successfully Change Your Password", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangePassword.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
