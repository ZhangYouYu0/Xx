package com.example.moded;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private ViewPager view_main;
    private TabLayout tablayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initView();
        initData();
    }

    private void initData() {
        final ArrayList<Fragment> list = new ArrayList<>();
        list.add(new HomeBlankFragment());
        list.add(new CollBlankFragment());

        view_main.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });


        tablayout.setupWithViewPager(view_main);
        tablayout.getTabAt(0).setText("首页");
        tablayout.getTabAt(1).setText("收藏");

    }

    private void initView() {
        view_main = (ViewPager) findViewById(R.id.view_main);
        tablayout = (TabLayout) findViewById(R.id.tablayout);
    }
}
