package com.example.ceshi1.Api;

import com.example.ceshi1.Bean.FoodBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String BaseUrl="https://www.wanandroid.com/";
    @GET("project/list/1/json")
    Observable<FoodBean> get();
}
