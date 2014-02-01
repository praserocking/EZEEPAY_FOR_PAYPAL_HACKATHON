package com.palmah.eazeepay;



import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.android.gcm.GCMRegistrar;

import android.animation.ObjectAnimator;
import android.app.Activity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import android.animation.ValueAnimator;


public class Pattern extends Activity implements OnClickListener

{

	ImageButton v1,v2,h1,h2,h3,h4;
	Button okay,chanpassd,newpattern;
	String pass="";
	String change_passd="",oldpassd="";
	public SQLiteAdapter sqliteadapter;
	Controller aController;
	
	String bank,creditcard,user,merchant;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lad);
		aController = (Controller) getApplicationContext();
		anim();
		
		newpattern=(Button)findViewById(R.id.newpat);
		chanpassd=(Button)findViewById(R.id.chanpassd);
		chanpassd.setVisibility(View.INVISIBLE);
		newpattern.setVisibility(View.INVISIBLE);
		change_passd=getIntent().getExtras().getString("view_adj");
		int key=getIntent().getExtras().getInt("key");
		if(key==2)
		{
			bank=getIntent().getExtras().getString("bank");
			creditcard=getIntent().getExtras().getString("card");
			String[] t=getIntent().getExtras().getStringArray("user_merchant");
			user=t[0];
			merchant=t[1];
		}
		
		
		initialize();
		Log.d("qqqqqqqqqqqqqqqqq","wwwwwwwwwwwwwwwwwwwww");
		if(change_passd.equals("chanpat"))
		{
			newpattern.setVisibility(View.VISIBLE);
			newpattern.setId(301);
			newpattern.setOnClickListener(this);
			
			chanpassd.setVisibility(View.VISIBLE);
			chanpassd.setId(302);
			chanpassd.setOnClickListener(this);
			
			okay.setVisibility(View.INVISIBLE);
			
			
			
			//change_pattern();
		}
		else if(change_passd.equals("payment"))
		{
			Log.d("gggggggggggggggg","hhhhhhhhhhhhhhhhhh");
		}
		else
		{
			
		}
			
			
				
	}

	

	private void anim() {
		// TODO Auto-generated method stub
		ObjectAnimator cloudAnim=ObjectAnimator.ofFloat(findViewById(R.id.cloud1),"x",175);
		cloudAnim.setDuration(30000);
		cloudAnim.setRepeatCount(ValueAnimator.INFINITE);
		cloudAnim.setRepeatMode(ValueAnimator.RESTART);
		cloudAnim.start();
		ObjectAnimator cloudAnim2=ObjectAnimator.ofFloat(findViewById(R.id.cloud2),"x",175);
		cloudAnim2.setDuration(30000);
		cloudAnim2.setRepeatCount(ValueAnimator.INFINITE);
		cloudAnim2.setRepeatMode(ValueAnimator.RESTART);
		cloudAnim2.start();
		ObjectAnimator cloudAnim3=ObjectAnimator.ofFloat(findViewById(R.id.cloud3),"x",300);
		cloudAnim3.setDuration(30000);
		cloudAnim3.setRepeatCount(ValueAnimator.INFINITE);
		cloudAnim3.setRepeatMode(ValueAnimator.RESTART);
		cloudAnim3.start();
		ObjectAnimator cloudAnim4=ObjectAnimator.ofFloat(findViewById(R.id.cloud4),"x",200);
		cloudAnim4.setDuration(30000);
		cloudAnim4.setRepeatCount(ValueAnimator.INFINITE);
		cloudAnim4.setRepeatMode(ValueAnimator.RESTART);
		cloudAnim4.start();
		
		
	}



	private void initialize() 
	{
		// TODO Auto-generated method stub
	/*	v1=(ImageButton)findViewById(R.id.imageButton1);
		v1.setId(101);
		v1.setBackgroundDrawable(null);
		v1.setOnClickListener(this);*/

		
		/*v2=(ImageButton)findViewById(R.id.imageButton2);
		v2.setId(102);
		v2.setBackgroundDrawable(null);
		v2.setOnClickListener(this);*/
		
	 h1=(ImageButton)findViewById(R.id.imageButton11);
		h1.setId(103);
		h1.setBackgroundDrawable(null);
		h1.setOnClickListener(this);
		
		h2=(ImageButton)findViewById(R.id.imageButton22);
		h2.setId(104);
		h2.setBackgroundDrawable(null);
		h2.setOnClickListener(this);
		
		h3=(ImageButton)findViewById(R.id.imageButton33);
		h3.setId(105);
		h3.setBackgroundDrawable(null);
		h3.setOnClickListener(this);
		
		h4=(ImageButton)findViewById(R.id.imageButton44);
		h4.setId(106);
		h4.setBackgroundDrawable(null);
		h4.setOnClickListener(this);
		
		okay=(Button)findViewById(R.id.button11);
		okay.setId(107);
		okay.setOnClickListener(this);
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

	@Override
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		int id=v.getId();
		switch (id) {
		case 101:
			pass+="1";
			break;
		case 102:
			pass+="2";
			break;
		case 103:
			pass+="3";
			break;
		case 104:
			pass+="4";
			break;
		case 105:
			pass+="5";
			break;
		case 106:
			pass+="6";
			break;
		case 107:
			Toast.makeText(getApplicationContext(), "password:"+pass+"",Toast.LENGTH_SHORT).show();
			try {
				//store_db();
				ret_test();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.d("sssssssssssssssssssss","ddddddddddddddddddddd");
				e.printStackTrace();
			}
			break;
		case 301:
			oldpassd=pass;
			pass="";
			Toast.makeText(getApplicationContext(), "Now Touch the new password",Toast.LENGTH_SHORT).show();
			 
			break;
		case 302:
			try {
				change_pattern();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		default:
			break;
		}
	}

	
	
	
	private void change_pattern() throws Exception 
	{
		// TODO Auto-generated method stub
		sqliteadapter=new SQLiteAdapter(this);
		sqliteadapter.openToRead();
		Cursor c=sqliteadapter.queueAll();
		//sqliteadapter.close();
		if(c.moveToFirst())
		{
			String ret_passd=MyCrypto.decrypt("kurushetra", c.getString(1));
			if(ret_passd.equals(oldpassd))
			{
				sqliteadapter.openToWrite();
				int ct=sqliteadapter.update(MyCrypto.encrypt("kurushetra", pass));
				if(ct>0)
				{
					Toast.makeText(getApplicationContext(), "Updated successfully",Toast.LENGTH_SHORT).show();
				}
				else
				{
					Log.d("eeeeeeeeeeeeeeeerrrrrrrrrrrrr", "oooooooooooorrrrrrrr");
				}
				
			}
			else
			{
				Toast.makeText(getApplicationContext(), "Pattern Mismatch",Toast.LENGTH_SHORT).show();
			}
		}
		
		
		
	}
	
	private void ret_test() throws Exception 
	{
		// TODO Auto-generated method stub
		sqliteadapter=new SQLiteAdapter(this);
		sqliteadapter.openToRead();
		Cursor c=sqliteadapter.queueAll();
		if(c.moveToFirst())
		{
			String enc_std_passd=c.getString(1);
			String dec=MyCrypto.decrypt("kurushetra",enc_std_passd);
			Toast.makeText(getApplicationContext(), "dec:"+dec+"",Toast.LENGTH_SHORT).show();
			if(dec.equals(pass))
			{
				Toast.makeText(getApplicationContext(), "correct",Toast.LENGTH_SHORT).show();
				post_server();
				
			}
			else
			{
				Toast.makeText(getApplicationContext(), "incorrect",Toast.LENGTH_SHORT).show();
			}
			
		}
		else
		{
			Toast.makeText(getApplicationContext(), "error",Toast.LENGTH_SHORT).show();
		}
		sqliteadapter.close();
		
		
		
	}

	private int post_server() throws IOException 
	{
		// TODO Auto-generated method stub

    	int res = 0;
    	 
       //Log.i(Config.TAG, "registering device (regId = " + regId + ")");
        
        String serverUrl = "http://10.0.2.2/HACKATHON-PayPal/php/mobileconfirm.php";
        
        Map<String, String> params = new HashMap<String, String>();
        params.put("bank", bank);
        params.put("cardnum", creditcard);
        params.put("user", user);
        params.put("merchant", merchant);
        
      int response= aController.post(serverUrl, params);
      if(response==1)
      {
    	  Toast.makeText(getApplicationContext(), "Payment Successful", Toast.LENGTH_SHORT).show();
      }
        
        
		return res;
		
	}



	private void store_db() throws Exception 
	{
		// TODO Auto-generated method stub
		sqliteadapter=new SQLiteAdapter(this);
		sqliteadapter.openToWrite();
		String enc_passd=MyCrypto.encrypt("kurushetra", pass);
		sqliteadapter.insert(enc_passd);
		Toast.makeText(getApplicationContext(), "Stored",Toast.LENGTH_SHORT).show();
		sqliteadapter.close();
		
		
	}

}
