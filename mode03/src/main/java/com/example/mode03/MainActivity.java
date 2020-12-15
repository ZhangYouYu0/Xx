package com.example.mode03;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView image;
    private Button bnt_xz;
    private ProgressBar pro_main;
    private TextView et_count;
    private File file = Environment.getExternalStorageDirectory();
    private String apkUrl = "https://alissl.ucdl.pp.uc.cn/fs08/2017/05/02/7/106_64d3e3f76babc7bce131650c1c21350d.apk";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        image = (ImageView) findViewById(R.id.image);
        bnt_xz = (Button) findViewById(R.id.bnt_xz);
        pro_main = (ProgressBar) findViewById(R.id.pro_main);
        et_count = (TextView) findViewById(R.id.et_count);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.circleCrop();
        Glide.with(this).load(R.drawable.a0).apply(requestOptions).into(image);
        bnt_xz.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bnt_xz:
                Request request = new Request.Builder()
                        .url(apkUrl)
                        .build();
                new OkHttpClient.Builder()
                        .build()
                        .newCall(request)
                        .enqueue(new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {

                            }

                            @Override
                            public void onResponse(Call call, Response response) throws IOException {
                                InputStream inputStream = response.body().byteStream();
                                long contentLength = response.body().contentLength();
                                sv(inputStream,contentLength,file+"/con.apk");
                            }
                        });
                break;
        }
    }

    private void sv(InputStream inputStream,final long contentLength, String s) {
        pro_main.setMax((int) contentLength);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(s));
            int len = 0;
            int count = 0;
            byte[] bytes = new byte[1024 * 10];
            while ((len = inputStream.read(bytes))!=-1){
                fileOutputStream.write(bytes,0,len);
                count +=len;
                final int filecount = count;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        pro_main.setProgress(filecount);
                        et_count.setText(Math.ceil(filecount*100f /contentLength)+"%");
                    }
                });
            }
            inputStream.close();
            fileOutputStream.close();
            in(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void in(String s) {
        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
        intent.putExtra("pic",s);
        startActivity(intent);
    }
}
