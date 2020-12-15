package com.example.ceshi1;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;

import com.example.ceshi1.Fragment.SerBwan;

import org.greenrobot.eventbus.EventBus;

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

public class MyService extends Service {

    //安装下载的安装包地址
    private String apkUrl = "https://alissl.ucdl.pp.uc.cn/fs08/2017/05/02/7/106_64d3e3f76babc7bce131650c1c21350d.apk"; //设置路径
    private File e = Environment.getExternalStorageDirectory(); //sd卡路径
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onstart(); //启动设置的方法
        return super.onStartCommand(intent, flags, startId);
    }

    private void onstart() {
        Request reuqest = new Request.Builder()
                .url(apkUrl) //添加路径
                .build();

        new OkHttpClient.Builder()
                .build()
                .newCall(reuqest)
                .enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {

                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        InputStream inputStream = response.body().byteStream(); //得到流
                        long contentLength = response.body().contentLength(); //得到长度
                        sv(inputStream,contentLength,e+"/e.apk"); //创建方法
                    }
                });
    }

    private void sv(InputStream inputStream, long contentLength, String s) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(s)); //创建文件读取流
            int lne = 0;
            int count = 0;
            byte[] bytes = new byte[1024 * 10];
            while ((lne = inputStream.read(bytes))!=-1){
                fileOutputStream.write(bytes,0,lne); //逐个添加
                count+=lne;
                EventBus.getDefault().post(new SerBwan(count,contentLength)); //EventBus传值
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
