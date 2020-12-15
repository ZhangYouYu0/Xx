package com.example.moded.api;

import com.example.moded.Bean.FoodBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Apiservice {
    String BaseUrl="https://www.wanandroid.com/";

    @GET("project/list/1/json?cid=294")
    Observable<FoodBean> get();
}
