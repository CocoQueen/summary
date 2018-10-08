package com.coco.zxingdemo.jifenshop;

import android.annotation.SuppressLint;

/**
 * Created by ydx on 18-9-29.
 */

public class TimeUtil {

    @SuppressLint("DefaultLocale")
    public static String getFormatTime(long time) {
        time = time / 1000;//总秒数
        int s = (int) (time % 60);//秒
        int m = (int) (time / 60);//分
        int h = (int) (time / 3600);//秒
        return String.format("%02d:%02d:%02d", h, m,s);

    }
}
