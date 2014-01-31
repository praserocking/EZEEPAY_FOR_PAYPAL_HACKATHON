package com.palmah.eazeepay;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class Item_display extends ListActivity {
	
	
	String[]  gcmmsg,user_merchant,now_created;
	
	String sel_det="",cd_det="";
 

	
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
 
		// no more this
		// setContentView(R.layout.list_fruit);
		 gcmmsg= getIntent().getExtras().getStringArray("details");
		    user_merchant= getIntent().getExtras().getStringArray("user_merchant");
      for(int i=0;i<gcmmsg.length/2;i++)
      {
    	  now_created[i]=gcmmsg[i];
      }
		setListAdapter(new ArrayAdapter<String>(this, R.layout.itemdiap,now_created));
 
		ListView listView = getListView();
		listView.setTextFilterEnabled(true);
 
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) 
			{
			    // When clicked, show a toast with the TextView text
			    Toast.makeText(getApplicationContext(),
				((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			    
			     sel_det=((TextView) view).getText().toString();
			     
			    for(int i=0;i<gcmmsg.length/2;i++)
			    {
			    	if(sel_det.equals(gcmmsg[i]))
			    	{
			    		cd_det=gcmmsg[i+gcmmsg.length];
			    		call_intent();
			    	}
			    	else
			    	{
			    		Log.d("eeeeeeeeeeerrrrrrrrrrrrr","oooooooooooooorrrrrrrrr");
			    	}
			    }
			    
			    
			}
		});
		
		
 
	}
	
	public void call_intent()
	{
		Intent i=new Intent(this,Pattern.class);
		i.putExtra("view_adj","payment");
		i.putExtra("bank", sel_det);
		i.putExtra("card", cd_det);
		i.putExtra("user_merchant", user_merchant);
		startActivity(i);
		
	}
 
}