package com.example.college;

import com.example.college.Internetconnection.InternetConnection;

import Commonality.User;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View.OnClickListener;
public class RegistActivity extends Activity{
	private EditText et_UserNickname;
	private EditText et_UserSchool;
	private EditText et_UserMajor;
	private EditText et_UserPassword;
	private Button bt_regist;
	private Button bt_return;
	CalThread calThread;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regist);
		et_UserNickname=(EditText)findViewById(R.id.et_usernickname);
	    et_UserSchool=(EditText)findViewById(R.id.et_school);
	    et_UserMajor=(EditText)findViewById(R.id.et_major);
	    et_UserPassword=(EditText)findViewById(R.id.et_userpassword);
	    bt_return=(Button)findViewById(R.id.bt_return);
	    bt_return.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent_return=new Intent(RegistActivity.this,LoginActivity.class);
				startActivity(intent_return);
			}
		});
		bt_regist=(Button)findViewById(R.id.bt_regist);
		bt_regist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(RegistActivity.this)
						.setTitle("×¢²áÕËºÅ")
						.setIcon(R.drawable.ic_launcher)
						.setMessage("È·¶¨Òª×¢²áÕËºÅÂð");
				setPositiveButton(builder);
				setNegativeButton(builder).create().show();
			}
		});
	}
	private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder)
	{
		return builder.setPositiveButton("È·¶¨", new DialogInterface.OnClickListener()
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
		return builder.setNegativeButton("È¡Ïû", new DialogInterface.OnClickListener()
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
	    	User changed_user=new User(null,
	    			et_UserPassword.getText().toString().trim(),
	    			et_UserSchool.getText().toString().trim(),
	    			et_UserMajor.getText().toString().trim(),
	    			et_UserNickname.getText().toString().trim());
	    	String usernumber=InternetConnection.RegistInformation(changed_user);
	    	if(usernumber!=null)
	    	{
	    		changed_user.setUserNumber(usernumber);
	    		LoginActivity.login_user=changed_user;
		    	Intent intent=new Intent(RegistActivity.this,MainActivity.class);
		    	startActivity(intent);
	    	}
	    	else
	    		Toast.makeText(RegistActivity.this, "ÕËºÅ×¢²áÊ§°Ü", Toast.LENGTH_SHORT).show();
	    	Looper.loop();
	    }
	};
}

