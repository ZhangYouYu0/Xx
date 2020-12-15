package com.example.ceshi1.M;

import com.example.ceshi1.Api.ApiService;
import com.example.ceshi1.Back.Back;
import com.example.ceshi1.Bean.FoodBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
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
                .build()
                .create(ApiService.class)
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
}
