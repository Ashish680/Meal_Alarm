package com.ashish.mealalarm;

import android.app.AlarmManager;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;

import java.util.Calendar;

/**
 * Created by Ashish Tiwari on 29,April,2021
 */
public class SetAlarmService extends IntentService {

    public SetAlarmService() {
        super("SetAlarmService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent.getExtras() != null) {
            Bundle bundle = intent.getExtras();
            int day = bundle.getInt(constant.DAY, 0);
            int hour = bundle.getInt(constant.HOUR, 0);
            int min = bundle.getInt(constant.MINUTE, 0);
            String desc = "";
            if (bundle.getString(constant.DESC) != null)
                desc = bundle.getString(constant.DESC);

            setAlarm(day, hour, min, desc);

        }
    }

    private void setAlarm(int day, int hour, int min, String desc) {
        final int fiveMinuteBefore = 5 * 60 * 1000;
        final AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        final Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.DAY_OF_WEEK, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, min);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        // using intent i have class AlarmReceiver class which inherits
        // BroadcastReceiver
        final Intent intent = new Intent(this, AlarmReceiver.class);
        intent.putExtra(constant.DESC, desc);

        // we call broadcast using pendingIntent
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, min, intent, PendingIntent.FLAG_ONE_SHOT);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis() - fiveMinuteBefore, AlarmManager.INTERVAL_DAY * 7, pendingIntent);

    }
}
