package com.example.administrator.qgzs;

import android.app.Application;

import com.example.administrator.qgzs.utils.RetrofitUtils;

/**
 * Created by Administrator on 2017/2/11 0011.
 */

public class MainAPP extends Application {

    public static MainAPP app;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化retrofitUtils
        RetrofitUtils.getInstance().initOkHttp(this);
        app=this;
    }

}
