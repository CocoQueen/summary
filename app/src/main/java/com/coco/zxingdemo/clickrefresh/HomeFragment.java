package com.coco.zxingdemo.clickrefresh;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coco.zxingdemo.R;

/**
 * Created by ydx on 18-9-19.
 */

public class HomeFragment extends Fragment {
    View mainView = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (mainView == null) {
            mainView = inflater.inflate(R.layout.fragment_main, container, false);
        }
        return mainView;
    }

    public void ScrollToTop() {//在这里面写刷新数据的方法
        Toast.makeText(getActivity(), "滑动到顶部并开始刷新", Toast.LENGTH_SHORT).show();
    }
}
