package com.biryanistudio.udacityapi.Alarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.biryanistudio.udacityapi.R;
import com.biryanistudio.udacityapi.UI.MainActivity;

/**
 * Created by Sravan on 17-Jul-16.
 */
public class AlarmReceiver extends BroadcastReceiver {
    private final String TAG = getClass().getSimpleName();

    @Override
    public void onReceive(final Context context, final Intent intent) {
        Log.i(TAG, "onReceive");
        displayNotification(context);
    }

    private void displayNotification(final Context context) {
        final PendingIntent pendingIntent = PendingIntent.
                getActivity(context, 953, new Intent(context, MainActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        final Notification.Builder notificationBuilder = new Notification.Builder(context)
                .setContentTitle(context.getString(R.string.notif_title))
                .setContentText(context.getString(R.string.notif_text))
                .setStyle(new Notification.BigTextStyle().bigText(context.getString(R.string.notif_text_big)))
                .setSmallIcon(R.drawable.ic_notification)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);
        final NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(953, notificationBuilder.build());
    }
}
