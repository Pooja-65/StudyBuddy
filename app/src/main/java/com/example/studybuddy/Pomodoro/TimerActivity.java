package com.example.studybuddy.Pomodoro;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.studybuddy.R;

public class TimerActivity extends AppCompatActivity {

    private EditText timerEditText;
    private TextView timerTextView;
    private Button startButton, stopButton;
    private CountDownTimer countDownTimer;
    private boolean timerRunning;
    private long timeLeftInMillis = 1500000; // 25 minutes initially

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timerEditText = findViewById(R.id.timerEditText);
        timerTextView = findViewById(R.id.timerTextView);
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);

        timerEditText.setText(String.valueOf(timeLeftInMillis / 60000));

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTimer();
            }
        });
    }

    private void startTimer() {
        if (!timerRunning) {
            long inputMinutes = Long.parseLong(timerEditText.getText().toString());
            timeLeftInMillis = inputMinutes * 60000;

            countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    timeLeftInMillis = millisUntilFinished;
                    updateTimer();
                }

                @Override
                public void onFinish() {
                    timerRunning = false;
                    updateTimer();
                }
            }.start();

            timerRunning = true;
            startButton.setEnabled(false);
            stopButton.setEnabled(true);
            timerEditText.setEnabled(false);
        }
    }

    private void stopTimer() {
        if (timerRunning) {
            countDownTimer.cancel();
            timerRunning = false;
            startButton.setEnabled(true);
            stopButton.setEnabled(false);
            timerEditText.setEnabled(true);
        }
    }

    private void updateTimer() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format("%02d:%02d", minutes, seconds);
        timerTextView.setText(timeLeftFormatted);
    }
}
