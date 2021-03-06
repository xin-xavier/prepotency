package com.example.prepotency.app.http.helper;

import com.example.prepotency.app.http.url.DNSConfig;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

class RetrofitFactory {

    static Retrofit apiFactory() {
        return gsonFactory()
                .baseUrl(DNSConfig.getInstance().getApiUrl())
                .build();
    }

    static Retrofit apiServerFactory() {
        return gsonFactory()
                .baseUrl(DNSConfig.getInstance().getApiServerUrl())
                .build();
    }

    static Retrofit scalarsApiServerFactory() {
        return scalarsFactory()
                .baseUrl(DNSConfig.getInstance().getApiServerUrl())
                .build();
    }

    static Retrofit liveApiFactory() {
        return gsonFactory()
                .baseUrl(DNSConfig.getInstance().getLiveApiUrl())
                .build();
    }

    private static Retrofit.Builder gsonFactory() {
        return new Retrofit.Builder()
                .client(GameHttpClient.getInstance().getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

    private static Retrofit.Builder scalarsFactory() {
        return new Retrofit.Builder()
                .client(GameHttpClient.getInstance().getOkHttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    }

}
