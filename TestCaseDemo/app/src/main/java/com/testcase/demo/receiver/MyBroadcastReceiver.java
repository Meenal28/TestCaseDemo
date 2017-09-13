package com.testcase.demo.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.testcase.demo.service.MyBroadcastIntentService;

/**
 * Class MyBroadcastReceiver created on 6/2/17.
 */

public class MyBroadcastReceiver extends BroadcastReceiver{
    private final static String TAG = MyBroadcastReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG, "onReceive was triggered");

        Intent service = new Intent(context, MyBroadcastIntentService.class);
        service.putExtra("ACTION", intent.getStringExtra("PERFORM"));

        //start the service which needs to handle the intent
        context.startService(service);
    }
}
