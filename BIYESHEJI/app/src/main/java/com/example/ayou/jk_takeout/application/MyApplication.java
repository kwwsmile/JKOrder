package com.example.ayou.jk_takeout.application;

import android.app.Application;

import cn.smssdk.SMSSDK;

/**
 * Created by kongweiwei on 2017/4/23.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {

        super.onCreate();

        //短信验证
        SMSSDK.initSDK(this,"1d563211022c2","c146ea2741657c97f9ee495a23f32b54");
    }

}
