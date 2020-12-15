package com.example.ceshi1.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.ceshi1.Bean.FooDbBean;
import com.example.ceshi1.Bean.FoodBean;
import com.example.ceshi1.Db.DbUtil;
import com.example.ceshi1.MyAdapter;
import com.example.ceshi1.P.P;
import com.example.ceshi1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeBlankFragment extends Fragment implements com.example.ceshi1.V.View {

    private RecyclerView recycler;
    private ArrayList<FoodBean.DataBean.DatasBean> list;
    private MyAdapter myAdapter;

    public HomeBlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home_blank, container, false);
        initView(inflate);
        initData(); //创建方法
        initDj();
        initCa();
        return inflate;
    }

    private void initCa() { //长按事件
        myAdapter.setCa(new MyAdapter.ca() {
            @Override
            public void ca(FoodBean.DataBean.DatasBean f) {
                Intent intent = new Intent("gb"); //广播吐司
                intent.putExtra("a",f.getDesc());
                getActivity().sendBroadcast(intent);
            }
        });
    }

    private void initDj() {
        myAdapter.setDj(new MyAdapter.dj() { //点击事件
            @Override
            public void dj(int pos, FoodBean.DataBean.DatasBean f) {
                FooDbBean fooDbBean = new FooDbBean();
                fooDbBean.setDes(f.getDesc());
                fooDbBean.setPic(f.getEnvelopePic()); //添加数据
                DbUtil dbUtil = new DbUtil();
                long insert = dbUtil.insert(fooDbBean);
                if(insert>0){ //判断是否成功
                    Toast.makeText(getActivity(),"插入成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),"插入失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData() {
        P p = new P(this); //创建p对象并得到数据
        p.P1();
    }

    private void initView(View inflate) {
        recycler = inflate.findViewById(R.id.recycler);
        list = new ArrayList<>(); //找控件
        recycler.setLayoutManager(new LinearLayoutManager(getActivity())); //设置布局管理器
        myAdapter = new MyAdapter(getActivity(),list); //创建适配器
        recycler.setAdapter(myAdapter); //绑定适配
    }

    @Override
    public void sj(FoodBean foodBean) {
        List<FoodBean.DataBean.DatasBean> datas = foodBean.getData().getDatas(); //添加数据
        list.addAll(datas);
        myAdapter.notifyDataSetChanged(); //刷新
    }

    @Override
    public void yc(String yc) {
        Log.e("TAG", "yc: "+yc );
    }//错误提示
}
