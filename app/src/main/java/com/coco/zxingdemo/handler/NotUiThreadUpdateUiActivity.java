package com.coco.zxingdemo.handler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.coco.zxingdemo.R;

public class NotUiThreadUpdateUiActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_ui_thread_update_ui);

        textView = findViewById(R.id.tv);

        new Thread(new Runnable() {
            @Override
            public void run() {

//                try {
//                    Thread.sleep(2000);
                    textView.setText("ok");
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }


            }
        }).start();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
