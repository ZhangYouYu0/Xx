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
import com.example.ceshi1.Bean.FoodBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {
    Context context; //创建上下文
    ArrayList<FoodBean.DataBean.DatasBean> list; //设置数据集合

    public MyAdapter(Context context, ArrayList<FoodBean.DataBean.DatasBean> list) { //创建构造方法
        this.context = context;
        this.list = list;
    }

    public static final int Y=0;
    public static final int E=1;

    @Override
    public int getItemViewType(int position) {
        if(position==Y){
            return Y;
        }else{
            return E;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==Y){
            View root = LayoutInflater.from(context).inflate(R.layout.rl_item, parent, false); //创建布局
            return new YViewHolder(root);
        }else{
            View root = LayoutInflater.from(context).inflate(R.layout.rl1_item, parent, false);
            return new EViewHolder(root);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType){
            case Y:
                YViewHolder yViewHolder = (YViewHolder) holder;
                yViewHolder.banner.setImages(list) //得到相对于集合
                        .setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                FoodBean.DataBean.DatasBean f = (FoodBean.DataBean.DatasBean) path;
                                Glide.with(context).load(f.getEnvelopePic()).into(imageView); //添加
                            }
                        }).start();
                break;
            case E:
                final FoodBean.DataBean.DatasBean datasBean = list.get(position); //得到数据对象
                EViewHolder eViewHolder = (EViewHolder) holder;
                eViewHolder.title.setText(datasBean.getDesc()); //添加数据
                Glide.with(context).load(datasBean.getEnvelopePic()).into(eViewHolder.imageView);
                eViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { //设置点击事件
                        if(dj!=null){
                            dj.dj(position,datasBean);
                        }
                    }
                });

                eViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() { //设置长按事件
                    @Override
                    public boolean onLongClick(View v) {
                        if(ca!=null){
                            ca.ca(datasBean);
                        }
                        return false;
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    } //集合长度

    public interface dj{ //点击接口
        void dj(int pos , FoodBean.DataBean.DatasBean f);
    }
    dj dj;

    public void setDj(MyAdapter.dj dj) {
        this.dj = dj;
    }

    private class YViewHolder extends RecyclerView.ViewHolder { //找控件
        Banner banner;
        public YViewHolder(View root) {
            super(root);
            banner = root.findViewById(R.id.banner);
        }
    }

    public interface ca{ //长按接口
        void ca(FoodBean.DataBean.DatasBean f);
    }
    ca ca;

    public void setCa(MyAdapter.ca ca) {
        this.ca = ca;
    }

    private class EViewHolder extends RecyclerView.ViewHolder { //找控件
        ImageView imageView;
        TextView title;
        public EViewHolder(View root) {
            super(root);
            imageView=root.findViewById(R.id.image);
            title=root.findViewById(R.id.title);
        }
    }
}
