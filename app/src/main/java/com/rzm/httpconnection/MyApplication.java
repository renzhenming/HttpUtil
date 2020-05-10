package com.rzm.httpconnection;

import android.app.Application;

import com.rzm.httpconnection.engine.okhttp.OkHttpEngine;
import com.rzm.httplibrary.cache.CacheUtils;
import com.rzm.httplibrary.http.HttpUtils;
import com.rzm.httplibrary.cache.SpCache;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .writeTimeout(10000L,TimeUnit.MILLISECONDS)
                .build();
        OkHttpEngine okHttpEngine = OkHttpEngine.initClient(okHttpClient);

        HttpUtils.initHttp(okHttpEngine);
        CacheUtils.getInstance().init(new SpCache());
    }
}
