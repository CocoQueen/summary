package com.coco.zxingdemo.handler;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.coco.zxingdemo.R;

public class HandlerThreadActivity extends AppCompatActivity {

    private HandlerThread thread;
    private TextView textView;

    private Handler handler;

    private static final String TAG = "HandlerThreadActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = new TextView(this);
        textView.setText("handler thread");
        setContentView(textView);

        thread=new HandlerThread("handler thread");//避免多线程并发造成空指针问题
        thread.start();

        handler=new Handler(thread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Log.e(TAG, "currentThread: "+Thread.currentThread());
            }
        };
        handler.sendEmptyMessage(1);
    }
}
