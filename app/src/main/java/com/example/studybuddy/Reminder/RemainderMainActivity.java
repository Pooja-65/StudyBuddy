package com.example.studybuddy.Reminder;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.studybuddy.R;


public class RemainderMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remainder_main);
        Button button1 = findViewById(R.id.assignment);
        Button button2 = findViewById(R.id.session);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action for Button 1 click
                showToast("Assignment Reminder Clicked");
                // Add code to start MainPage activity
                startActivity(new Intent(RemainderMainActivity.this, MainPage1.class));

            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Action for Button 2 click
                showToast("Session Reminder Clicked");
                // Add code to start MainPage activity
                startActivity(new Intent(RemainderMainActivity.this, MainPage.class));

            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}