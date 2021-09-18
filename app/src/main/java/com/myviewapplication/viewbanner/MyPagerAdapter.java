package com.myviewapplication.viewbanner;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.myviewapplication.R;
import com.yc.video.player.OnVideoStateListener;
import com.yc.video.player.VideoPlayer;
import com.yc.video.ui.view.BasisVideoController;

/**
 * 使用YCVideoPlayer
 */
public class MyPagerAdapter extends PagerAdapter {
    private final Context context;
    private final String[] videoUrlList;
    private final OnVideoStateListener onVideoStateListener;
    private boolean first = true;

    public MyPagerAdapter(Context context, String[] videoUrlList, OnVideoStateListener onVideoStateListener) {
        this.context = context;
        this.videoUrlList = videoUrlList;
        this.onVideoStateListener = onVideoStateListener;
    }

    @Override
    public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.setPrimaryItem(container, position, object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_video_banner, container, false);
        VideoPlayer mVideoPlayer = view.findViewById(R.id.video_player);
        TextView tvPosition = view.findViewById(R.id.tvPosition);
        ImageView img = view.findViewById(R.id.img);

        BasisVideoController controller = new BasisVideoController(context);
        //移除所有控件
        controller.removeAllControlComponent();
        //是否开启根据屏幕方向进入/退出全屏
        controller.setEnableOrientation(false);
        //设置控制器
        mVideoPlayer.setController(controller);
        //设置视频播放链接地址
        mVideoPlayer.setUrl(videoUrlList[position]);
        mVideoPlayer.setOnStateChangeListener(onVideoStateListener);
        if (first) {
            //开始播放
            mVideoPlayer.start();
//            mVideoPlayer.postDelayed(mVideoPlayer::start, 300);
            first = false;
        }
        Log.d("zhang", "instantiateItem: "+position+" : " + mVideoPlayer);

        tvPosition.setText(String.valueOf(position));
        container.addView(view);
        return view;
    }


    @Override
    public int getCount() {
        return videoUrlList.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
