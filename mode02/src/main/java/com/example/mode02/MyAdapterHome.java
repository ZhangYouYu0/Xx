package com.example.mode02;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.example.mode02.Bean.FooBean;
import com.example.mode02.Bean.FoodDBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class MyAdapterHome extends RecyclerView.Adapter {
    Context context;
    ArrayList<FooBean.DataBean.DatasBean> list;

    public MyAdapterHome(Context context, ArrayList<FooBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    private static final int Y = 0;
    private static final int E = 1;
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
        if (viewType == Y) {
            View root = LayoutInflater.from(context).inflate(R.layout.y_item, parent, false);
            return new YViewHolder(root);
        } else {
            View root = LayoutInflater.from(context).inflate(R.layout.e_item, parent, false);
            return new EViewHolder(root);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType){
            case Y:

                YViewHolder yViewHolder = (YViewHolder) holder;
                yViewHolder.banner.setImages(list)
                        .setImageLoader(new ImageLoader() {
                            @Override
                            public void displayImage(Context context, Object path, ImageView imageView) {
                                FooBean.DataBean.DatasBean f = (FooBean.DataBean.DatasBean) path;
                                Glide.with(context).load(f.getEnvelopePic()).into(imageView);
                            }
                        }).start();
                break;
            case E:
                final FooBean.DataBean.DatasBean datasBean = list.get(position);
                EViewHolder eViewHolder = (EViewHolder) holder;
                eViewHolder.title.setText(datasBean.getDesc());
                Glide.with(context).load(datasBean.getEnvelopePic()).apply(RequestOptions.bitmapTransform(new RoundedCorners(8))).into(eViewHolder.imageView);
                eViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(dj!=null){
                            dj.dj(datasBean);
                        }
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface dj{
        void dj(FooBean.DataBean.DatasBean f);
    }
    dj dj;

    public void setDj(MyAdapterHome.dj dj) {
        this.dj = dj;
    }

    private class YViewHolder extends RecyclerView.ViewHolder {
        Banner banner;
        public YViewHolder(View root) {
            super(root);
            banner=root.findViewById(R.id.banner);
        }
    }

    private class EViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;

        public EViewHolder(View root) {
            super(root);
            title=root.findViewById(R.id.title);
            imageView=root.findViewById(R.id.image);
        }
    }
}
