package com.example.moded;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.moded.Bean.FoodDbBean;
import com.example.moded.Db.DbUtil;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class CollBlankFragment extends Fragment {

    private RecyclerView recyclerview;
    private ArrayList<FoodDbBean> list;
    private MyAdapterColl myAdapterColl;

    public CollBlankFragment() {
        // Required empty public constructor
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

    private void initData() {
        DbUtil dbUtil = new DbUtil();
        List<FoodDbBean> query = dbUtil.query();
        list.addAll(query);
        myAdapterColl.notifyDataSetChanged();
    }

    private void initView(View inflate) {
        recyclerview = inflate.findViewById(R.id.recycler_coll);
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        myAdapterColl = new MyAdapterColl(getActivity(),list);
        recyclerview.setAdapter(myAdapterColl);
    }
}
