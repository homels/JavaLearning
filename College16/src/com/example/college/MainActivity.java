package com.example.college;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.college.datatransport.DatabaseQuery;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

public class MainActivity extends FragmentActivity {
	//TabLayout
    private TabLayout mTabLayout;
    //ViewPager
    private ViewPager mViewPager;
    //Title
    private List<String> mTitle;
    //Fragment
    private List<Fragment> mFragment;
    
    public static File database;//数据库文件
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏  
        setContentView(R.layout.activity_main);
        initData();
        initView();
        database=initdatabase();
    }

    /*
     * 初始化数据
     */
    private void initData() {
        mTitle = new ArrayList<String>();
        mTitle.add("用户登陆");
        mTitle.add("查询");

        mFragment = new ArrayList<Fragment>();
        mFragment.add(new UserFragment());
        mFragment.add(new SearchFragment());
        DatabaseQuery.CollegeMkdirs();//创建下载文件的目录
    }

    /*
     * 初始化view
     */
    private void initView() {
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        mViewPager = (ViewPager) findViewById(R.id.mViewPager);
        //
        mViewPager.setOffscreenPageLimit(mFragment.size());

        //mViewPager
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //添加适配器
        mViewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(),mFragment,mTitle));
        mTabLayout.setupWithViewPager(mViewPager);
    }
    //将数据库加载到手机中
    private File initdatabase()
    {
    	File file=getDatabasePath("school.db");
		if(!file.exists())
		{
			file.getParentFile().mkdirs();
		}
		else
		{
			return file;
		}
		BufferedInputStream bufferedInputStream = null;
		BufferedOutputStream bufferedOutputStream=null;
		try {
			bufferedInputStream=new BufferedInputStream(getAssets().open("school.db"));
			bufferedOutputStream=new BufferedOutputStream(new FileOutputStream(file));
			byte[] buffer=new byte[8192];
			int len=0;
			while((len=bufferedInputStream.read(buffer))!=-1)
			{
				bufferedOutputStream.write(buffer,0,len);
			}
			bufferedOutputStream.flush();
			return file;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
				try {
					if(bufferedInputStream!=null)
						bufferedInputStream.close();
					if(bufferedOutputStream!=null)
						bufferedOutputStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
    }
}
