package com.example.ceshi1.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ceshi1.Bean.FooDbBean;
import com.example.ceshi1.Db.DbUtil;
import com.example.ceshi1.MyAdapterColl;
import com.example.ceshi1.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollBlankFragment extends Fragment {

    private RecyclerView recycler_coll;
    private ArrayList<FooDbBean> list;
    private MyAdapterColl myAdapterColl;

    public CollBlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            initData();
        }else{
            if(list!=null && list.size()>0){
                list.clear();
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_coll_blank, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
        DbUtil dbUtil = new DbUtil();
        List<FooDbBean> query = dbUtil.query();
        list.addAll(query); //得到数据库内的数据
        myAdapterColl.notifyDataSetChanged();
    }

    private void initView(View inflate) {
        recycler_coll = inflate.findViewById(R.id.recycler_coll);//找控件
        recycler_coll.setLayoutManager(new LinearLayoutManager(getActivity())); //设置布局管理器
        list = new ArrayList<>(); //设置数据集合
        myAdapterColl = new MyAdapterColl(getActivity(),list); //创建适配
        recycler_coll.setAdapter(myAdapterColl); //绑定
    }
}
