package com.coco.zxingdemo.recyclerview;

import java.util.ArrayList;

/**
 * Created by ydx on 18-9-26.
 */

public class App {
    //  获取要显示的数据（初始化数据）
    public static ArrayList<Model> getSampleData(int size) {
        ArrayList<Model> sampleData = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            //  每一项数据后面都有相应的序号
            sampleData.add(new Model("新的列表项<" + i + ">"));
        }

        return sampleData;
    }
}
