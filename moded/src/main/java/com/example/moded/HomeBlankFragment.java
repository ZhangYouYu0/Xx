package com.example.moded;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.moded.Bean.FoodBean;
import com.example.moded.Bean.FoodDbBean;
import com.example.moded.Db.DbUtil;
import com.example.moded.P.P;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeBlankFragment extends Fragment implements com.example.moded.V.View {

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
        initVIew(inflate);
        initData();
        initDj();
        return inflate;
    }

    private void initDj() {
        myAdapter.setDj(new MyAdapter.dj() {
            @Override
            public void dj(int pos, FoodBean.DataBean.DatasBean f) {
                DbUtil dbUtil = new DbUtil();
                FoodDbBean foodDbBean = new FoodDbBean();
                foodDbBean.setDes(f.getDesc());
                foodDbBean.setPic(f.getEnvelopePic());
                long insert = dbUtil.insert(foodDbBean);
                if(insert>0){
                    Toast.makeText(getActivity(),"插入成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),"插入失败",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData() {
        P p = new P(this);
        p.P1();
    }

    private void initVIew(View inflate) {
        recycler = inflate.findViewById(R.id.recycler_home);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        myAdapter = new MyAdapter(getActivity(),list);
        recycler.setAdapter(myAdapter);
    }

    @Override
    public void sj(FoodBean foodBean) {
        List<FoodBean.DataBean.DatasBean> datas = foodBean.getData().getDatas();
        list.addAll(datas);
        myAdapter.notifyDataSetChanged();
    }

    @Override
    public void yc(String yc) {
        Log.e("TAG", "yc: "+yc );
    }
}
