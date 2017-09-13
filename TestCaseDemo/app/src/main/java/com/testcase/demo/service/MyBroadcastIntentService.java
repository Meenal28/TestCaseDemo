package com.testcase.demo.service;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.testcase.demo.R;
import com.testcase.demo.view.MainActivity;

/**
 * Class MyBroadcastIntentService created on 6/2/17.
 */

public class MyBroadcastIntentService extends IntentService {
    private final static String TAG = MyBroadcastIntentService.class.getSimpleName();
    public final static int NOTIFICATION_ID = 335446435;
    public final static String NOTIFICATION_TAG = "BNTAG_ACTION";
    public Context context;

    public MyBroadcastIntentService() {
        super(MyBroadcastIntentService.class.getSimpleName());
        Log.d(TAG, "Creating new instance of MyBroadcastIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent was called");

        Bundle extras = intent.getExtras();

        if (extras != null && !extras.isEmpty()) {  // has effect of unparcelling Bundle
            Log.d(TAG, "Extras were found");
            context = context == null ? getApplicationContext() : context;
            String action = intent.getStringExtra("ACTION");

            this.sendNotification(action);
        }
    }

    public void setContext(Context context) {
        this.context = context;
    }

    private void sendNotification(String action) {
        Log.d(TAG, "Sending notification");

        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //set-up the action for authorizing the action
        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);


        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("App")
                        .setAutoCancel(Boolean.TRUE)
                        .setContentText("You are going to " + action);


        builder.setContentIntent(pendingIntent);

        notificationManager.notify(NOTIFICATION_TAG, NOTIFICATION_ID, builder.build());
    }
}
