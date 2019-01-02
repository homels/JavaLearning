package com.example.college;

import java.util.List;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter{
	private List<String> mTitles;
	private List<Fragment> mFragments;
	public MyFragmentPagerAdapter(FragmentManager fm,List<Fragment> mFragment,List<String> mTitles) {
		super(fm);
		this.mTitles=mTitles;
		this.mFragments=mFragment;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int position) {
		// TODO Auto-generated method stub
		return mFragments.get(position);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mTitles.size();
	}
	@Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }
}
