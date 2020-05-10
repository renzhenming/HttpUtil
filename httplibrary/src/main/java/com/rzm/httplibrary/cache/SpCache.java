package com.rzm.httplibrary.cache;

import android.content.Context;

import com.rzm.httplibrary.cache.ICache;

/**
 * Created by renzhenming on 2018/3/25.
 *
 * 仅限进行网络请求的缓存，
 * 针对的是以url为key以服务器数据为value的接口返回数据
 */

public class SpCache implements ICache {

    @Override
    public String getCache(Context context, String key) {
        return null;
    }

    @Override
    public boolean setCache(Context context, String key, String value) {
        return false;
    }

    @Override
    public boolean deleteCache(Context context, String key) {
        return false;
    }

    @Override
    public boolean deleteAllCache(Context context) {
        return false;
    }

    @Override
    public boolean updateCache(Context context, String newValue, String key, String value) {
        return false;
    }
}
