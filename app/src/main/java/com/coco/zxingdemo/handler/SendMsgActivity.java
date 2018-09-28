package com.coco.zxingdemo.handler;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.coco.zxingdemo.R;

public class SendMsgActivity extends AppCompatActivity implements View.OnClickListener {

    private Handler threadHandler;

    private static final String TAG = "SendMsgActivity";

    //创建主线程的handler
    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Message message=new Message();
            Log.e(TAG, "handleMessage:main handler");
            //向子线程发送消息
            threadHandler.sendMessageDelayed(message,1000);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_msg);

        Button send = findViewById(R.id.send);
        Button getMsg = findViewById(R.id.stop);

        send.setOnClickListener(this);
        getMsg.setOnClickListener(this);

        HandlerThread thread=new HandlerThread("handler thread");
        thread.start();

        //创建子线程的handler
        threadHandler=new Handler(thread.getLooper()){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Message message=new Message();
                Log.e(TAG, "handleMessage:thread handler");
                //向主线程发送消息
                handler.sendMessageDelayed(message,1000);
            }
        };

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send:
                handler.sendEmptyMessage(1);
                break;
            case R.id.stop:
                //该参数为Message的标识，第一次发送的标识为1；而后因Message message=new Message（）；
                // 默认参数为0；以后的Message标识均为0，故remove“0”；send键：再次点击，会开启一个
                // 新的主线程和子线程通信循环。多个循环打印都在logCat上，自然就混乱随机了
                handler.removeMessages(0);
                break;
        }
    }
}
