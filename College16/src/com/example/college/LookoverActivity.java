package com.example.college;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.security.auth.Subject;

import com.example.college.ScanFileActivity.CalThread;
import com.example.college.datatransport.DatabaseQuery;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class LookoverActivity extends Activity {

	ListView listView;
	TextView textView;
	File currentParent;
	File[] currentFiles;
	CalThread calThread;
	String SubjectName=null;
	int file_position;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan_file);
		listView=(ListView)findViewById(R.id.list);
		textView=(TextView)findViewById(R.id.path);
		File root = new File(DatabaseQuery.getSDPath()+"/College");
		if(root.exists())
		{
			currentParent = root;
			currentFiles = root.listFiles();
			inflateListView(currentFiles);
		}
		listView.setOnItemClickListener(new OnItemClickListener()
				{
					public void onItemClick(AdapterView<?> parent,View view, int position,long id)
					{
						if(currentFiles[position].isFile())
						{
							file_position=position;
							
							return;
						}
						File[] tmp = currentFiles[position].listFiles();
						if(tmp==null||tmp.length==0)
						{
							Toast.makeText(LookoverActivity.this, "没有访问权限", Toast.LENGTH_SHORT).show();
						}
						else
						{
							currentParent = currentFiles[position];
							currentFiles=tmp;
							inflateListView(currentFiles);
						}
					}
				});
		Button parent = (Button)findViewById(R.id.parent);
		parent.setOnClickListener(new OnClickListener()
				{
					public void onClick(View v)
					{
						Intent i =new Intent(LookoverActivity.this,MainActivity.class);
						startActivity(i);
					}
				});
	}
	private void inflateListView(File[] files)
	{
		List<Map<String,Object>> listItems = new ArrayList<Map<String,Object>>();
		for(int i=0;i<files.length;i++)
		{
			Map<String,Object> listItem = new HashMap<String,Object>();
			if(files[i].isDirectory())
			{
				listItem.put("icon", R.drawable.ic_launcher);
			}
			else
			{
				listItem.put("icon", R.drawable.ic_launcher);
			}
			listItem.put("fileName", files[i].getName());
			listItems.add(listItem);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,
				listItems,R.layout.line
				,new String[]{"icon","fileName"}
				,new int[]{R.id.icon,R.id.file_name});
		listView.setAdapter(simpleAdapter);
		try
		{
			textView.setText("当前路径为："+currentParent.getCanonicalPath());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
}

