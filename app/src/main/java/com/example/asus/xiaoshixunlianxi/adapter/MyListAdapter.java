package com.example.asus.xiaoshixunlianxi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.xiaoshixunlianxi.R;
import com.example.asus.xiaoshixunlianxi.bean.NewsBean;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ASUS on 2018/3/1.
 */

public class MyListAdapter extends BaseAdapter {
    private List<NewsBean.ResultBean.ListBean> data;
    private Context context;

    public MyListAdapter(List<NewsBean.ResultBean.ListBean> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (view == null) {
            holder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.liay, null);
            holder.tv_title = view.findViewById(R.id.tv_title);
            holder.img_title = view.findViewById(R.id.img_title);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.tv_title.setText(data.get(i).getTitle());
        Picasso.with(context).load(data.get(i).getFirstImg()).into(holder.img_title);
        return view;
    }

    class ViewHolder {
        TextView tv_title;
        ImageView img_title;
    }
}
