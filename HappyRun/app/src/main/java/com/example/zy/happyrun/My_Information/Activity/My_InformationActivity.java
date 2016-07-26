package com.example.zy.happyrun.My_Information.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zy.happyrun.My_Information.Service.get_tianqi_thread;
import com.example.zy.happyrun.R;


import java.util.logging.LogRecord;


/**
 * Created by zy on 2016/7/19.
 */
public class My_InformationActivity extends AppCompatActivity{
    private get_tianqi_thread tianqi_thread;
    private TextView textView;
    private Handler handler;
    String jsonresult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_information);
        init();
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tianqi_thread=new get_tianqi_thread(jsonresult);
                tianqi_thread.start();
                if(jsonresult!=null){
                    Message message=new Message();
                    message.what=1;
                    handler.handleMessage(message);
                }
                else{
                    Message message=new Message();
                    message.what=2;
                    handler.handleMessage(message);
                }
            }
        });
        handler=new Handler(){

            @Override
            public void handleMessage(Message msg) {
                switch(msg.what){
                    case 1:{
                        Toast.makeText(My_InformationActivity.this,"jsonresult",Toast.LENGTH_LONG).show();
                      break;
                    }
                    case 2:{
                        Toast.makeText(My_InformationActivity.this,"出错了",Toast.LENGTH_LONG).show();
                        break;
                    }
                }
                super.handleMessage(msg);
            }
        };
    }

    private void init() {
        textView= (TextView) findViewById(R.id.test);

    }
}
