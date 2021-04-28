package com.ashish.mealalarm.retrofit;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public interface RestServices {
    String BASE_URL = "http://naviasmart.health/";
    ExecutorService EXECUTOR_SERVICE = Executors.newCachedThreadPool(new ThreadFactory() {
        final AtomicInteger atomicInteger = new AtomicInteger();

        @Override
        public Thread newThread(Runnable task) {
            return new Thread(task, "Retrofit-Thread-" + atomicInteger.get());
        }
    });

    OkHttpClient client = getClient();

    static OkHttpClient getClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(15);
        httpClient.dispatcher(dispatcher);
        return httpClient.build();
    }


    ApiRetrofitInterface API_SERVICE = new Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .callbackExecutor(EXECUTOR_SERVICE)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiRetrofitInterface.class);
}
