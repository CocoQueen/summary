package com.coco.zxingdemo.webview;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.coco.zxingdemo.R;

public class WebActivity extends AppCompatActivity implements JsBridge{

    private WebView web;
    private static final String TAG = "WebActivity";
    private TextView tv;
    private EditText edit;
    private Button btn;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);
        web = findViewById(R.id.web);
        tv = findViewById(R.id.tv);

        edit = findViewById(R.id.edit);
        btn = findViewById(R.id.btn);

        WebSettings settings = web.getSettings();

        settings.setJavaScriptEnabled(true);//允许webview加载js

        web.addJavascriptInterface(new JsInterface(this),"inter");//给webview添加js接口

        web.loadUrl("file:///android_asset/index.html");

        Log.d(TAG, "onCreate: ");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = edit.getText().toString().trim();
                web.loadUrl("javascript:if(window.remote){window.remote('"+s+"')}");
            }
        });

        web.setWebContentsDebuggingEnabled(true);//打开允许调试的开关

    }

    private Handler handler=new Handler();

    @Override
    public void setTextValue(final String value) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                tv.setText(value);
            }
        });
    }
}
