package com.palmah.eazeepay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomePage extends Activity implements OnClickListener
{
	ImageButton cp,otr;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.home_page);
	    Toast.makeText(getApplicationContext(), "HomePage", Toast.LENGTH_SHORT).show();
	    initialize();
	    // TODO Auto-generated method stub
	}
	public void initialize() 
	{
		// TODO Auto-generated method stub
		cp=(ImageButton)findViewById(R.id.cp);
		cp.setId(101);
		cp.setOnClickListener(this);
		otr=(ImageButton)findViewById(R.id.otr);
		otr.setId(102);
		otr.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		Intent i;
		int id=v.getId();
		switch (id) 
		{
		case 101:
			i=new Intent(this,Pattern.class);
			i.putExtra("view_adj", "chanpat");
			i.putExtra("key", 1);
			startActivity(i);
			
			break;
		case 102:
			//check database remaining
			i=new Intent(this,RegisterActivity.class);
			startActivity(i);
			break;

		default:
			break;
		}
		
	}

}
