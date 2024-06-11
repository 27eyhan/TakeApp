package com.example.takeapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ForgetPasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget_password);
        Button SendCode = findViewById(R.id.sendCode);

        TextView codeMsg = findViewById(R.id.codeSendMessage);

        EditText codeInput = findViewById(R.id.codeInputText);

        TextView codeReqMsg = findViewById(R.id.codeReqMessage);

        Button resendcode = findViewById(R.id.resendCode);

        Button confirm = findViewById(R.id.confirm);

        codeMsg.setVisibility(View.INVISIBLE);
        codeInput.setVisibility(View.INVISIBLE);
        codeReqMsg.setVisibility(View.INVISIBLE);
        resendcode.setVisibility(View.INVISIBLE);
        confirm.setVisibility(View.INVISIBLE);

        ImageView Back = findViewById(R.id.back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}