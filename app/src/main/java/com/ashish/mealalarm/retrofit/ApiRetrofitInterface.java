package com.ashish.mealalarm.retrofit;

import com.ashish.mealalarm.models.DietResponseModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;


/**
 * Created by Ashish Tiwari on 28,April,2021
 */
public interface ApiRetrofitInterface {
    @Headers({"Accept: application/json"})
    @GET("dummy/")
    Call<DietResponseModel> dummyApi();
}
