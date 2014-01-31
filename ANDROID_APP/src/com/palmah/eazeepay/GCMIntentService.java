package com.palmah.eazeepay;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {

	private static final String TAG = "GCMIntentService";
	
	private Controller aController = null;

    public GCMIntentService() {
    	// Call extended class Constructor GCMBaseIntentService
        super(Config.GOOGLE_SENDER_ID);
    }

    /**
     * Method called on device registered
     **/
    @Override
    protected void onRegistered(Context context, String registrationId) {
    	
    	//Get Global Controller Class object (see application tag in AndroidManifest.xml)
    	if(aController == null)
           aController = (Controller) getApplicationContext();
    	
        Log.i(TAG, "Device registered: regId = " + registrationId);
        aController.displayMessageOnScreen(context, "Your device registred with GCM");
        Log.d("NAME", MainActivity.name);
        aController.register(context, MainActivity.name, MainActivity.email, registrationId,MainActivity.model);
    }

    /**
     * Method called on device unregistred
     * */
    @Override
    protected void onUnregistered(Context context, String registrationId) {
    	if(aController == null)
            aController = (Controller) getApplicationContext();
        Log.i(TAG, "Device unregistered");
        aController.displayMessageOnScreen(context, getString(R.string.gcm_unregistered));
        aController.unregister(context, registrationId);
    }

    /**
     * Method called on Receiving a new message from GCM server
     * */
    @Override
    protected void onMessage(Context context, Intent intent) {
    	Log.d("ssssssssssssssssssss", "swwwwwwwwwwwwwwwwwww");
    	
    	
    	if(aController == null)
            aController = (Controller) getApplicationContext();
    	
        Log.i(TAG, "Received message");
        String ini_msg = intent.getExtras().getString("count");
        String[] user_merchant=new String[2];
        for(int i=0;i<2;i++)
        {
        	if(i==0)
        	user_merchant[i]=intent.getExtras().getString("user");
        	else
        	user_merchant[i]=intent.getExtras().getString("merchant");
        		
        }
        int ct=Integer.parseInt(ini_msg);
        String[] bk_msg=new String[ct];
        String[] cd_msg=new String[ct];
        for(int i=0;i<ct;i++)
        {
        	bk_msg[i]=intent.getExtras().getString("bank"+ct);
        	
        }
        for(int i=0;i<ct;i++)
        {
        	cd_msg[i]=intent.getExtras().getString("cardnum"+ct);
        	
        }
        String[] final_msg=new String[2*ct];
        for(int i=0;i<(2*ct);i++)
        {
        	if(i<ct)
        	{
        		final_msg[i]=bk_msg[i];
        	}
        	
        	else
        	{
        		final_msg[i]=cd_msg[i-ct];
        	}
        }
        
        aController.displayMessageOnScreen(context, ini_msg);
        // notifies user
        generateNotification(context, final_msg,ini_msg,user_merchant);
    }

    /**
     * Method called on receiving a deleted message
     * */
    @Override
    protected void onDeletedMessages(Context context, int total) {
    	
    	if(aController == null)
            aController = (Controller) getApplicationContext();
    	
        Log.i(TAG, "Received deleted messages notification");
        String message = getString(R.string.gcm_deleted, total);
        aController.displayMessageOnScreen(context, message);
        // notifies user
       // generateNotification(context, message);
    }

    /**
     * Method called on Error
     * */
    @Override
    public void onError(Context context, String errorId) {
    	
    	if(aController == null)
            aController = (Controller) getApplicationContext();
    	
        Log.i(TAG, "Received error: " + errorId);
        aController.displayMessageOnScreen(context, getString(R.string.gcm_error, errorId));
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
    	
    	if(aController == null)
            aController = (Controller) getApplicationContext();
    	
        // log message
        Log.i(TAG, "Received recoverable error: " + errorId);
        aController.displayMessageOnScreen(context, getString(R.string.gcm_recoverable_error,
                errorId));
        return super.onRecoverableError(context, errorId);
    }

    /**
     * Create a notification to inform the user that server has sent a message.
     */
    private  static void generateNotification(Context context, String[] final_message,String message,String[] user_merchant) {
        int icon = R.drawable.ic_launcher;
        long when = System.currentTimeMillis();
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notification = new Notification(icon, message, when);
        
        String title = context.getString(R.string.app_name);
        
        Intent notificationIntent = new Intent(context, Popup.class);
       // notificationIntent.putExtra("gcmmsg", message);
        notificationIntent.putExtra("gcmmsg", final_message);
        notificationIntent.putExtra("user_merchant", user_merchant);
        Log.d("rrrrrrrrrrrrrrrrrrrrrrr", "kkkkkkkkkkkkkkkkkkk");
        //modify
        //context.startActivity(notificationIntent);
        // set intent so it does not start a new activity
      // notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
       context.startActivity(notificationIntent);
       Log.d("hhhhhhhhhhhhhhhhhhhhh","jjjjjjjjjjjjjjjjjjjjjj");
                
        PendingIntent intent =
                PendingIntent.getActivity(context, 0, notificationIntent, 0);
        notification.setLatestEventInfo(context, title, message, intent);
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        
        // Play default notification sound
        notification.defaults |= Notification.DEFAULT_SOUND;
        
        //notification.sound = Uri.parse("android.resource://" + context.getPackageName() + "your_sound_file_name.mp3");
        
        // Vibrate if vibrate is enabled
        notification.defaults |= Notification.DEFAULT_VIBRATE;
        notificationManager.notify(0, notification);      

    }

}
