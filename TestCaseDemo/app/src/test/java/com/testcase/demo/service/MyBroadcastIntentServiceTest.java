package com.testcase.demo.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.Shadows;
import org.robolectric.shadows.ShadowLog;
import org.robolectric.shadows.ShadowNotification;
import org.robolectric.shadows.ShadowNotificationManager;

/**
 * Class MyBroadcastIntentServiceTest created on 6/2/17.
 */
@RunWith(RobolectricTestRunner.class)
public class MyBroadcastIntentServiceTest {

    static {
        ShadowLog.stream = System.out;
    }

    @Before
    public void setup() {
    }

    @Test
    public void testNoBundleExtrasFound() {
        Context context = RuntimeEnvironment.application;
        Intent serviceIntent = new Intent(context, MyBroadcastIntentServiceMock.class);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        //Robolectric.getShadowApplication().startService(serviceIntent);
        MyBroadcastIntentServiceMock service = new MyBroadcastIntentServiceMock();
        service.onCreate();
        service.onHandleIntent(serviceIntent);

        Assert.assertEquals("Expected no notifications", 0, Shadows.shadowOf(notificationManager).size());
    }

    @Test
    public void testWithBundleExtrasFound() {
        Intent serviceIntent = new Intent(RuntimeEnvironment.application, MyBroadcastIntentServiceMock.class);
        Bundle bundle = new Bundle();
        bundle.putString("ACTION", "eat an apple");
        serviceIntent.putExtras(bundle);

        NotificationManager notificationManager = (NotificationManager) RuntimeEnvironment.application.getSystemService(Context.NOTIFICATION_SERVICE);

        //Robolectric.getShadowApplication().startService(serviceIntent);
        MyBroadcastIntentServiceMock service = new MyBroadcastIntentServiceMock();
        service.onCreate();
        service.setContext(RuntimeEnvironment.application);
        service.onHandleIntent(serviceIntent);


        ShadowNotificationManager manager = Shadows.shadowOf(notificationManager);
        Assert.assertEquals("Expected one notification", 1, manager.size());

        Notification notification = manager.getNotification(MyBroadcastIntentService.NOTIFICATION_TAG, MyBroadcastIntentService.NOTIFICATION_ID);
        Assert.assertNotNull("Expected notification object", notification);

        ShadowNotification shadowNotification = Shadows.shadowOf(notification);
        Assert.assertNotNull("Expected shadow notification object", shadowNotification);

        Assert.assertEquals("You are going to eat an apple", shadowNotification.getContentText());
    }

    class MyBroadcastIntentServiceMock extends MyBroadcastIntentService {
        @Override
        public void onHandleIntent(Intent intent) {
            super.onHandleIntent(intent);
        }

        @Override
        public void setContext(Context context) {
            super.setContext(context);
        }
    }
}
