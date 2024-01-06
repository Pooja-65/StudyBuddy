package com.example.studybuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;

import com.example.studybuddy.Community.CallDashboard;
import com.example.studybuddy.Pomodoro.TimerActivity;
import com.example.studybuddy.Reminder.RemainderMainActivity;
import com.example.studybuddy.chatgpt.MainActivity;

public class DashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        // Find the CardViews by their IDs
        CardView communityCard = findViewById(R.id.communityCard);
        CardView group_chatCard = findViewById(R.id.group_chatCard);
        CardView scheduleCard = findViewById(R.id.scheduleCard);
        CardView session_codeCard = findViewById(R.id.session_codeCard);
        CardView pomodoroCard = findViewById(R.id.pomodoroCard);
        CardView chatgptCard = findViewById(R.id.chatgptCard);


        // Set click listeners for each CardView
        communityCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for Community CardView
                openActivity(CallDashboard.class);
                vibrate();
            }
        });

        group_chatCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for Medicine Reminder CardView
//                      openActivity(MainPage.class);
//                vibrate();
            }
        });

        scheduleCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for Emergency SOS CardView
               openActivity(RemainderMainActivity.class);
                vibrate();
            }
        });

        session_codeCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for Magnifying Glass CardView
//                openActivity(.class);
                vibrate();
            }
        });
        pomodoroCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for Magnifying Glass CardView
                openActivity(TimerActivity.class);
                vibrate();
            }
        });
        chatgptCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle click for Magnifying Glass CardView
                            openActivity(MainActivity.class);
                vibrate();
            }
        });


    }

    private void openActivity(Class<?> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }

    private void vibrate() {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        // Check if the device has a vibrator
        if (vibrator != null) {
            // Vibrate for 500 milliseconds (adjust duration as needed)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
            } else {
                // Deprecated in API 26
                vibrator.vibrate(500);
            }
        }
    }
}
