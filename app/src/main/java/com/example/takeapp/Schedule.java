package com.example.takeapp;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class Schedule extends AppCompatActivity {
    private ImageView habitImageDefault;
    private TextView habitNameDefault, date, time;

    private Button datebutton, timebutton;

    private String previousSelectedDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

        habitImageDefault = findViewById(R.id.habitpic);
        habitNameDefault = findViewById(R.id.habitname);
        date = findViewById(R.id.dateview);
        time = findViewById(R.id.timeview);
        datebutton = findViewById(R.id.datebtn);
        timebutton = findViewById(R.id.timebtn);

        datebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDatePicker(); // Open date picker dialog
            }
        });
        timebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openTimePicker(); //Open time picker dialog
            }
        });
        ImageView addHabit = findViewById(R.id.add);
        addHabit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Schedule.this, CreateHabit.class);
                startActivity(intent);
            }
        });
        ImageView homeView = findViewById(R.id.home);
        homeView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Schedule.this, HomeActivity.class);
                startActivity(intent);
            }
        });
        ImageView profileView = findViewById(R.id.profile);
        profileView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Schedule.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Schedule.this, HomeActivity.class);
                intent.putExtra("selectedDate", date.getText().toString());
                intent.putExtra("selectedTime", time.getText().toString());
                startActivity(intent);
            }
        });

        // Retrieve previously selected date if available
        if (savedInstanceState != null) {
            previousSelectedDate = savedInstanceState.getString("previousSelectedDate");
        }

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            Bitmap image = extras.getParcelable("image");
            String text = extras.getString("text");
            if (image != null) {
                habitImageDefault.setImageBitmap(image);
            }

            if (text != null) {
                habitNameDefault.setText(text);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        // Save the previousSelectedDate in case of activity recreation
        outState.putString("previousSelectedDate", previousSelectedDate);
    }

    private void openDatePicker() {
        // Get current date from calendar
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                // Construct selected date
                String selectedDate = String.valueOf(year) + "." + String.valueOf(month + 1) + "." + String.valueOf(day);

                // Check if the selected date is different from the previous one
                if (!selectedDate.equals(previousSelectedDate)) {
                    // Update the UI and store the new selected date
                    updateUI(selectedDate);
                    previousSelectedDate = selectedDate;
                }
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    private void updateUI(String selectedDate) {
        // Update the date TextView with the selected date
        date.setText(selectedDate);
        // Your other UI update logic can go here
    }

    private void openTimePicker() {
        // Get current time from calendar
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                // Showing the picked value in the textView
                time.setText(String.valueOf(hour) + ":" + String.valueOf(minute));
            }
        }, hour, minute, false);

        timePickerDialog.show();
    }
}