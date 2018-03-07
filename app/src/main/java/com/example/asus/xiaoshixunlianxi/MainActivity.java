package com.example.asus.xiaoshixunlianxi;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.asus.xiaoshixunlianxi.adapter.MyFragmenTAdpater;
import com.example.asus.xiaoshixunlianxi.fragment.FamilyFragment;
import com.example.asus.xiaoshixunlianxi.fragment.StoryFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private TabLayout tab;
    private List<String> title;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initAdapter();
        initListener();
    }

    private void initListener() {

    }

    private void initAdapter() {
        MyFragmenTAdpater myFragmenTAdpater = new MyFragmenTAdpater(getSupportFragmentManager(), title, fragmentList);
        vp.setAdapter(myFragmenTAdpater);
        tab.setupWithViewPager(vp);
        vp.setCurrentItem(0);

    }

    private void initData() {
        title = new ArrayList<>();
        title.add("故事");
        title.add("亲子");

        fragmentList = new ArrayList<>();
        fragmentList.add(new StoryFragment());
        fragmentList.add(new FamilyFragment());


    }

    private void initView() {
        vp = (ViewPager) findViewById(R.id.vp);
        tab = (TabLayout) findViewById(R.id.tab);
    }
}
