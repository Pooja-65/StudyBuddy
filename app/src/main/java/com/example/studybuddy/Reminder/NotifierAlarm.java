package com.example.studybuddy.Reminder;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import androidx.core.app.NotificationCompat;

import com.example.studybuddy.R;

public class NotifierAlarm extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        AppDatabase appDatabase = AppDatabase.geAppdatabase(context.getApplicationContext());
        RoomDAO roomDAO = appDatabase.getRoomDAO();
        Reminders reminder;

        reminder = roomDAO.getObjectUsingID(intent.getIntExtra("id", 0));
        roomDAO.Delete(reminder);
        AppDatabase.destroyInstance();

        // Replace this line with the path to your custom sound file
        Uri customSoundUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.reminder_alarm);

        Intent intent1 = new Intent(context, MainPage.class);
        intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context);
        taskStackBuilder.addParentStack(MainPage.class);
        taskStackBuilder.addNextIntent(intent1);

        PendingIntent intent2;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            intent2 = taskStackBuilder.getPendingIntent(1, PendingIntent.FLAG_MUTABLE);
        } else {
            intent2 = taskStackBuilder.getPendingIntent(1, PendingIntent.FLAG_UPDATE_CURRENT);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "891");
        builder.setSound(customSoundUri);
        NotificationChannel channel = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            channel = new NotificationChannel("891", "hello1", NotificationManager.IMPORTANCE_HIGH);
        }

        // Set the volume to 1.0 for maximum volume


        Notification notification = builder.setContentTitle("Reminder")
                .setContentText(intent.getStringExtra("Message"))
                .setAutoCancel(true)
                .setSound(customSoundUri)
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setContentIntent(intent2)
                .setChannelId("my_channel_02")
                .build();


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(1, notification);

        // Vibrate pattern (you can customize this pattern)
        long[] vibrationPattern = {0, 500, 100, 600, 100, 800};

        // Get the default vibration pattern
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        if (vibrator != null && vibrator.hasVibrator()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                // For Android O and above
                VibrationEffect vibrationEffect = VibrationEffect.createWaveform(vibrationPattern, -1);
                vibrator.vibrate(vibrationEffect);
            } else {
                // For Android Nougat and below
                vibrator.vibrate(vibrationPattern, -1);
            }
        }
    }
}