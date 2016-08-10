package com.example.zy.happyrun.My_Information.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.baidu.location.LocationClient;
import com.example.zy.happyrun.BD_location.BD_location_entity;
import com.example.zy.happyrun.BD_location.BD_location_thread;
import com.example.zy.happyrun.My_Information.Entity.tianqi_back_entity;
import com.example.zy.happyrun.My_Information.Service.get_tianqi_thread;
import com.example.zy.happyrun.R;
import com.google.gson.Gson;


import java.util.logging.LogRecord;


/**
 * Created by zy on 2016/7/19.
 */
public class My_InformationActivity extends AppCompatActivity{
    public LocationClient mLocationClient;
    public BD_location_thread bd_location_thread;
    private TextView textView;
    private tianqi_back_entity  t_b_entity;
    Gson gson=new Gson();
    private BD_location_entity bd_location_entity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_information);
        init();
        mLocationClient=new LocationClient(getApplicationContext());
        bd_location_thread=new BD_location_thread(mLocationClient);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bd_location_thread.run();
                Log.d("thread","run开始");
                Parameters para = new Parameters();
                para.put("citypinyin", "handan");
                ApiStoreSDK.execute("http://apis.baidu.com/apistore/weatherservice/weather",
                        ApiStoreSDK.GET,
                        para,
                        new ApiCallBack() {
                            @Override
                            public void onSuccess(int status, String responseString) {
                                Log.i("sdkdemo", "onSuccess");
                                textView.setText(responseString);
                                t_b_entity=gson.fromJson(responseString, tianqi_back_entity.class);
                                textView.setText("adadadadadadaaad");
                            }

                            @Override
                            public void onComplete() {
                                Log.i("sdkdemo", "onComplete");
                            }

                            @Override
                            public void onError(int status, String responseString, Exception e) {
                                Log.i("sdkdemo", "onError, status: " + status);
                                Log.i("sdkdemo", "errMsg: " + (e == null ? "" : e.getMessage()));
                                textView.setText("错了");
                            }

                        });
//                tianqi_thread=new get_tianqi_thread(jsonresult);
//                tianqi_thread.start();
//                if(jsonresult!=null){
//                    Message message=new Message();
//                    message.what=1;
//                    handler.handleMessage(message);
//                }
//                else{
//                    Message message=new Message();
//                    message.what=2;
//                    handler.handleMessage(message);
//                }
            }
        });
    }

    private void init() {
        textView= (TextView) findViewById(R.id.test);

    }
}
