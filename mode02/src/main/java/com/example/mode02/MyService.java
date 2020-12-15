package com.example.mode02;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.IBinder;

import com.example.mode02.Bean.SerBean;

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
    private File file = Environment.getExternalStorageDirectory();
    private String apkUrl = "https://alissl.ucdl.pp.uc.cn/fs08/2017/05/02/7/106_64d3e3f76babc7bce131650c1c21350d.apk";
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        onStar();
        return super.onStartCommand(intent, flags, startId);
    }

    private void onStar() {
        Request build = new Request.Builder()
                .url(apkUrl)
                .build();
        new OkHttpClient.Builder()
                .build()
                .newCall(build)
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
    }

    private void sv(InputStream inputStream, long contentLength, String path) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(path));
            int len = 0;
            int count = 0;
            byte[] bytes = new byte[1024 * 10];
            while ((len=inputStream.read(bytes))!=-1){
                fileOutputStream.write(bytes,0,len);
                count+=len;
                EventBus.getDefault().post(new SerBean(count,contentLength));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
