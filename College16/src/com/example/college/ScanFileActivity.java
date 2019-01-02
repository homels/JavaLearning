package com.example.college;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.college.Internetconnection.InternetConnection;
import Commonality.CFile;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class ScanFileActivity extends Activity {
	
	ListView listView;
	TextView textView;
	File currentParent;
	File[] currentFiles;
	CalThread calThread;
	String SubjectName=null;
	int file_position;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scan_file);
		listView=(ListView)findViewById(R.id.list);
		textView=(TextView)findViewById(R.id.path);
		Bundle bundle=getIntent().getExtras();
    	SubjectName=(String)bundle.get("SubjectName");
		File root = new File("/");
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
							AlertDialog.Builder builder = new AlertDialog.Builder(ScanFileActivity.this)
									.setTitle("�ϴ��ļ�")
									.setIcon(R.drawable.ic_launcher)
									.setMessage("ȷ��Ҫ�ϴ��ļ���");
							setPositiveButton(builder);
							setNegativeButton(builder).create().show();
							return;
						}
						File[] tmp = currentFiles[position].listFiles();
						if(tmp==null||tmp.length==0)
						{
							Toast.makeText(ScanFileActivity.this, "û�з���Ȩ��", Toast.LENGTH_SHORT).show();
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
						try
						{
							if(!currentParent.getCanonicalPath().equals("/"))
							{
								currentParent = currentParent.getParentFile();
								currentFiles = currentParent.listFiles();
								inflateListView(currentFiles);
							}
						}
						catch(IOException e)
						{
							e.printStackTrace();
						}
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
			textView.setText("��ǰ·��Ϊ��"+currentParent.getCanonicalPath());
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder)
	{
		return builder.setPositiveButton("ȷ��", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog,int which)
			{
				calThread = new CalThread();
			    calThread.start();
			}
		});
	}
	private AlertDialog.Builder setNegativeButton(AlertDialog.Builder builder)
	{
		return builder.setNegativeButton("ȡ��", new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialog,int which)
			{
						
			}
		});
	}
	class CalThread extends Thread
	{
	    @SuppressLint("SimpleDateFormat")
		public void run()
	    {
	    	Looper.prepare();
	    	boolean succeed=InternetConnection.FileUpload(new CFile(LoginActivity.login_user.getUserId(),
	    			currentFiles[file_position].getName(),SubjectName,currentFiles[file_position]));
	    	if(succeed)
	    	{
	    		Toast.makeText(getApplication(), "�ļ��ϴ��ɹ�!", Toast.LENGTH_SHORT).show();
	    	}
	    	else
	    	{
	    		Toast.makeText(getApplication(), "�ļ��ϴ�ʧ��!", Toast.LENGTH_SHORT).show();
	    	}
	    	Looper.loop();
	    }
	};
	

}