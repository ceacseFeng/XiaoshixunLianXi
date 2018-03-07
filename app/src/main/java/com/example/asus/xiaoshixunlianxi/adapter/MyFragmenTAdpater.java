package com.example.asus.xiaoshixunlianxi.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by ASUS on 2018/3/1.
 */

public class MyFragmenTAdpater extends FragmentPagerAdapter {
    private List<String> title;
    private List<Fragment> fragmentList;

    public MyFragmenTAdpater(FragmentManager fm, List<String> title, List<Fragment> fragmentList) {
        super(fm);
        this.title = title;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}
