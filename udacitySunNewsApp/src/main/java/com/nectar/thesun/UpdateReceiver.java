package com.nectar.thesun;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by victor on 3/11/15.
 */
public class UpdateReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Intent service1 = new Intent(context, UpdateService.class);
        context.startService(service1);

    }
}
