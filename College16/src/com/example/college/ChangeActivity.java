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
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;
public class ChangeActivity extends Activity{
	private EditText et_UserNickname;
	private EditText et_UserSchool;
	private EditText et_UserMajor;
	private EditText et_UserPassword;
	private TextView tv_UserNumber;
	private Button bt_change;
	private Button bt_return;
	CalThread calThread;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change);
		et_UserNickname=(EditText)findViewById(R.id.et_usernickname);
	    et_UserSchool=(EditText)findViewById(R.id.et_school);
	    et_UserMajor=(EditText)findViewById(R.id.et_major);
	    et_UserPassword=(EditText)findViewById(R.id.et_userpassword);
	    tv_UserNumber=(TextView)findViewById(R.id.tv_usernumber);
	    et_UserNickname.setText(LoginActivity.login_user.getUserNickname());
	    et_UserSchool.setText(LoginActivity.login_user.getUserSchool());
	    et_UserMajor.setText(LoginActivity.login_user.getUserMajor());
	    et_UserPassword.setText(LoginActivity.login_user.getPassWord());
	    tv_UserNumber.setText(LoginActivity.login_user.getUserNumber());
	    bt_change=(Button)findViewById(R.id.bt_change);
	    bt_change.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(ChangeActivity.this)
						.setTitle("修改信息")
						.setIcon(R.drawable.ic_launcher)
						.setMessage("确定要修改信息吗");
				setPositiveButton(builder);
				setNegativeButton(builder).create().show();
			}
		});
	    bt_return=(Button)findViewById(R.id.bt_return);
	    bt_return.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent_return=new Intent(ChangeActivity.this,LoginActivity.class);
				startActivity(intent_return);
			}
		});
	}
	private AlertDialog.Builder setPositiveButton(AlertDialog.Builder builder)
	{
		return builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
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
		return builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
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
	    	User changed_user=new User(tv_UserNumber.getText().toString().trim(),
	    			et_UserPassword.getText().toString().trim(),
	    			et_UserSchool.getText().toString().trim(),
	    			et_UserMajor.getText().toString().trim(),
	    			et_UserNickname.getText().toString().trim());
	    	boolean succeed=InternetConnection.ChangeInformation(changed_user);
	    	if(succeed)
	    	{
	    		LoginActivity.login_user=changed_user;
		    	Intent intent=new Intent(ChangeActivity.this,MainActivity.class);
		    	startActivity(intent);
	    	}
	    	else
	    		Toast.makeText(ChangeActivity.this, "修改信息失败", Toast.LENGTH_SHORT).show();
	    	Looper.loop();
	    }
	};
}

