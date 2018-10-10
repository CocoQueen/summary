package com.coco.zxingdemo.comment;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.coco.zxingdemo.R;

import java.util.List;

/**
 * Created by ydx on 18-10-10.
 */

public class CommentAdapter extends BaseAdapter {

    Context context;
    List<CommentBean> list;

    public CommentAdapter(Context context, List<CommentBean> list) {
        this.context = context;
        this.list = list;
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_comment, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.mTv_name.setText(list.get(position).getName());
        holder.mTv_content.setText(list.get(position).getContent());
        return convertView;
    }

    public void addComment(CommentBean bean) {
        list.add(bean);
        notifyDataSetChanged();
    }

    public static class ViewHolder {
        public View rootView;
        public TextView mTv_name;
        public TextView mTv_content;

        public ViewHolder(View rootView) {
            this.rootView = rootView;
            this.mTv_name = (TextView) rootView.findViewById(R.id.mTv_name);
            this.mTv_content = (TextView) rootView.findViewById(R.id.mTv_content);
        }

    }
}
