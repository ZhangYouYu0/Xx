package com.example.mode02.Fragment;

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

import com.example.mode02.Bean.SerBean;
import com.example.mode02.MyService;
import com.example.mode02.R;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class XZBlankFragment extends Fragment {

    private ProgressBar probar;
    private TextView title;
    private Button bnt;
    private String apkUrl = "https://alissl.ucdl.pp.uc.cn/fs08/2017/05/02/7/106_64d3e3f76babc7bce131650c1c21350d.apk";
    private File file = Environment.getExternalStorageDirectory();
    private File getSdrk= Environment.getExternalStorageDirectory();
    public XZBlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_x_z_blank, container, false);
        EventBus.getDefault().register(this);
        initView(inflate);
        return inflate;
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void initData(SerBean serBean){
        probar.setMax((int) serBean.getLen());
        probar.setProgress((int) serBean.getCon());
        title.setText(Math.ceil(serBean.getCon()*100f / serBean.getLen())+"%");
        if(serBean.getLen()==serBean.getCon()){
            sv(file+"/con.apk");

        }
    }

    private void sv(String s) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://"+s),"application/vnd.android.package-archive");
        startActivity(intent);
    }

    private void initView(View inflate) {
        probar = inflate.findViewById(R.id.xz_pro);
        title = inflate.findViewById(R.id.xz_title);
        bnt = inflate.findViewById(R.id.bnt);


        bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MyService.class);
                getActivity().startService(intent);
            }
        });
    }
}
