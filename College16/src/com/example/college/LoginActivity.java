package com.example.college;

import com.example.college.Internetconnection.*;
import Commonality.User;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Looper;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends Activity {
	User user=null;
    public static User login_user=null;
	EditText et_username;
	EditText et_password;
	TextView tv_forget;
	TextView tv_regist;
	public CheckBox remember;
	CalThread calThread;
	private SharedPreferences preference;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		preference=getSharedPreferences("config", MODE_PRIVATE);
		et_username=(EditText)findViewById(R.id.username);
		et_password=(EditText)findViewById(R.id.userpassword);
		remember=(CheckBox)findViewById(R.id.remember);
		tv_forget=(TextView)findViewById(R.id.tv_forget);
		tv_regist=(TextView)findViewById(R.id.tv_regist);
		boolean ischecked=preference.getBoolean("ischecked", false);
		remember.setChecked(ischecked);
		if(ischecked)
		{
			et_username.setText(preference.getString("username", ""));
			et_password.setText(preference.getString("password", ""));
		}
	}
	public void ClickLogin(View v)
	{
		if(et_username.length()==0)
		{
			new AlertDialog.Builder(this)
			.setTitle("ע��")
			.setMessage("�û���Ϊ��")
			.setPositiveButton("ȷ��",null)
			.show();
		}
		else if(et_password.length()==0)
		{
			new AlertDialog.Builder(this)
			.setTitle("ע��")
			.setMessage("���벻��Ϊ��")
			.setPositiveButton("ȷ��",null)
			.show();
			
		}
		else
		{
			user=new User(et_username.getText().toString().trim(),et_password.getText().toString().trim());
			calThread = new CalThread();
		    calThread.start();
		}
	}
	class CalThread extends Thread
    {
        public void run()
        {
            Looper.prepare();
			try {
				login_user = InternetConnection.MessageTransmit(user);
				if(login_user!=null)
				{
					Editor edit=preference.edit();
					String username=et_username.getText().toString();
					String password=et_password.getText().toString();
					boolean ischecked=remember.isChecked();
					edit.putBoolean("ischecked", ischecked);
					if(remember.isChecked())
					{
						edit.putString("username", username).putString("password", password);
					}
					else
					{
						edit.remove("username").remove("password");
					}
					edit.commit();//�ύ������
					
					Intent intent=new Intent(LoginActivity.this,MainActivity.class);
					startActivity(intent);
				}
				else
				{
					Toast.makeText(LoginActivity.this, "�û����������벻�ԣ�����������!\n", Toast.LENGTH_SHORT).show();
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            Looper.loop();
        }
    };
	public boolean onKeyDown(int keyCode,KeyEvent event){  
	    if(keyCode==KeyEvent.KEYCODE_BACK)   
	    return true;//��ִ�и������¼�  
	    return super.onKeyDown(keyCode, event);//����ִ�и�����������¼�  
	}
	public void OnClickedForget(View view)
	{
	}
	public void OnClickedRegist(View view)
	{
		Intent regist_intent=new Intent(LoginActivity.this,RegistActivity.class);
		startActivity(regist_intent);
	}
}
