package com.example.college.datatransport;

import java.io.File;

import com.example.college.FinformationActivity;
import com.example.college.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.widget.ArrayAdapter;

public class DatabaseQuery {
	private SQLiteDatabase database;
	private Context context;
	public DatabaseQuery(File file,Context context)
	{
		database=SQLiteDatabase.openOrCreateDatabase(file, null);
		this.context=context;
	}
	public String getSchoolWebsite(String schoolname)
	{
		Cursor cursor=database.rawQuery("select * from school where name=?",new String[]{schoolname});
		if(cursor.moveToNext())
		{
			String website=cursor.getString(cursor.getColumnIndex("website"));
			return website;
		}
		return null;
	}
	public ArrayAdapter<String> getSchoolName()
	{
		ArrayAdapter<String> arrayAdapter= new ArrayAdapter<String>(context,R.layout.autotextview, new String[]{"长安大学","清华大学","北京大学","西安电子科技大学","西北工业大学","中国石油大学"});
		return arrayAdapter;
	}
	@SuppressLint("SdCardPath")
	public static String getSDPath() {
        boolean hasSDCard = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
        if (hasSDCard) {
            return Environment.getExternalStorageDirectory().toString();
        } else
            return "/data/data/";
    }
	public static void CollegeMkdirs()
	{
		String path=getSDPath()+"/College";
		File dir = new File(path);  
        if (!dir.exists())
        	dir.mkdirs();
	}
}
