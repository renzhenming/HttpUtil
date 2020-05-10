package com.rzm.httplibrary.http;

import android.content.Context;

import java.util.Map;

/**
 * Created by rzm on 2017/8/20.
 */

public abstract class CallBackImpl<T>  implements ICallBack {
    @Override
    public void onPreExecute(Context context, Map<String, Object> params) {
        // 可以在这里添加公共的一些参数
        params.put("device_platform","android");
        onPreExecute();
    }

    @Override
    public void onDownloadProgress(long total, long current) {
        downloadProgress(total,current);
    }

    /**
     * 监听下载进度重写这个方法，不要重写onDownloadProgress
     * @param total
     * @param current
     */
    public void downloadProgress(long total, long current) {

    }

    @Override
    public void onUploadProgress(long total, long current) {
        uploadProgress(total,current);
    }

    /**
     * 监听上传进度重写这个方法，不要重写onUploadProgress
     * @param total
     * @param current
     */
    public void uploadProgress(long total, long current) {

    }

    @Override
    public void onSuccess(String result) {
        onSuccess(result);
    }

    //在执行成功数据解析之后再次执行success
    public abstract void onSuccess(T result);

    //添加参数之后开始执行，回调过去
    public void onPreExecute(){

    }
}
