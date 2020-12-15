package com.example.mode02.Fragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.example.mode02.Bean.FoodDBean;
import com.example.mode02.Db.DbUtil;
import com.example.mode02.MyAdapterColl;
import com.example.mode02.MyAdapterHome;
import com.example.mode02.R;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollBlankFragment extends Fragment {

    private RecyclerView recycler;
    private ArrayList<FoodDBean> list;
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
        initDj();
        initCa();
        return inflate;
    }

    private void initCa() {
        myAdapterColl.setCa(new MyAdapterColl.ca() {
            @Override
            public void ca(FoodDBean foodDBean, final int pos) {
                View root1 = LayoutInflater.from(getActivity()).inflate(R.layout.pop1_item, null, false);
                final PopupWindow popupWindow = new PopupWindow(root1,400, 400);
                popupWindow.setFocusable(true);
            //    popupWindow.setFocusable(true);
                popupWindow.setBackgroundDrawable(new ColorDrawable());

                final EditText edname = root1.findViewById(R.id.ed_name);
                    edname.setText(foodDBean.getDes());
                Button bnt2 = root1.findViewById(R.id.bnt2);
                bnt2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String s = edname.getText().toString();
                        FoodDBean foodDBean1 = list.get(pos);
                        foodDBean1.setDes(s);
                        DbUtil dbUtil = new DbUtil();
                        dbUtil.update(foodDBean1);
                        myAdapterColl.notifyDataSetChanged();
                        popupWindow.dismiss();
                    }
                });
                setback(0.5f);
                popupWindow.showAsDropDown(root1,300,600);
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        setback(1f);
                    }
                });
            }
        });
    }

    private void setback(float v) {
        WindowManager.LayoutParams attributes = getActivity().getWindow().getAttributes();
        attributes.alpha=v;
        getActivity().getWindow().setAttributes(attributes);
    }

    private void initDj() {
        myAdapterColl.setDj(new MyAdapterColl.dj() {
            @Override
            public void dj(final FoodDBean foodDBean,final int pos) {
                View  root = LayoutInflater.from(getActivity()).inflate(R.layout.pop_item, null, false);
                final AlertDialog aler = new AlertDialog.Builder(getActivity())
                      //  .setTitle("是否删除")
                        .setView(root)
                        .show();
                Button bnt = root.findViewById(R.id.bnt1);
                bnt.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DbUtil dbUtil = new DbUtil();
                        dbUtil.delete(foodDBean);
                        list.remove(pos);
                        myAdapterColl.notifyDataSetChanged();
                        aler.cancel();
                    }
                });
            }
        });
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
        List<FoodDBean> query = dbUtil.query();
        list.addAll(query);
        myAdapterColl.notifyDataSetChanged();
    }

    private void initView(View inflate) {
        recycler = inflate.findViewById(R.id.recy_coll);
        recycler.setLayoutManager(new LinearLayoutManager(getActivity()));
        list = new ArrayList<>();
        myAdapterColl = new MyAdapterColl(getActivity(),list);
        recycler.setAdapter(myAdapterColl);
    }
}
