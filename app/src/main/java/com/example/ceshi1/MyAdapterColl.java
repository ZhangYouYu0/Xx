package com.example.ceshi1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.ceshi1.Bean.FooDbBean;

import java.util.ArrayList;

public class MyAdapterColl extends RecyclerView.Adapter<MyAdapterColl.ViewHolder> {
    Context context; //上下文
    ArrayList<FooDbBean> list; //数据集合

    public MyAdapterColl(Context context, ArrayList<FooDbBean> list) { //构造方法
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapterColl.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rppt = LayoutInflater.from(context).inflate(R.layout.rl1_item, parent, false); //设置布局
        return new ViewHolder(rppt);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterColl.ViewHolder holder, int position) {
        FooDbBean fooDbBean = list.get(position); //添加数据
        holder.title.setText(fooDbBean.getDes());
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.circleCrop();
        Glide.with(context).load(fooDbBean.getPic()).apply(requestOptions).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
    } //获得数据长度

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView; //找控件
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
        }
    }
}
