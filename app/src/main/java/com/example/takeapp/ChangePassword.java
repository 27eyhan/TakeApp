package com.example.takeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ChangePassword extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_password);

        EditText newPass = findViewById(R.id.newPass);
        EditText confirmNewPass = findViewById(R.id.ConfirmnewPass);
        Button changePassButton = findViewById(R.id.changePass);

        changePassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String NewPassword = newPass.getText().toString().trim();
                String confirmNewPassword = confirmNewPass.getText().toString().trim();
                if(NewPassword.isEmpty() || confirmNewPassword.isEmpty()) {
                    Toast.makeText(ChangePassword.this, "Please Enter Your New Password", Toast.LENGTH_SHORT).show();
                }else if(confirmNewPassword.equals(NewPassword)){
                    Toast.makeText(ChangePassword.this,"Your Password Successfully Change!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(ChangePassword.this, LoginActivity.class);
                    startActivity(intent);
                }else if(confirmNewPassword != NewPassword){
                    Toast.makeText(ChangePassword.this,"Your Password do not match!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ChangePassword.this,"Error: Please Try Again!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
