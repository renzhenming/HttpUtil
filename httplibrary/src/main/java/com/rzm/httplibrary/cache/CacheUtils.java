package com.rzm.httplibrary.cache;

import android.content.Context;

/**
 * Created by rzm on 2018/3/25.
 * 缓存引擎封装，可以方便的切换缓存方式
 */
public class CacheUtils {

    private static ICache mCache;

    //IHttpCache是否被初始化过
    private static boolean mHttpCacheInit = false;

    //单例的DCL方式
    private static volatile CacheUtils instance;

    private CacheUtils(){

    }

    public static CacheUtils getInstance(){
        if (instance == null){
            synchronized (CacheUtils.class){
                if (instance == null){
                    instance = new CacheUtils();
                }
            }
        }
        return instance;
    }

    /**
     * 初始化缓存引擎，在application的onCreate方法中设置一次即可
     * @param httpCache
     */
    public CacheUtils init(ICache httpCache){
        if (mHttpCacheInit){
            throw new IllegalStateException("HttpCache have already been init");
        }
        mCache = httpCache;
        mHttpCacheInit = true;
        return this;
    }

    /**
     * 切换缓存引擎
     * @param cache
     * @return
     */
    public CacheUtils changeCache(ICache cache){
        mCache = cache;
        return this;
    }

    public String getCache(Context context, String key) {
        if (mCache == null){
            throw new NullPointerException("to use cache function ,please init ICache first");
        }
        return mCache.getCache(context,key);
    }

    public boolean setCache(Context context, String key, String value){
        if (mCache == null){
            throw new NullPointerException("to use cache function ,please init ICache first");
        }
        return mCache.setCache(context,key,value);
    }

}
