package com.ndm.da_test.FCM;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.Log;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.ndm.da_test.Activity.MainActivity;
import com.ndm.da_test.Activity.MyApplication;
import com.ndm.da_test.R;

import java.util.Map;

public class MessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage message) {
        super.onMessageReceived(message);

        Map<String, String> data = message.getData();
        if (data == null) {
            return;
        }
        String title = data.get("title");
        String body = data.get("body");
        sendNotification(title, body);
    }

    private void sendNotification(String title, String body) {


        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        // Kiểm tra xem máy có đang tắt không
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        if (powerManager != null && !powerManager.isInteractive()) {
            // Nếu máy đang tắt, gửi một Intent để bật màn hình
            Intent wakeUpIntent = new Intent(this, MainActivity.class);
            wakeUpIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(wakeUpIntent);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, MyApplication.CHANNEL_ID)
                .setContentTitle(title)
                .setContentText(body)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.notify(1, notificationBuilder.build());
        }
    }


    @Override
    public void onNewToken(@NonNull String token) {
        Log.d("New Token", token);
        super.onNewToken(token);
    }
}

