package com.coco.zxingdemo.handler;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.coco.zxingdemo.R;

public class CustomHandlerActivity extends AppCompatActivity {

    private static final String TAG = "CustomHandlerActivity";
    private MyThread thread;
    private Handler handler2;

//    @SuppressLint("HandlerLeak")
//    private Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {//此方法中不能进行耗时操作，否则的话会出现卡死的情况
//            super.handleMessage(msg);
//            Log.e(TAG, "ui thread" + Thread.currentThread());
//        }
//    };

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView textView = new TextView(this);
        textView.setText("custom handler");
        setContentView(textView);
        thread = new MyThread();
        thread.start();
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        thread.handler.sendEmptyMessage(1);
//        handler.sendEmptyMessage(1);
        handler2 = new Handler(thread.looper){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Log.e(TAG, "handleMessage: "+msg);
            }
        };
        handler2.sendEmptyMessage(1);
    }

    class MyThread extends Thread {

        public Handler handler;

        public Looper looper;

        @SuppressLint("HandlerLeak")
        @Override
        public void run() {
            Looper.prepare();
            looper=Looper.myLooper();
            handler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    Log.e(TAG, "currentThread: " + Thread.currentThread());
                }
            };
            Looper.loop();
        }
    }
}
