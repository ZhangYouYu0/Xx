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
import com.example.mode02.Bean.FoodDBean;

import java.util.ArrayList;

public class MyAdapterColl extends RecyclerView.Adapter<MyAdapterColl.ViewHolder> {
    Context context;
    ArrayList<FoodDBean> list;

    public MyAdapterColl(Context context, ArrayList<FoodDBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyAdapterColl.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(context).inflate(R.layout.e_item, parent, false);
        return new ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapterColl.ViewHolder holder, final int position) {
        final FoodDBean foodDBean = list.get(position);
        holder.title.setText(foodDBean.getDes());
        Glide.with(context).load(foodDBean.getPri()).apply(RequestOptions.bitmapTransform(new RoundedCorners(10))).into(holder.imageView);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dj!=null){
                    dj.dj(foodDBean,position);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(ca!=null){
                    ca.ca(foodDBean,position);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface ca{
        void ca(FoodDBean foodDBean,int pos);
    }

    ca ca;

    public void setCa(MyAdapterColl.ca ca) {
        this.ca = ca;
    }

    public interface dj{
        void dj(FoodDBean foodDBean,int pos);
    }
    dj dj;

    public void setDj(MyAdapterColl.dj dj) {
        this.dj = dj;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.image);
            title=itemView.findViewById(R.id.title);
        }
    }
}
