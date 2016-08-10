package com.example.zy.happyrun.My_Information;

import android.app.Application;

import com.baidu.apistore.sdk.ApiStoreSDK;

/**
 * Created by zy on 2016/8/8.
 */
public class My_Information_Applycation extends Application {
    @Override
    public void onCreate() {
        ApiStoreSDK.init(this,"b14c8677c9693816484adc4ffe1be484");
        super.onCreate();

    }
}
