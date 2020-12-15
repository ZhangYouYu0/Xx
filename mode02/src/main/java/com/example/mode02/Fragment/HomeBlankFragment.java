package com.example.mode02.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mode02.Bean.FooBean;
import com.example.mode02.Bean.FoodDBean;
import com.example.mode02.Db.DbUtil;
import com.example.mode02.MyAdapterHome;
import com.example.mode02.P.P;
import com.example.mode02.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeBlankFragment extends Fragment implements com.example.mode02.V.View {

    private RecyclerView recyclerview;
    private ArrayList<FooBean.DataBean.DatasBean> list;
    private MyAdapterHome myAdapterHome;

    public HomeBlankFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_home_blank, container, false);
        initView(inflate);
        initData();
        initDj();
        return inflate;
    }

    private void initDj() {
        myAdapterHome.setDj(new MyAdapterHome.dj() {
            @Override
            public void dj(FooBean.DataBean.DatasBean f) {
                FoodDBean foodDBean = new FoodDBean();
                foodDBean.setDes(f.getDesc());
                foodDBean.setPri(f.getEnvelopePic());
                DbUtil dbUtil = new DbUtil();
                long inset = dbUtil.inset(foodDBean);
                if(inset>0){
                    Toast.makeText(getActivity(),"添加成功",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(),"不允许重复添加",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initData() {
        P p = new P(this);
        p.P1();
    }

    private void initView(View inflate) {
        recyclerview = inflate.findViewById(R.id.recy_home);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        myAdapterHome = new MyAdapterHome(getActivity(),list);
        recyclerview.setAdapter(myAdapterHome);
    }

    @Override
    public void sj(FooBean fooBean) {
        List<FooBean.DataBean.DatasBean> datas = fooBean.getData().getDatas();
        list.addAll(datas);
        myAdapterHome.notifyDataSetChanged();
    }

    @Override
    public void yc(String yc) {
        Log.e("TAG", "yc: "+yc );
    }
}
