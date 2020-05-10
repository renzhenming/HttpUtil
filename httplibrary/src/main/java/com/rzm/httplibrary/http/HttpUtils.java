package com.rzm.httplibrary.http;

import android.content.Context;
import android.text.TextUtils;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rzm on 2017/8/20.
 */
public class HttpUtils{

    private String mUrl;
    private String mPath;

    //默认请求类型为get方式
    private int mType = GET_TYPE;
    private static final int POST_TYPE = 0x0011;
    private static final int GET_TYPE = 0x0022;
    private static final int DOWNLOAD_TYPE = 0x0033;
    private static final int UPLOAD_TYPE = 0x0044;

    private Context mContext;

    private static IHttp mHttp;

    //IHttpEngine是否被初始化过
    private static boolean mHttpInit = false;

    private Map<String,Object> mParams;

    //默认未设置缓存
    private boolean mCache = false;

    private HttpUtils(Context context){
        mContext = context;
        mParams = new HashMap<>();
    }

    private HttpUtils(){

    }

    public static HttpUtils with(Context context){
        return new HttpUtils(context);
    }

    public HttpUtils post(){
        mType = POST_TYPE;
        return this;
    }

    public HttpUtils get(){
        mType = GET_TYPE;
        return this;
    }

    public HttpUtils download(){
        mType = DOWNLOAD_TYPE;
        return this;
    }

    public HttpUtils upload(){
        mType = UPLOAD_TYPE;
        return this;
    }

    /**
     * 添加参数
     * @param key
     * @param value
     * @return
     */
    public HttpUtils addParams(String key, Object value){
        mParams.put(key,value);
        return this;
    }

    public HttpUtils addParams(Map<String,Object> params){
        mParams.putAll(params);
        return this;
    }

    /**
     * 网络请求的url
     * @param url
     * @return
     */
    public HttpUtils url(String url){
        mUrl = url;
        return this;
    }

    /**
     * 上传文件的本地地址
     * @param path
     * @return
     */
    public HttpUtils path(String path){
        mPath = path;
        return this;
    }

    /**
     * 设置是否添加缓存,如果设置为true，要使用缓存功能，一定要设置缓存引擎
     */
    public HttpUtils cache(boolean cache){
        mCache = cache;
        return this;
    }

    /**
     * 初始化网络引擎
     * 在application中初始化一次
     * @param http
     */
    public static void initHttp(IHttp http){
        if (mHttpInit){
            throw new IllegalStateException("HttpEngine have already been init");
        }
        mHttp = http;
        mHttpInit = true;
    }

    public HttpUtils changeHttp(IHttp http){
        mHttp = http;
        return this;
    }

    /**
     * 执行请求
     * @param url
     * @param params
     * @param callBack
     */
    private void get(Context context, String url, Map<String, Object> params, ICallBack callBack) {
        mHttp.get(mCache,context,url,params,callBack);
    }

    private void post(Context context, String url, Map<String, Object> params, ICallBack callBack) {
        mHttp.post(mCache,context,url,params,callBack);
    }

    private void download(String url, ICallBack callBack) {
        mHttp.download(url,callBack);
    }

    private void upload(String path, String url, ICallBack callBack) {
        mHttp.upload(path,url,callBack);
    }

    /**
     * 执行 设置回调
     * @return
     */
    public HttpUtils execute(ICallBack callBack){
        if (callBack == null){
            callBack = ICallBack.DEFAULT_CALL_BACK;
        }

        callBack.onPreExecute(mContext,mParams);

        if (TextUtils.isEmpty(mUrl)){
            throw new NullPointerException("you have not set request url,use url(xxx) to set");
        }

        //判断执行方法
        if (mType == POST_TYPE){
            post(mContext,mUrl,mParams,callBack);
        }
        if (mType == GET_TYPE){
            get(mContext,mUrl,mParams,callBack);
        }
        if (mType == DOWNLOAD_TYPE){
            download(mUrl,callBack);
        }
        if (mType == UPLOAD_TYPE){
            if (mPath == null){
                throw new NullPointerException("you have not set upload file path ,use path(xxx) to set");
            }
            upload(mPath,mUrl,callBack);
        }
        return this;
    }

    //--------------------------------  供外界调用 --------------------------------//

    /**
     * 拼接参数（打印）
     */
    public static String printUrlWithParams(String url, Map<String, Object> params) {
        if (params == null || params.size() <= 0) {
            return url;
        }

        StringBuffer stringBuffer = new StringBuffer(url);
        if (!url.contains("?")) {
            stringBuffer.append("?");
        } else {
            if (!url.endsWith("?")) {
                stringBuffer.append("&");
            }
        }

        for (Map.Entry<String, Object> entry : params.entrySet()) {
            stringBuffer.append(entry.getKey() + "=" + entry.getValue() + "&");
        }

        stringBuffer.deleteCharAt(stringBuffer.length() - 1);

        return stringBuffer.toString();
    }

    /**
     * 解析一个类上面的class信息
     */
    public static Class<?> analysisClazzInfo(Object object) {
        Type genType = object.getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        return (Class<?>) params[0];
    }

}
