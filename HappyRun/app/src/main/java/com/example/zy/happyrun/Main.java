package com.example.zy.happyrun;

import android.app.Activity;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zy on 2016/7/16.
 */
public class Main extends AppCompatActivity {
    ArrayList<Map<String,Object>> viewlist=new ArrayList<Map<String, Object>>();
    TabLayout tabLayout=null;
    LocalActivityManager activityManager;
    ViewPager viewpager=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        activityManager=new LocalActivityManager(this,true);
        activityManager.dispatchCreate(savedInstanceState);
        tabLayout= (TabLayout) findViewById(R.id.tablayout);
        viewpager= (ViewPager) findViewById(R.id.container);
        Map<String,Object>map1=new HashMap<String, Object>();
        map1.put("title", "第一个Activity");
        map1.put("view", getView((String) map1.get("title"), new Intent(this, F_activity.class)));
        viewlist.add(map1);
        Map<String,Object>map2=new HashMap<String, Object>();
        map2.put("title","第二个Activity");
        map2.put("view", getView((String) map2.get("title"), new Intent(this, S_activity.class)));
        viewlist.add(map2);
        Map<String,Object>map3=new HashMap<String, Object>();
        map3.put("title","第三个Activity");
        map3.put("view", getView((String) map3.get("title"), new Intent(this, T_activity.class)));
        viewlist.add(map3);

        MainViewPaperAdapter mainViewPaperAdapter=new MainViewPaperAdapter(viewlist,getSupportFragmentManager());
        tabLayout.setTabsFromPagerAdapter(mainViewPaperAdapter);
        viewpager.setAdapter(mainViewPaperAdapter);
        tabLayout.setupWithViewPager(viewpager);
        viewpager.setOnPageChangeListener(new MyViewPagerPageChangeListener());
    }
    public class MyViewPagerPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            loadActivity(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private View getView(String id, Intent intent) {
        return activityManager.startActivity(id, intent).getDecorView();
    }

    //调用子Activity发放
    private void loadActivity(int position) {
        Activity activity = activityManager.getActivity((String) viewlist.get(position).get("title"));

    }
}
