package com.coco.zxingdemo.webview;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Created by ydx on 18-9-20.
 */

public class JsInterface {
    private static final String TAG = "JsInterface";

    private JsBridge jsBridge;

    public JsInterface(JsBridge jsBridge) {
        this.jsBridge = jsBridge;
    }

    /**
     * 这个方法不在主线程中执行
     * @param value
     */
    @JavascriptInterface//注解必须要加
    public void setValue(String value) {
        jsBridge.setTextValue(value);
        Log.d(TAG, "setValue: " + value);
    }
}
