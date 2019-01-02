package com.example.college;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class LocalfindActivity extends Activity {
	private String[] names=new String[]{"����","�ӱ�","���","�Ϻ�","����","����","����","ɽ��","�½�","����","���ɹ�","����","����","�Ĵ�","�㶫","����","����","����","����","����"};
	private String[] descs=new String[]{"����","�ӱ�","���","�Ϻ�","����","����","����","ɽ��","�½�","����","���ɹ�","����","����","�Ĵ�","�㶫","����","����","����","����","����"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_localfind);
		List<Map<String,Object>> listItems= new ArrayList<Map<String,Object>>();
		for(int i=0;i<names.length;i++)
		{
			Map<String,Object> listItem = new HashMap<String,Object>();
			listItem.put("personName", names[i]);
			listItem.put("desc", descs[i]);
			listItems.add(listItem);
		}
		SimpleAdapter simpleAdapter = new SimpleAdapter(this,listItems,R.layout.simple_item,new String[]{"personName","desc"},new int[]{R.id.name,R.id.desc});
		ListView list = (ListView)findViewById(R.id.mylist);
		list.setAdapter(simpleAdapter);
		list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent,View view,int position,long id)
			{
				Toast.makeText(LocalfindActivity.this, "aa", Toast.LENGTH_LONG).show();
			}
		});
	}
	public void SchoolName()
	{
		String[] schoolname=new String[]{"������ѧ","�廪��ѧ","������ѧ","������ѧ","�Ϻ���ͨ��ѧ","������ͨ��ѧ","����ʦ����ѧ"};
	}
}