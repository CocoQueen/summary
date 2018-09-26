package com.coco.zxingdemo.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.coco.zxingdemo.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by ydx on 18-9-26.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private final ArrayList<Model> sampleData = App.getSampleData(20);

    // 用于创建控件
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parentViewGroup, int i) {

        // 获得列表项控件（LinearLayer对象）

        // list_item.xml布局文件中只包含一个<LinearLayer>标签，在该标签中包含
        // 了一个<TextView>标签
        //  item是LinearLayout对象
        View item = LayoutInflater.from(parentViewGroup.getContext()).inflate(
                R.layout.list_item, parentViewGroup, false);

        return new ViewHolder(item);

    }

    // 为控件设置数据
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
//  获取当前item中显示的数据
        final Model rowData = sampleData.get(position);

        //  设置要显示的数据
        viewHolder.textViewSample.setText(rowData.getSampleText());
        viewHolder.itemView.setTag(rowData);
    }

    @Override
    public int getItemCount() {

        return sampleData.size();
    }

    //  删除指定的Item
    public void removeData(int position) {
        sampleData.remove(position);
        //  通知RecyclerView控件某个Item已经被删除
        notifyItemRemoved(position);
    }

    //  在指定位置添加一个新的Item
    public void addItem(int positionToAdd) {
        sampleData.add(positionToAdd, new Model("新的列表项"));
        //  通知RecyclerView控件插入了某个Item
        notifyItemInserted(positionToAdd);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textViewSample;

        ViewHolder(View itemView) {
            super(itemView);
            textViewSample = (TextView) itemView.findViewById(R.id.textViewSample);
        }
    }

}
