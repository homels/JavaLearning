package com.example.college;

import com.example.college.MainActivity;
import com.example.college.R;
import com.example.college.Internetconnection.InternetConnection;
import com.example.college.datatransport.DatabaseQuery;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.ViewGroup.LayoutParams;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
public class SearchFragment extends Fragment {
	CalThread calThread;
	String SubjectName=null;
	@SuppressWarnings("deprecation")
	@SuppressLint("InflateParams")
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, null);
        final DatabaseQuery database_query=new DatabaseQuery(MainActivity.database,getActivity());
        final AutoCompleteTextView et_search = (AutoCompleteTextView)view.findViewById(R.id.et_search);
        
        et_search.setAdapter(database_query.getSchoolName());
        Button bt_search = (Button) view.findViewById(R.id.bt_search);
        Button bt_english = (Button)view.findViewById(R.id.English);
        Button bt_math = (Button)view.findViewById(R.id.math);
        Button bt_chance = (Button)view.findViewById(R.id.chance);
        Button bt_pgr = (Button)view.findViewById(R.id.program);
        LinearLayout bt_collage = (LinearLayout)view.findViewById(R.id.bt_collage);
        LinearLayout bt_self = (LinearLayout)view.findViewById(R.id.bt_self);
        LinearLayout bt_study_prg = (LinearLayout)view.findViewById(R.id.bt_study_prg);
        LinearLayout bt_essay=(LinearLayout)view.findViewById(R.id.bt_essay);
        TextView bt_collegefind=(TextView)view.findViewById(R.id.collegefind);
        LinearLayout bt_majorfind=(LinearLayout)view.findViewById(R.id.majorfind);
        LinearLayout bt_localfind=(LinearLayout)view.findViewById(R.id.localfind);
        LinearLayout bt_coursefind=(LinearLayout)view.findViewById(R.id.coursefind);
        LinearLayout shishi=(LinearLayout)view.findViewById(R.id.shishi);
        //设置搜索按钮长宽相等
        WindowManager wm=getActivity().getWindowManager();
        LayoutParams layoutParams_bt=bt_search.getLayoutParams();
        layoutParams_bt.width=wm.getDefaultDisplay().getWidth()/11;
        layoutParams_bt.height=layoutParams_bt.width;
        bt_search.setLayoutParams(layoutParams_bt);
        Log.d("like","设置了一次 参数"+layoutParams_bt.width+" "+layoutParams_bt.height);
        
        shishi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	Intent intent=new Intent(getActivity(),SchoolfindActivity.class);
            	startActivity(intent);
            }
        });
        //英语资料上传
        bt_english.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	SubjectName="英语";
            	calThread=new CalThread();
                calThread.start();
            }
        });
        //高数资料
        bt_math.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	SubjectName="数学";
            	calThread=new CalThread();
                calThread.start();
            }
        });
        //概率论资料
        bt_chance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	SubjectName="概率论";
            	calThread=new CalThread();
                calThread.start();
            }
        });
        //编程资料
        bt_pgr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	SubjectName="编程";
            	calThread=new CalThread();
                calThread.start();
            }
        });
        //下面的文章按钮
        bt_essay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	Intent essay_intent=new Intent(getActivity(),EssayActivity.class);
            	startActivity(essay_intent);
            }
        });
        //院校查询按钮
        bt_collegefind.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent college_intent=new Intent(getActivity(),SchoolfindActivity.class);
            	startActivity(college_intent);
			}
		});
        //专业查询按钮
        bt_majorfind.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent major_intent=new Intent(getActivity(),MajorfindActivity.class);
            	startActivity(major_intent);
			}
		});
        //地区查询
        bt_localfind.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent local_intent=new Intent(getActivity(),LocalfindActivity.class);
            	startActivity(local_intent);
			}
		});
        //课程查询
        bt_coursefind.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent course_intent=new Intent(getActivity(),CoursefindActivity.class);
            	startActivity(course_intent);
			}
		});
        //尚未添加
        bt_collage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	Intent essay_intent1=new Intent(getActivity(),Essay1Activity.class);
            	startActivity(essay_intent1);            
            }
        });
        //尚未添加
        bt_self.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	Intent essay_intent2=new Intent(getActivity(),Essay2Activity.class);
            	startActivity(essay_intent2); 
            }
        });
        //尚未添加
        bt_study_prg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	Intent essay_intent3=new Intent(getActivity(),Essay3Activity.class);
            	startActivity(essay_intent3); 
            }
        });
        //查询按钮
        bt_search.setOnClickListener(new View.OnClickListener() {

        	Intent intent = new Intent(getActivity(),SchoolWebViewActivity.class);
            @Override
            public void onClick(View view) {
            	String sch_name = et_search.getText().toString();
            	String website;
            	
            	website=database_query.getSchoolWebsite(sch_name);
            	if(website==null)
            		Toast.makeText(getContext(), "没有查询到该高校!", Toast.LENGTH_SHORT).show();
            	else
            	{
            		Toast.makeText(getContext(), website, Toast.LENGTH_SHORT).show();
            		intent.putExtra("school_website", website);
            		startActivity(intent);
            	}
            }
        });
        return view;
    }
	class CalThread extends Thread
    {
    	public void run()
    	{
    		Looper.prepare();
    		Intent intent_subject = new Intent(getActivity(), SubjectActivity.class);
    		intent_subject.putExtra("SubjectName", SubjectName);
    		SubjectActivity.fileList=InternetConnection.CFileTransmit(SubjectName);
            startActivity(intent_subject);
    	   	Looper.loop();
    	}
    };
    
}