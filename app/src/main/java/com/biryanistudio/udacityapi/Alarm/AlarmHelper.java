package com.biryanistudio.udacityapi.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * Created by Sravan on 17-Jul-16.
 */
public class AlarmHelper {
    final private String TAG = getClass().getSimpleName();
    private Context mContext;

    public AlarmHelper(final Context context) {
        mContext = context;
    }

    public void setMonthlyAlarms() {
        final long triggerInMillis = getFirstTrigger();
        final long intervalInMillis = getMonthInMillis();
        final AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        final Intent intent = new Intent(mContext, AlarmReceiver.class);
        intent.setAction("com.biryanistudio.udacityapi.SET_ALARM");
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 953,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC, triggerInMillis, intervalInMillis, pendingIntent);
        Log.i(TAG, String.format("Alarm set, triggerInMillis: %d, intervalInMillis: %d", triggerInMillis, intervalInMillis));
    }

    private long getFirstTrigger() {
        final Calendar calendar = Calendar.getInstance();
        final int currentMonth = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, currentMonth + 1);
        return calendar.getTimeInMillis();
    }

    private long getMonthInMillis() {
        final Calendar calendar = Calendar.getInstance();
        final int numOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return numOfDays * 24 * 60 * 60 * 1000;
    }
}
