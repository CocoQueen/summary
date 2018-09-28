package com.coco.zxingdemo.handler;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.coco.zxingdemo.R;

public class HandlerUpdateActivity extends AppCompatActivity {

    private TextView tv;

    @SuppressLint("HandlerLeak")
    private Handler handler1 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    @SuppressLint("HandlerLeak")
    private Handler handler2 = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            tv.setText("handler sendmessage");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler_update);

        tv = findViewById(R.id.textView);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
//                    handle1();
//                    handle2();
//                    updateUi();
                    viewUi();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    //handler post
    private void handle1() {
        handler1.post(new Runnable() {
            @Override
            public void run() {
                tv.setText("handler post");
            }
        });
    }

    //handler sendmessage
    private void handle2() {
        handler2.sendEmptyMessage(1);
    }

    //runOnUiThread
    private void updateUi() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv.setText("runOnUiThread");
            }
        });
    }

    //view ui
    private void viewUi() {
        tv.post(new Runnable() {
            @Override
            public void run() {
                tv.setText("view ui");
            }
        });

    }

}
