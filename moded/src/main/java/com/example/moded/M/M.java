package com.example.moded.M;

import com.example.moded.Back.Back;
import com.example.moded.Bean.FoodBean;
import com.example.moded.api.Apiservice;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class M implements M1 {
    @Override
    public void M1(final Back back) {
        new Retrofit.Builder()
                .baseUrl(Apiservice.BaseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //.client(new in())
                .build()
                .create(Apiservice.class)
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FoodBean foodBean) {
                        back.sj(foodBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        back.yc(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

//  private OkHttpClient in(){
//        return new OkHttpClient.Builder()
//                .addNetworkInterceptor(new ins())
//                .addInterceptor(new ins())
//                .build();
//  }

//    private class ins implements Interceptor {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            long l = System.nanoTime();
//            return null;
//        }
//    }
}
