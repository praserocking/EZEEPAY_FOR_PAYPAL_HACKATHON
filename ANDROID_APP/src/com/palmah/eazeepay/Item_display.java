package com.palmah.eazeepay;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
 
public class Item_display extends ListActivity  implements OnClickListener
{
	
	
	String[]  gcmmsg,user_merchant,now_created;
	
	String sel_det="",cd_det="";
	Button[] b;
	LinearLayout ll;
	int ii;
 

	
 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.itemdiap);
		ll=(LinearLayout)findViewById(R.id.ll);
 
		// no more this
		// setContentView(R.layout.list_fruit);
		 gcmmsg= getIntent().getExtras().getStringArray("details");
		    user_merchant= getIntent().getExtras().getStringArray("user_merchant");
      for(int i=0;i<gcmmsg.length/2;i++)
      {
    	  now_created[i]=gcmmsg[i];
      }
      b=new Button[gcmmsg.length/2];
      for( ii=0;ii<gcmmsg.length/2;ii++)
      {
    	  b[ii]=new Button(this);
    	  b[ii].setText(now_created[ii]);
    	  ll.addView(b[ii]);
    	  b[ii].setId(ii);
    	  b[ii].setOnClickListener(this);
      }
		//setListAdapter(new ArrayAdapter<String>(this, R.layout.itemdiap,now_created));
 
		/*ListView listView = getListView();
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
			    		cd_det=gcmmsg[gcmmsg.length/2];
			    		call_intent();
			    	}
			    	else
			    	{
			    		Log.d("eeeeeeeeeeerrrrrrrrrrrrr","oooooooooooooorrrrrrrrr");
			    	}
			    }
			    
			    
			}
		});*/
		
		
 
	}
	
	public void call_intent()
	{
		Intent i=new Intent(this,Pattern.class);
		i.putExtra("view_adj","payment");
		i.putExtra("key", 2);
		i.putExtra("bank", sel_det);
		i.putExtra("card", cd_det);
		i.putExtra("user_merchant", user_merchant);
		startActivity(i);
		
	}

	@Override
	public void onClick(View v) 
	{
		// TODO Auto-generated method stub
		int id=v.getId();
		
		sel_det=now_created[id];
		cd_det=gcmmsg[id+gcmmsg.length];
		
		call_intent();
		
		
	}
 
}