package com.example.asus.xiaoshixunlianxi.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.asus.xiaoshixunlianxi.MainActivity;
import com.example.asus.xiaoshixunlianxi.R;
import com.example.asus.xiaoshixunlianxi.adapter.MyListAdapter;
import com.example.asus.xiaoshixunlianxi.adapter.MyPageAdapter;
import com.example.asus.xiaoshixunlianxi.bean.NewsBean;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class StoryFragment extends Fragment {


    private ViewPager vp;
    private GridView gv;
    private ListView lv;
    private List<NewsBean.ResultBean.ListBean> list;
    int a = 0;
    private String[] picUrl = {
            "http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg",
            "http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg",
            "http://img.zcool.cn/community/01c8dc56e1428e6ac72531cbaa5f2c.jpg",
            "http://img.zcool.cn/community/01fda356640b706ac725b2c8b99b08.jpg",
            "http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg",
            "http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg"
    };
    public List<ImageView> imgList;
    private Handler handler = new Handler() {


        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String jsonString = (String) msg.obj;
            list = new Gson().fromJson(jsonString, NewsBean.class).getResult().getList();
            initAdapter();
        }

        private void initAdapter() {
            MyListAdapter myListAdapter = new MyListAdapter(list, getActivity());
            lv.setAdapter(myListAdapter);
        }
    };
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (a < 5) {
                ImageView imageView = new ImageView(getActivity());
                Picasso.with(getActivity()).load(picUrl[a]).into(imageView);
                imgList.add(imageView);
                a++;
            } else {
                a = 0;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_story, container, false);
        initView(inflate);
        initData();
        return inflate;
    }

    private void initData() {
//        handler.postDelayed(runnable, 2000);
        List<String> tagList = new ArrayList<>();
        tagList.add("最新");
        tagList.add("叫早");
        tagList.add("哄睡");
        tagList.add("全部");
        gv.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, tagList));

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("http://192.168.1.19/json/gaoji.json").build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {


            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                handler.obtainMessage(1, response.body().string()).sendToTarget();
            }
        });

    }

    private void initView(View inflate) {
        vp = (ViewPager) inflate.findViewById(R.id.vp);
        gv = (GridView) inflate.findViewById(R.id.gv);
        lv = (ListView) inflate.findViewById(R.id.lv);
    }
}
