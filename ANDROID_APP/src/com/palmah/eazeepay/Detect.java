package com.palmah.eazeepay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class Detect extends BroadcastReceiver
{
	Controller aController;

	@Override
	public void onReceive(Context context, Intent intent) 
	{
		// TODO Auto-generated method stub
		
		String newMessage = intent.getExtras().getString(Config.EXTRA_MESSAGE);
		
		// Waking up mobile if it is sleeping
		aController.acquireWakeLock(context);
		
		// Display message on the screen
		//lblMessage.append(newMessage + "\n");
		//popup_alert(newMessage);
		
		
		Toast.makeText(context, "Got Message: " + newMessage, Toast.LENGTH_LONG).show();
		
		// Releasing wake lock
		aController.releaseWakeLock();
		
	}

}
