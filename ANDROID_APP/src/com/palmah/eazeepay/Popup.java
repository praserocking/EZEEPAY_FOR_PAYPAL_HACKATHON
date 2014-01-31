package com.palmah.eazeepay;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class Popup extends Activity 
{
	String[]  gcmmsg,user_merchant;
	//public Spinner spinner2;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.alert);
	    getWindow().setBackgroundDrawableResource(R.drawable.back);
	    gcmmsg= getIntent().getExtras().getStringArray("gcmmsg");
	    user_merchant= getIntent().getExtras().getStringArray("user_merchant");
	   
	   
	   popup_create("hai");
	   
	    Toast.makeText(getApplicationContext(), "Hai",Toast.LENGTH_SHORT).show();
	
	    // TODO Auto-generated method stub
	}

	private void popup_create(String msg) 
	{
		// TODO Auto-generated method stub
		Log.d("dddddddddddddddddddd", "ddsfssssdfs");
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);
		Toast.makeText(getApplicationContext(), "PpUp", Toast.LENGTH_SHORT).show();
 
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
						//choose_action(gcmmsg,user_merchant);
						
						call_intent();
						
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
	public void call_intent()
	{
		/*Intent i=new Intent(this,Pattern.class);
		i.putExtra("view_adj","payment");
		startActivity(i);*/
		
		Intent i=new Intent(this,Item_display.class);
		i.putExtra("details", gcmmsg);
		i.putExtra("user_merchant", user_merchant);
		startActivity(i);
	}
	
	/*public void choose_action(String[] details,String[] user_merchant)
	{
		add_dynamic_item(details);
	}*/

	/*private void add_dynamic_item(String[] details) 
	{
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		int ct=details.length;
		for(int i=0;i<(ct/2);i++)
		{
			list.add(details[i]);
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		

		
	}*/

	

		
	

}
