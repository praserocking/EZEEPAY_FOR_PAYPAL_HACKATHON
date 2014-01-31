package com.palmah.eazeepay;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper
{
	public SQLiteHelper(Context context, String name,CursorFactory factory, int version) 
			{
			 super(context, name, factory, version);
			}

	@Override
	public void onCreate(SQLiteDatabase db) 
	{
		// TODO Auto-generated method stub
		Log.d("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
		SQLiteAdapter myadapter=new SQLiteAdapter(null);
		db.execSQL(myadapter.SCRIPT_CREATE_DATABASE); 
		Log.d("ccccccccccccccccccccccccccccc","ddddddddddddddddddddd");
		db.execSQL(myadapter.OTR_CREATE_DATABASE); 
		
		ContentValues cv1=new ContentValues();
		int c=0;
		cv1.put(SQLiteAdapter.KEY_ID1, c);
		db.insert(SQLiteAdapter.MYDATABASE_TABLE1, null,cv1);
		
		
		
		ContentValues cv=new ContentValues();
		String st_pass="";
		try {
			
			 st_pass=MyCrypto.encrypt("kurushetra","6543");
			 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cv.put(SQLiteAdapter.KEY_CONTENT,st_pass);
	    db.insert(SQLiteAdapter.MYDATABASE_TABLE, null, cv);
	   // this.close();
		Log.d("ccccccccccccccccccccccccccccc","ddddddddddddddddddddd");

		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		// TODO Auto-generated method stub
		
		
	}

}
