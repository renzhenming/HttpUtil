package com.rzm.httplibrary.http;

import android.content.Context;

import com.rzm.httplibrary.http.ICallBack;

import java.util.Map;

/**
 * Created by rzm on 2017/8/20.
 */

public interface IHttp {
    void get(boolean cache, Context context, String url, Map<String, Object> params, ICallBack callBack);
    void post(boolean cache, Context context, String url, Map<String, Object> params, ICallBack callBack);
    void download(String url, ICallBack callBack);
    void upload(String path, String url, ICallBack callBack);

    // https添加安全证书
    // https 添加证书
}
