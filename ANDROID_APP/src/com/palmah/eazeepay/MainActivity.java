package com.palmah.eazeepay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gcm.GCMRegistrar;

public class MainActivity extends Activity {
	// label to display gcm messages
	TextView lblMessage;
	Controller aController;
	int resid=0;
	
	// Asyntask
	AsyncTask<Void, Void, Void> mRegisterTask;
	
	public static String name;
	public static String email;
	public static String model;

	@Override
	public void onCreate(Bundle savedInstanceState) { 
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//Get Global Controller Class object (see application tag in AndroidManifest.xml)
		aController = (Controller) getApplicationContext();
		
		Log.d("aaaaaaaaaaaaaaaaaa","wwwwwwwwwwwwwwwwwwwwwwwwwwww");
		// Check if Internet present
		if (!aController.isConnectingToInternet()) {
			
			// Internet Connection is not present
			aController.showAlertDialog(MainActivity.this,
					"Internet Connection Error",
					"Please connect to Internet connection", false);
			// stop executing code by return
			return;
		}
		
		// Getting name, email from intent
		Intent i = getIntent();
		
		name = i.getStringExtra("name");
		email = i.getStringExtra("email");		
		
		// Make sure the device has the proper dependencies.
		GCMRegistrar.checkDevice(this);
		
		Log.d("rrrrrrrrrrr","ttttttttttt");
		// Make sure the manifest permissions was properly set 
		GCMRegistrar.checkManifest(this);

		lblMessage = (TextView) findViewById(R.id.lblMessage);
		
		// Register custom Broadcast receiver to show messages on activity
		/*registerReceiver(mHandleMessageReceiver, new IntentFilter(
				Config.DISPLAY_MESSAGE_ACTION));*/
		
		// Get GCM registration id
		final String regId = GCMRegistrar.getRegistrationId(this);

		Log.d("hhhhhhhhhhhhhhh","lllllllllllllllllllll");
		// Check if regid already presents
		if (regId.equals("")) {
			
			// Register with GCM			
			GCMRegistrar.register(this, Config.GOOGLE_SENDER_ID);
			Log.d("iiiiiiiiiiiiiiiiii","cccccccccccnnnnnnnn");
			lblMessage.setText("regid1:"+GCMRegistrar.getRegistrationId(this)+"");
			Log.d("gggggggggggggggoooooooooooooooo","tttttttttttttttttt");
			
		} else {
			
			lblMessage.setText("regid2:"+GCMRegistrar.getRegistrationId(this)+"");
			Log.d("iiiiiiiiiiiiiidddddddddddd",GCMRegistrar.getRegistrationId(this));
			// Device is already registered on GCM Server
			/*if (GCMRegistrar.isRegisteredOnServer(this)) {
				
				// Skips registration.				
				Toast.makeText(getApplicationContext(), "Already registered with GCM Server", Toast.LENGTH_LONG).show();
			
			} else {*/
				
				// Try to register again, but not in the UI thread.
				// It's also necessary to cancel the thread onDestroy(),
				// hence the use of AsyncTask instead of a raw thread.
				
				final Context context = this;
				mRegisterTask = new AsyncTask<Void, Void, Void>() {

					@Override
					protected Void doInBackground(Void... params) {
						
						// Register on our server
						// On server creates a new user
						Model m=new Model();
						
						 model=m.getDeviceName();
						 
						 resid=aController.register(context, name, email, regId,model);
						
						
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						mRegisterTask = null;
					}

				};
				
				// execute AsyncTask
				mRegisterTask.execute(null, null, null);
			//}
				if(resid==1)
				{
					Toast.makeText(getApplicationContext(), "Successfully Registered your dev_id", Toast.LENGTH_SHORT).show();
					Intent successintent=new Intent(this,HomePage.class);
					startActivity(successintent);
					
				}
				else
				{
					Toast.makeText(getApplicationContext(), "Username&Password Mismatch", Toast.LENGTH_SHORT).show();
					
				}
		}
	}		

	// Create a broadcast receiver to get message and show on screen 
	/*private final BroadcastReceiver mHandleMessageReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			
			String newMessage = intent.getExtras().getString(Config.EXTRA_MESSAGE);
			
			// Waking up mobile if it is sleeping
			aController.acquireWakeLock(getApplicationContext());
			
			// Display message on the screen
			lblMessage.append(newMessage + "\n");
			//popup_alert(newMessage);
			
			
			Toast.makeText(getApplicationContext(), "Got Message: " + newMessage, Toast.LENGTH_LONG).show();
			
			// Releasing wake lock
			aController.releaseWakeLock();
		}

		
	};*/
	
	public void popup_alert(String newMessage) 
	{
		// TODO Auto-generated method stub
		Log.d("dddddddddddddddddddd", "ddsfssssdfs");
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);
		Toast.makeText(getApplicationContext(), "inside", Toast.LENGTH_SHORT).show();
 
			// set title
			alertDialogBuilder.setTitle("Payment!!!");
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
 
			// set dialog message
			alertDialogBuilder
				.setMessage("Are you sure to make Payment")
				.setCancelable(false)
				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, close
						// current activity
						
					//call ladder activity	
						
					}
				  })
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						// if this button is clicked, just close
						// the dialog box and do nothing
						dialog.cancel();
					}
				});
 
				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();
 
				// show it
				alertDialog.show();
		
		
	}
	@Override
	protected void onDestroy() {
		// Cancel AsyncTask
		if (mRegisterTask != null) {
			mRegisterTask.cancel(true);
		}
		try {
			// Unregister Broadcast Receiver
			//unregisterReceiver(mHandleMessageReceiver);
			
			//Clear internal resources.
			GCMRegistrar.onDestroy(this);
			
		} catch (Exception e) {
			Log.e("UnRegister Receiver Error", "> " + e.getMessage());
		}
		super.onDestroy();
	}

}
