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
import com.example.moded.Bean.FoodDbBean;

import java.util.ArrayList;

class MyAdapterColl extends RecyclerView.Adapter<MyAdapterColl.ViewHolder> {
    Context context;
    ArrayList<FoodDbBean> list;

    public MyAdapterColl(Context context, ArrayList<FoodDbBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapterColl.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.rl1_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterColl.ViewHolder holder, int position) {
        FoodDbBean foodDbBean = list.get(position);
        holder.title.setText(foodDbBean.getDes());
        Glide.with(context).load(foodDbBean.getPic()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return list.size();
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
