# HttpUtil

如何使用自己的网络框架？实现IHttp接口及其方法即可，demo中提供了一个OkHttp的实现示例供你参考
```
public class OkHttpEngine implements IHttp {
    void get(boolean cache, Context context, String url, Map<String, Object> params, ICallBack callBack);
    void post(boolean cache, Context context, String url, Map<String, Object> params, ICallBack callBack);
    void download(String url, ICallBack callBack);
    void upload(String path, String url, ICallBack callBack);
}
```

怎么把这个网络引擎设置给HttpUtil？通常在初始化application时
```
HttpUtils.initHttp(new OkHttpEngine());
```

然后你就可以在需要时发起请求了
```
HttpUtils.with(this)
         .cache(true)
         .get()
         .url("http://www.baidu.com")
         .addParams("iid", "6152551759")
         .addParams("aid", "7")
         .execute(new CallBackImpl<String>() {
               @Override
               public void onPreExecute() {
                   super.onPreExecute();
                   Toast.makeText(getApplicationContext(), "加载中", Toast.LENGTH_LONG).show();
               }
               
               @Override
               public void onError(final Exception e) {
                   Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
               }
                   
               @Override
               public void onSuccess(final String result) {
                  Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
               }
         });
```
