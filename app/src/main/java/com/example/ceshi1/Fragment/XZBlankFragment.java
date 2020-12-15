package com.example.ceshi1.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ceshi1.MyApp;
import com.example.ceshi1.MyService;
import com.example.ceshi1.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

/**
 * A simple {@link Fragment} subclass.
 */
public class XZBlankFragment extends Fragment {

    private ProgressBar probar;
    private Button bnt;
    private TextView title;
    private File e = Environment.getExternalStorageDirectory();
    public XZBlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_x_z_blank, container, false);
        EventBus.getDefault().register(this);
        initView(inflate);//设置方法
        return inflate;
    }

    @Subscribe(threadMode = ThreadMode.MAIN) //变为主线
    public void initData(SerBwan serBwan){
        probar.setMax((int) serBwan.getLen());
        probar.setProgress((int) serBwan.getCon()); //添加数据
        title.setText(Math.ceil(serBwan.getCon()*100f/serBwan.getLen())+"%");
        if(serBwan.getLen()==serBwan.getCon()){ //判断
            Intent intent = new Intent(Intent.ACTION_VIEW); //下载
            intent.setDataAndType(Uri.parse("file://"+e+"/e.apk"),"application/vnd.android.package-archive");
            startActivity(intent);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    private void initView(View inflate) {
        probar = inflate.findViewById(R.id.probar);
        title = inflate.findViewById(R.id.tv_title); //找控件
        bnt = inflate.findViewById(R.id.bnt);
        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onstac();
            }
        });
    }

    private void onstac() {
        Intent intent = new Intent(getActivity(), MyService.class); //创建意图
        getActivity().startService(intent); //启动并跳转到服务
    }
}
