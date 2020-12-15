package com.example.mode02.api;

import com.example.mode02.Bean.FooBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {
    String BaseUrl = "https://www.wanandroid.com/";

    @GET("project/list/1/json")
    Observable<FooBean> get();
}
