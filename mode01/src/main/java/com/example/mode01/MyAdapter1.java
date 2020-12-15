package com.example.mode01;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mode01.Bean.FoodBean;

import java.util.ArrayList;

class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.ViewHolder> {
    Context context;
    ArrayList<FoodBean> list;

    public MyAdapter1(Context context, ArrayList<FoodBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.rl_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter1.ViewHolder holder, int position) {
        FoodBean s = list.get(position);
        holder.textView.setText(s.getDes());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.title_rl);
        }
    }
}
