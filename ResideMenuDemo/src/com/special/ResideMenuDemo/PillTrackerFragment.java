package com.special.ResideMenuDemo;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

/**
 * Mail: hsdars@gmail.com
 */
public class PillTrackerFragment extends Fragment  {
    Calendar calendar;
    Intent myIntent;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    TimePicker timePillTracker;
    View rootView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(R.layout.pilltracker, container, false);
        myIntent = new Intent(getActivity() , NotifyService.class);
        alarmManager = (AlarmManager)getActivity().getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        pendingIntent = PendingIntent.getService(getActivity(), 0, myIntent, 0);
        timePillTracker= (TimePicker) rootView.findViewById(R.id.timePillTracker);
        timePillTracker.setIs24HourView(true);
        TextView tvPillTracker= (TextView) rootView.findViewById(R.id.tvPillTracker);
        EditText etPillTrackerValue= (EditText) rootView.findViewById(R.id.etPillTrackerValue);
        Button bnCreateAlarm= (Button) rootView.findViewById(R.id.bnCreateAlarm);
        bnCreateAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour=timePillTracker.getCurrentHour();
                int minute= timePillTracker.getCurrentMinute();
                calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent);
                Toast.makeText(getActivity(),"Alarm Set",Toast.LENGTH_SHORT).show();
            }
        });
        return rootView;
    }


}
