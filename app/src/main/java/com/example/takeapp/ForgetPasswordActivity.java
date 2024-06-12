package com.example.takeapp;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;


public class ForgetPasswordActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private TextView codeReqMsg;
    private Button resendcode;
    private String codeGen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        dbHelper = new DatabaseHelper(this);

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
                Cursor cursor = dbHelper.getUser(email);
                generateAndNotifCOde();
                /*if (cursor != null && cursor.moveToFirst()) {
                    codeMsg.setVisibility(View.VISIBLE);
                    codeInput.setVisibility(View.VISIBLE);
                    codeReqMsg.setVisibility(View.VISIBLE);
                    resendcode.setVisibility(View.VISIBLE);
                    confirm.setVisibility(View.VISIBLE);
                    resendcode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            CodeTimer();
                            generateAndNotifCOde();
                        }
                    });
                }*/
                if (email.equals("android1@gmail.com")) {
                    codeMsg.setVisibility(View.VISIBLE);
                    codeInput.setVisibility(View.VISIBLE);
                    codeReqMsg.setVisibility(View.VISIBLE);
                    resendcode.setVisibility(View.VISIBLE);
                    confirm.setVisibility(View.VISIBLE);
                    resendcode.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            generateAndNotifCOde();
                            CodeTimer();
                        }
                    });
                }
                else if (email.isEmpty()) {
                    Toast.makeText(ForgetPasswordActivity.this, "Please Enter Your Email", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(ForgetPasswordActivity.this, "User Not Found", Toast.LENGTH_SHORT).show();
                }
                confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String code = codeInput.getText().toString().trim();
                        if (code.equals(codeGen)) {
                            Intent intent = new Intent(ForgetPasswordActivity.this, ChangePassword.class);
                            intent.putExtra("EMAIL", email);
                            startActivity(intent);
                        } else if (code.isEmpty()) {
                            Toast.makeText(ForgetPasswordActivity.this, "Please Enter the Code", Toast.LENGTH_SHORT).show();
                        } else {
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
    private void generateAndNotifCOde(){
        codeGen = CodeGenerator();
        notifCode(codeGen);
    }
    private void notifCode(String code){
        Toast.makeText(this, "Code: " + code, Toast.LENGTH_LONG).show();
    }
    private String CodeGenerator(){
        Random rand = new Random();
        int code = 100000 + rand.nextInt(600000);
        return String.valueOf(code);
    }
}
