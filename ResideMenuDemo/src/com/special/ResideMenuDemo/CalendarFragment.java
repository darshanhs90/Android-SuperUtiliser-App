package com.special.ResideMenuDemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.Calendar;

/**
 * User: special
 * Date: 13-12-22
 * Time: 下午1:31
 * Mail: specialcyci@gmail.com
 */
public class CalendarFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "CalendarFragment", Toast.LENGTH_SHORT).show();
        Calendar cal = Calendar.getInstance();
        Intent intent = new Intent(Intent.ACTION_EDIT);
        intent.setType("vnd.android.cursor.item/event");
        intent.putExtra("beginTime", cal.getTimeInMillis());
        intent.putExtra("allDay", true);
        intent.putExtra("rrule", "FREQ=YEARLY");
        intent.putExtra("endTime", cal.getTimeInMillis() + 60
                * 60 * 1000);
        intent.putExtra("title", "A Test Event from android app");
        startActivity(intent);
        return inflater.inflate(R.layout.calendar, container, false);
    }

}

//Uri EVENTS_URI = Uri.parse(CalendarContract.Events.CONTENT_URI.toString());
//ContentResolver cr = getActivity().getContentResolver();
//
//ContentValues values = new ContentValues();
//
//values.put("calendar_id", 1);
//        values.put(Events.TITLE, recordatorio);
//        values.put(Events.ALL_DAY, 1);
//        values.put(Events.EVENT_LOCATION, lugar);
//        values.put("dtstart", calDate.getTimeInMillis());
//        values.put("dtend", calDate.getTimeInMillis());
//        values.put(Events.DESCRIPTION, observaciones);
//        values.put("availability", 0);
//        values.put(Events.HAS_ALARM, true);
//        values.put(Events.EVENT_TIMEZONE, TimeZone.getDefault().toString());
//
//        Uri uri = cr.insert(EVENTS_URI, values);
//// to get the Id Event
//        long eventID = Long.parseLong(uri.getLastPathSegment());
