package com.example.college;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class EssayActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_essay);
		WebView versionwv = (WebView) findViewById(R.id.about_version);        //��ȡwebview��id
		versionwv.setVerticalScrollBarEnabled(false);                                  //����������ù���������ʹ�õķ���
		versionwv.loadDataWithBaseURL("", getString(R.string.about_content1), "text/html", "utf-8","");  
	}
}
