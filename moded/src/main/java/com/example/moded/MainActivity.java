package com.example.moded;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.github.dfqin.grantor.PermissionListener;
import com.github.dfqin.grantor.PermissionsUtil;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    private Button bnt;
    private ImageView image;
    private TextView title;
    String [] stu = {"二","一","零"};
    int count = 0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what==1){
                title.setText(stu[count]);
                count++;
                if(count==3){
                    timer.cancel();
                    startActivity(new Intent(MainActivity.this,Main2Activity.class));
                }
            }
        }
    };
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        bnt = (Button) findViewById(R.id.bnt);
        image = (ImageView) findViewById(R.id.image);
        title = (TextView) findViewById(R.id.title);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim);
        timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(1);
            }
        };
        timer.schedule(task,1000,1000);
        image.startAnimation(animation);
        bnt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bnt:
                startActivity(new Intent(MainActivity.this,Main2Activity.class));
                timer.cancel();
                break;
        }
    }
}
