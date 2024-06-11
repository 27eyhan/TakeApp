package com.example.takeapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ForgetPasswordActivity extends AppCompatActivity {

    private static final long COUNTDOWN_TIME = 60000;
    private TextView codeReqMsg;
    private Button resendcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_forget_password);

        EditText emailInput = findViewById(R.id.EmailEditText);

        Button SendCode = findViewById(R.id.sendCode);

        TextView codeMsg = findViewById(R.id.codeSendMessage);

        EditText codeInput = findViewById(R.id.codeInputText);

        codeReqMsg = findViewById(R.id.codeReqMessage);

        resendcode = findViewById(R.id.resendCode);

        Button confirm = findViewById(R.id.confirm);

        ImageView backButton = findViewById(R.id.back);

        codeMsg.setVisibility(View.INVISIBLE);
        codeInput.setVisibility(View.INVISIBLE);
        codeReqMsg.setVisibility(View.INVISIBLE);
        resendcode.setVisibility(View.INVISIBLE);
        confirm.setVisibility(View.INVISIBLE);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgetPasswordActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        SendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = emailInput.getText().toString().trim();

                if(email.equals("android1@gmail.com")){
                    codeMsg.setVisibility(View.VISIBLE);
                    codeInput.setVisibility(View.VISIBLE);
                    codeReqMsg.setVisibility(View.VISIBLE);
                    resendcode.setVisibility(View.VISIBLE);
                    confirm.setVisibility(View.VISIBLE);
                    resendcode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CodeTimer();
                        }
                    });
                }else if(email.isEmpty()){
                    Toast.makeText(ForgetPasswordActivity.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ForgetPasswordActivity.this, "User Not Found", Toast.LENGTH_SHORT).show();
                }
                    confirm.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String code = codeInput.getText().toString().trim();
                            if(code.equals("123")){
                                Intent intent = new Intent(ForgetPasswordActivity.this, ChangePassword.class);
                                startActivity(intent);
                            }
                            else if(code.isEmpty()){
                                Toast.makeText(ForgetPasswordActivity.this, "Please Enter the Code", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(ForgetPasswordActivity.this, "Invalid Code", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            }
        });
    }
    private void CodeTimer() {
        resendcode.setEnabled(false);
        new CountDownTimer(60000, 1000) {
            public void onTick(long millisUntilFinished) {
                codeReqMsg.setText("Resend Code in " + millisUntilFinished / 1000 + "s");
            }
            public void onFinish() {
                codeReqMsg.setText("Resend Code");
                resendcode.setEnabled(true);
            }
        }.start();
    }
}
