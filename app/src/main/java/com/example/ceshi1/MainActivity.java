package com.example.ceshi1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.ceshi1.Fragment.CollBlankFragment;
import com.example.ceshi1.Fragment.HomeBlankFragment;
import com.example.ceshi1.Fragment.XZBlankFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewpager;
    private TabLayout tablyout;
    private ArrayList<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new HomeBlankFragment()); //添加页面
        list.add(new CollBlankFragment());
        list.add(new XZBlankFragment());
        viewpager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) { //设置适配器
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            } //适配器页面的数量
        });


        tablyout.setupWithViewPager(viewpager); //绑定
        tablyout.getTabAt(0).setText("首页"); //设置页面按钮
        tablyout.getTabAt(1).setText("收藏");
        tablyout.getTabAt(2).setText("下载");

    }

    private void initView() {
        viewpager = (ViewPager) findViewById(R.id.viewpager); //找控件
        tablyout = (TabLayout) findViewById(R.id.tablyout);
    }
}
