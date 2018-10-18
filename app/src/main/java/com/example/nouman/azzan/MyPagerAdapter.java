package com.example.nouman.azzan;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private  final List<Fragment> fragments=new ArrayList<>();
    private  final List<String> titlelist=new ArrayList<>();
    public MyPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }
    public  void addFragment(Fragment fragment,String title){
        fragments.add(fragment);
        titlelist.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return null;
        //return titlelist.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return  fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }
}
