package com.example.wanandroid.ui.home.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;


import com.example.wanandroid.R;
import com.example.wanandroid.base.BaseFragment;
import com.example.wanandroid.databinding.FragmentHomepageBinding;
import com.example.wanandroid.ui.home.ImageAdapter;


import java.util.Timer;
import java.util.TimerTask;

/**
 * @author dengfeng
 * @data 2023/4/20
 * @description
 */
public class HomePageFragment extends BaseFragment {

    FragmentHomepageBinding binding;


    private int index = 0;
    private final int AUTOPLAY = 2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomepageBinding.inflate(inflater);
        //初始化画廊
        initGallery();
       binding.myGallery.setOnItemClickListener((parent, view, position, id) -> Toast.makeText
                (getContext(), "当前位置position:" + id + "的图片被选中了", Toast.LENGTH_SHORT).show());
        return binding.getRoot();
    }

    @Override
    protected void initData() {
        //自动播放
        autoPlay();
    }



    //初始化画廊
    private void initGallery() {
        //用网络加载的方式未实现
        /*pictureAdapter = new PictureAdapter(this, mUrls);*/
       /* GlideAdapter glideAdapter = new GlideAdapter(this, mUrls);
        binding.myGallery.setAdapter(glideAdapter);*/
        //图片资源数组，本地图片资源
        int[] imageResIDs = new int[]{R.mipmap.w1, R.mipmap.w2, R.mipmap.w3};
        ImageAdapter adapter = new ImageAdapter(imageResIDs, getContext());
        binding.myGallery.setAdapter(adapter);
        /*binding.myGallery.setSpacing(20); //图片之间的间距*/
        binding.myGallery.setSelection((Integer.MAX_VALUE / 2) - (Integer.MAX_VALUE / 2) % imageResIDs.length);
        binding.myGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



    //在消息队列中实现对控件的更改，实现画廊的自动播放
    @SuppressLint("HandlerLeak")
    private final Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == AUTOPLAY) {
                binding.myGallery.setSelection(index);
            }
        }
    };

    private void autoPlay(){
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Message message = new Message();
                message.what = AUTOPLAY;
                index = binding.myGallery.getSelectedItemPosition();
                index++;
                handler.sendMessage(message);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task,3000,3000);
    }


}
