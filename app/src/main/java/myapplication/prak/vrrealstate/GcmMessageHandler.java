package myapplication.prak.vrrealstate;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;
/**
 * Created by Prakhar on 9/8/2015.
 */
public class GcmMessageHandler extends IntentService {

    String mes;
    private Handler handler;
    public GcmMessageHandler() {
        super("GcmMessageHandler");
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        handler = new Handler();
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();

        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        // The getMessageType() intent parameter must be the intent you received
        // in your BroadcastReceiver.
        String messageType = gcm.getMessageType(intent);

        mes = extras.getString("message");
       // showToast();

        for (String
                s : intent.getExtras().keySet())
        Log.i("GCM", "Received : (" +messageType+")  "+s);

      //  GcmBroadcastReceiver.completeWakefulIntent(intent);



        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this) ;
        mBuilder.setSmallIcon(R.mipmap.ic_launcher);
        mBuilder.setContentTitle(getResources().getString(R.string.noti));
        mBuilder.setContentText("" + mes);
        mBuilder.setAutoCancel(true);
        Intent notificationIntent = new Intent(this, Splash.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0);

        mBuilder.setContentIntent(contentIntent);

      Notification mm =  mBuilder.build();
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);


// notificationID allows you to update the notification later on.
        mNotificationManager.notify(12, mBuilder.build());


    }

    public void showToast(){
        handler.post(new Runnable() {
            public void run() {
                Toast.makeText(getApplicationContext(),mes , Toast.LENGTH_LONG).show();
            }
        });

    }
}
