package com.example.mode02;

import android.Manifest;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.mode02.Fragment.CollBlankFragment;
import com.example.mode02.Fragment.HomeBlankFragment;
import com.example.mode02.Fragment.XZBlankFragment;
import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ViewPager pager_main;
    private TabLayout tab_lyout;
    private ArrayList<Fragment> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        PermissionsUtil.requestPermission(this, new PermissionListener() {
            @Override
            public void permissionGranted(@NonNull String[] permission) {
                Toast.makeText(MainActivity.this,"授权成功",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void permissionDenied(@NonNull String[] permission) {
                Toast.makeText(MainActivity.this,"授权失败",Toast.LENGTH_SHORT).show();
            }
        }, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        initView();
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new HomeBlankFragment());
        list.add(new CollBlankFragment());
        list.add(new XZBlankFragment());
        //list.add(new JzBlankFragment());

        pager_main.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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

        tab_lyout.setupWithViewPager(pager_main);
        tab_lyout.getTabAt(0).setText("首页");
        tab_lyout.getTabAt(1).setText("收藏");
        tab_lyout.getTabAt(2).setText("下载");
      //  tab_lyout.getTabAt(3).setText("饺子播放器");

    }

    private void initView() {
        pager_main = (ViewPager) findViewById(R.id.pager_main);
        tab_lyout = (TabLayout) findViewById(R.id.tab_lyout);
    }
}
