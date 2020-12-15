package com.example.moded;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moded.Bean.FoodBean;

import java.util.ArrayList;

class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    Context context;
    ArrayList<FoodBean.DataBean.DatasBean> list;

    public MyAdapter(Context context, ArrayList<FoodBean.DataBean.DatasBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.rl1_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, final int position) {
        final FoodBean.DataBean.DatasBean datasBean = list.get(position);
        holder.title.setText(datasBean.getDesc());
        Glide.with(context).load(datasBean.getEnvelopePic()).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dj!=null){
                    dj.dj(position,datasBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface dj{
        void dj(int pos,FoodBean.DataBean.DatasBean f);
    }
    dj dj;

    public void setDj(MyAdapter.dj dj) {
        this.dj = dj;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.v_image);
            title=itemView.findViewById(R.id.v_title);
        }
    }
}
