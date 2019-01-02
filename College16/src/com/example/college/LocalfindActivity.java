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
	private String[] names=new String[]{"北京","河北","天津","上海","重庆","陕西","河南","山西","新疆","西藏","内蒙古","吉林","湖南","四川","广东","广西","宁夏","江西","贵州","福建"};
	private String[] descs=new String[]{"北京","河北","天津","上海","重庆","陕西","河南","山西","新疆","西藏","内蒙古","吉林","湖南","四川","广东","广西","宁夏","江西","贵州","福建"};
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
		String[] schoolname=new String[]{"北京大学","清华大学","复旦大学","长安大学","上海交通大学","西安交通大学","北京师范大学"};
	}
}