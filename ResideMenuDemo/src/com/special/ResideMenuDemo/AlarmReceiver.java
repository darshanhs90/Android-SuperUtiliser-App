package com.special.ResideMenuDemo;

/**
 * Created by hasudhakar on 7/9/15.
 */
import android.content.BroadcastReceiver;
        import android.content.Context;
        import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent service1 = new Intent(context, NotifyService.class);
        context.startService(service1);

    }
}
