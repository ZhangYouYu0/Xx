package com.example.ceshi1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class gb extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String a = intent.getStringExtra("a"); //得到数据
        Toast.makeText(context,a,Toast.LENGTH_SHORT).show();//吐司
    }
}
