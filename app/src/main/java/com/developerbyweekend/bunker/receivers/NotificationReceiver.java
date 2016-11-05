package com.developerbyweekend.bunker.receivers;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;

import com.developerbyweekend.bunker.R;
import com.developerbyweekend.bunker.main.MainActivity;

/**
 * Created by sunit on 15/10/16.
 */

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_pikachu)
                        .setContentTitle("ProjectXXX")
                        .setContentText("Open App fro fun :P.")
                        .setVibrate(new long[] { 1000 })
                        .setLights(Color.BLUE, 3000, 3000);
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        mBuilder.setSound(alarmSound);

        Intent resultIntent = new Intent(context, MainActivity.class);

        PendingIntent resultPendingIntent = PendingIntent.getActivity(context,123,resultIntent,0);

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, mBuilder.build());
    }
}
