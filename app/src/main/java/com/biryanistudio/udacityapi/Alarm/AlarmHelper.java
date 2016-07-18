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

    public AlarmHelper(Context context) {
        mContext = context;
    }

    public void setMonthlyAlarms() {
        long triggerInMillis = getFirstTrigger();
        long intervalInMillis = getMonthInMillis();
        AlarmManager alarmManager = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(mContext, AlarmReceiver.class);
        intent.setAction("com.biryanistudio.udacityapi.SET_ALARM");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 953,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setRepeating(AlarmManager.RTC, triggerInMillis, intervalInMillis, pendingIntent);
        Log.i(TAG, String.format("Alarm set, triggerInMillis: %d, intervalInMillis: %d", triggerInMillis, intervalInMillis));
    }

    private long getFirstTrigger() {
        Calendar calendar = Calendar.getInstance();
        int currentMonth = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.MONTH, currentMonth + 1);
        return calendar.getTimeInMillis();
    }

    private long getMonthInMillis() {
        Calendar calendar = Calendar.getInstance();
        int numOfDays = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        return numOfDays * 24 * 60 * 60 * 1000;
    }
}
