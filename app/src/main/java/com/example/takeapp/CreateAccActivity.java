package com.example.takeapp;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKeys;

import java.io.IOException;
import java.security.GeneralSecurityException;

public class CreateAccActivity extends AppCompatActivity {

    private static final int REQUEST_CODE_STORAGE_PERMISSION = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;

    private DatabaseHelper dbHelper;
    private ImageView imageViewProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);


        dbHelper = new DatabaseHelper(this);

        EditText firstNameEditText = findViewById(R.id.firstName);
        EditText lastNameEditText = findViewById(R.id.lastName);
        EditText emailEditText = findViewById(R.id.Email);
        EditText passwordEditText = findViewById(R.id.password);
        EditText confirmPasswordEditText = findViewById(R.id.ConfirmPassword);
        Button createAccountButton = findViewById(R.id.createAccountButton);
        imageViewProfile = findViewById(R.id.Profile);
        Button buttonSelectImage = findViewById(R.id.pickImage);

        buttonSelectImage.setOnClickListener(v -> {
            if (ContextCompat.checkSelfPermission(
                    CreateAccActivity.this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
            ) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        CreateAccActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        REQUEST_CODE_STORAGE_PERMISSION
                );
            } else {
                selectImage();
            }
        });

        createAccountButton.setOnClickListener(v -> {
            String firstname = firstNameEditText.getText().toString().trim();
            String lastname = lastNameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            String confirmPassword = confirmPasswordEditText.getText().toString().trim();

            if (firstname.isEmpty() || lastname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(CreateAccActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirmPassword)) {
                Toast.makeText(CreateAccActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            } else if (dbHelper.checkUserExists(email)) {
                Toast.makeText(CreateAccActivity.this, "Account with this email already exists", Toast.LENGTH_SHORT).show();
            } else {
                String encryptedPassword = encryptPassword(password);
                if (encryptedPassword != null && dbHelper.addUser(firstname, lastname, email, encryptedPassword)) {
                    Toast.makeText(CreateAccActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                    navigateToHomeActivity();
                } else {
                    Toast.makeText(CreateAccActivity.this, "Error creating account", Toast.LENGTH_SHORT).show();
                }
            }
        });
        if (ContextCompat.checkSelfPermission(
                CreateAccActivity.this,
                Manifest.permission.READ_EXTERNAL_STORAGE
        ) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    CreateAccActivity.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_STORAGE_PERMISSION
            );
        }
    }
    private boolean checkUserExists(String email) {
        // Check if the user with the given email already exists in the database
        // Implement this method in your DatabaseHelper class
        return dbHelper.checkUserExists(email);
    }



    private void selectImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE);
        }
    }

    private void checkStoragePermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_STORAGE_PERMISSION);
        } else {
            selectImage();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_STORAGE_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, proceed with selecting image
                selectImage();
            } else {
                // Permission denied, show a message or handle accordingly
                Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_SELECT_IMAGE && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                imageViewProfile.setImageURI(selectedImageUri);
            }
        }
    }

    private String encryptPassword(String password) {
        try {
            String masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC);
            SharedPreferences encryptedPrefs = EncryptedSharedPreferences.create(
                    "secret_shared_prefs",
                    masterKeyAlias,
                    this,
                    EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                    EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            );

            encryptedPrefs.edit().putString("encrypted_password", password).apply();
            return encryptedPrefs.getString("encrypted_password", null);
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void navigateToHomeActivity() {
        Intent intent = new Intent(CreateAccActivity.this, HomeActivity.class);
        startActivity(intent);
        finish(); // Optionally, finish this activity to prevent going back to it using the back button
    }
}
