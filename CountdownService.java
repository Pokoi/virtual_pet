///
/// Created by Jesus 'Pokoidev' Villar, jesusferminvillar@gmail.com (www.pokoidev.com)
/// On December 2018
/// Copyright © 2018 Pokoidev. Creative Commons License:
/// Attribution 4.0 International (CC BY 4.0)
///

package com.pokoidev.virtual_pet;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;


public class CountdownService extends Service {


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        new Count(5000, 1000).start();
        return super.onStartCommand(intent, flags, startId);

    }

    public CountdownService() {


    }

    @Override
    public IBinder onBind(Intent intent) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    class Count extends CountDownTimer {

        public Count(long millisInFuture, long countDownInterval) {

            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {

            Notification my_notification = new Notification();
        }


    }

    class Notification{

        //For the notification
        NotificationCompat.Builder notification;

        /**
         * Notification ID
         */
        static final int ID_UNICA = 1995;
        static final String CHANNEL_ID = "notification channel";


        /**
         * Builder Method
         */
        Notification(){

            //Create a notification
            notification = new NotificationCompat.Builder(getBaseContext(), CHANNEL_ID);
            notification.setAutoCancel(true);

            //Creates the notification channel
            createNotificationChannel();

            //Set the icon of the notification
            notification.setSmallIcon(R.drawable.b05);

            //Set the time of the notification
            notification.setWhen(System.currentTimeMillis());

            //Set the title of the notification
            notification.setContentTitle(getString(R.string.notification_title));

            //Set the text of the notification
            notification.setContentText(getString(R.string.notification_text));

            //Set the priority of the notification
            notification.setPriority(NotificationCompat.PRIORITY_DEFAULT);

            //Gets the intent to the main activity context
            //This is the activity that will be open when click on the notification
            Intent intent = new Intent(getBaseContext(), MainActivity.class);
            PendingIntent pending = PendingIntent.getActivity(getBaseContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            notification.setContentIntent(pending);

            //Creates the notification service
            NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            manager.notify(ID_UNICA, notification.build());

        }

        public void createNotificationChannel(){

            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                CharSequence name = "Personal Notifications";
                String description = "Include all the personal notifications";

                int importance = NotificationManager.IMPORTANCE_DEFAULT;

                NotificationChannel notificationChannel = new NotificationChannel(CHANNEL_ID, name, importance);

                notificationChannel.setDescription(description);

                NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                notificationManager.createNotificationChannel(notificationChannel);
            }

        }


    }

}
