package com.example.college;

import com.example.college.R;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class UserFragment extends Fragment{
	private TextView tv_UserNickname;
	private TextView tv_UserSchool;
	private TextView tv_UserMajor;
	private TextView tv_UserNumber;
	@SuppressLint("InflateParams")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user, null);
        tv_UserNumber=(TextView)view.findViewById(R.id.user_number);
        tv_UserNickname=(TextView)view.findViewById(R.id.user_nickname);
        tv_UserSchool=(TextView)view.findViewById(R.id.user_school);
        tv_UserMajor=(TextView)view.findViewById(R.id.user_major);
        tv_UserNickname.setText(LoginActivity.login_user.getUserNickname());
        tv_UserSchool.setText(LoginActivity.login_user.getUserSchool());
        tv_UserMajor.setText(LoginActivity.login_user.getUserMajor());
        tv_UserNumber.setText(LoginActivity.login_user.getUserNumber());
        //退出登陆
        Button exit = (Button)view.findViewById(R.id.bt_exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_exit = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent_exit);
            }
        });
        TextView tv_change=(TextView)view.findViewById(R.id.tv_change);
        tv_change.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent_change = new Intent(getActivity(), ChangeActivity.class);
		        startActivity(intent_change);
			}
		});
        return view;
    }
}
