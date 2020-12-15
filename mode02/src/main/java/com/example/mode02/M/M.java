package com.example.mode02.M;

import android.util.Log;

import com.example.mode02.Back.Back;
import com.example.mode02.Bean.FooBean;
import com.example.mode02.api.ApiService;

import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class M implements M1 {
    @Override
    public void M1(final Back back) {
        new Retrofit.Builder()
                .baseUrl(ApiService.BaseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(in())
                .build()
                .create(ApiService.class)
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FooBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FooBean fooBean) {
                        back.sj(fooBean);
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

    private OkHttpClient in(){
        return new OkHttpClient.Builder()
                .addInterceptor(new is())
                .addNetworkInterceptor(new is())
                .build();
    }

//    private class in implements Interceptor {
//        @Override
//        public Response intercept(Chain chain) throws IOException {
//            Request request = chain.request();
//            long l = System.nanoTime();
//            Log.e("TAG", "intercept: "+request.url() +chain.connection()+request.headers());
//            Response proceed = chain.proceed(request);
//            long l1 = System.nanoTime();
//            Log.e("TAG", "intercept: "+proceed.request().url()+(l-l1)/1e6d+proceed.headers() );
//            return proceed;
//        }
//    }

    private class is implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            long l = System.nanoTime();
            Request request = chain.request();
            Log.e("TAG", "intercept: "+request.url() + chain.connection()+request.headers());
            Response proceed = chain.proceed(request);
            long l1 = System.nanoTime();
            Log.e("TAG", "intercept: "+proceed.request().url()+(l-l1)/1e6d+proceed.headers());
            return proceed;
        }
    }
}
