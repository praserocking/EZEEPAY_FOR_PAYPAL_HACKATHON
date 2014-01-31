package com.palmah.eazeepay;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;



public class SQLiteAdapter 
{

public static final String MYDATABASE_NAME = "MY_DATABASE";
public static final String MYDATABASE_TABLE = "MY_TABLE";
public static final String MYDATABASE_TABLE1 = "MY_TABLE1";
public static final int MYDATABASE_VERSION = 1;
public static final String KEY_ID = "_id";
public static final String KEY_ID1 = "_id1";
public static final String KEY_CONTENT = "Content";

//create table MY_DATABASE (ID integer primary key, Content text not null);
public static final String SCRIPT_CREATE_DATABASE =
"create table " + MYDATABASE_TABLE + " ("
+ KEY_ID + " integer primary key autoincrement, "
+ KEY_CONTENT + " text not null);";

public static final String OTR_CREATE_DATABASE =
"create table " + MYDATABASE_TABLE + " ("
+ KEY_ID + " integer);";


private SQLiteHelper sqLiteHelper;
private SQLiteDatabase sqLiteDatabase;

private Context context;

public SQLiteAdapter(Context c){
context = c;
}

public SQLiteAdapter openToRead() throws android.database.SQLException {
sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
sqLiteDatabase = sqLiteHelper.getReadableDatabase();

return this;
}

public SQLiteAdapter openToWrite() throws android.database.SQLException {
sqLiteHelper = new SQLiteHelper(context, MYDATABASE_NAME, null, MYDATABASE_VERSION);
sqLiteDatabase = sqLiteHelper.getWritableDatabase();
return this;
}

public void close(){
sqLiteHelper.close();
}

public long insert(String content){

ContentValues contentValues = new ContentValues();
contentValues.put(KEY_CONTENT, content);
return sqLiteDatabase.insert(MYDATABASE_TABLE, null, contentValues);


}

public int deleteAll(){
return sqLiteDatabase.delete(MYDATABASE_TABLE, null, null);
}

public Cursor queueAll()
{
String[] columns = new String[]{KEY_ID, KEY_CONTENT};
Cursor cursor = sqLiteDatabase.query(MYDATABASE_TABLE, columns,
  null, null, null, null, null);
/* if(cursor.moveToFirst())
 {
	 cursor.getString(1);
 }*/
 
//String[] c=new String[]{"a","b"};



return cursor;
}

public int update(String newpat)
{
	ContentValues cv=new ContentValues();
	cv.put(KEY_CONTENT, newpat);
	int ct=sqLiteDatabase.update(MYDATABASE_TABLE, cv, null, null);
	return ct;
}
}
