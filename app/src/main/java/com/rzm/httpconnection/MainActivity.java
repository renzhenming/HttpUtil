package com.rzm.httpconnection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.rzm.httplibrary.http.HttpUtils;
import com.rzm.httplibrary.http.CallBackImpl;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void get(View view) {
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
    }
}
