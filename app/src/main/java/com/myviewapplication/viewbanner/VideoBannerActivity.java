package com.myviewapplication.viewbanner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.myviewapplication.R;
import com.yc.video.config.ConstantKeys;
import com.yc.video.config.VideoPlayerConfig;
import com.yc.video.player.OnVideoStateListener;
import com.yc.video.player.VideoPlayer;
import com.yc.video.player.VideoViewManager;

import static com.google.android.exoplayer2.Player.STATE_ENDED;

public class VideoBannerActivity extends AppCompatActivity {
    private static final String TAG = "zhang";
    private static final String[] videoUrlList = {
            "http://vfx.mtime.cn/Video/2019/02/04/mp4/190204084208765161.mp4",
            "http://img2.wushang.com/img/2019/6/21/183001221883249959082951.mp4",
            "http://play.g3proxy.lecloud.com/vod/v2/MjUxLzE2LzgvbGV0di11dHMvMTQvdmVyXzAwXzIyLTExMDc2NDEzODctYXZjLTE5OTgxOS1hYWMtNDgwMDAtNTI2MTEwLTE3MDg3NjEzLWY1OGY2YzM1NjkwZTA2ZGFmYjg2MTVlYzc5MjEyZjU4LTE0OTg1NTc2ODY4MjMubXA0?b=259&mmsid=65565355&tm=1499247143&key=f0eadb4f30c404d49ff8ebad673d3742&platid=3&splatid=345&playid=0&tss=no&vtype=21&cvid=2026135183914&payff=0&pip=08cc52f8b09acd3eff8bf31688ddeced&format=0&sign=mb&dname=mobile&expect=1&tag=mobile&xformat=super"
    };

    ViewPager view_pager;
    private final OnVideoStateListener onVideoStateListener = new OnVideoStateListener() {
        @Override
        public void onPlayerStateChanged(int playerState) {

        }

        @Override
        public void onPlayStateChanged(int playState) {
            Log.d(TAG, "onPlayStateChanged: playState = " + playState);
            if (playState == ConstantKeys.CurrentState.STATE_BUFFERING_PLAYING) {
                int nextIndex = view_pager.getCurrentItem() == (view_pager.getChildCount() - 1) ? 0 : (view_pager.getCurrentItem() + 1);
                view_pager.setCurrentItem(nextIndex, true);
            }
        }
    };

    private final Player.EventListener eventListener = new Player.EventListener() {
        @Override
        public void onPlayerStateChanged(boolean playWhenReady, int playbackState) {
            if (playbackState == STATE_ENDED) {
                int nextIndex = view_pager.getCurrentItem() == (view_pager.getChildCount() - 1) ? 0 : (view_pager.getCurrentItem() + 1);
                view_pager.setCurrentItem(nextIndex, true);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_banner);

        VideoViewManager.setConfig(VideoPlayerConfig.newBuilder()
                //设置上下文
                .setContext(this)
                //调试的时候请打开日志，方便排错
                .setLogEnabled(true)
                //在移动环境下调用start()后是否继续播放，默认不继续播放
                .setPlayOnMobileNetwork(true)
                .build());
        view_pager = findViewById(R.id.view_pager);
        view_pager.setAdapter(new MyPagerAdapter(this, videoUrlList, onVideoStateListener));
        view_pager.setOffscreenPageLimit(6);
        view_pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Log.d(TAG, "onPageSelected: " + position);
                View view = view_pager.getChildAt(position);
                if (view != null) {
                    VideoPlayer mVideoPlayer = view.findViewById(R.id.video_player);
                    if (mVideoPlayer.getCurrentPlayState() == 5) {
                        //视频已放完的情况下，重新播放
                        mVideoPlayer.replay(true);
                    } else {
                        //开始播放
                        mVideoPlayer.start();
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        findViewById(R.id.viewFullScreen).setOnClickListener(v -> {
            finish();
            overridePendingTransition(R.anim.alpha_fade_in_quick, R.anim.alpha_fade_out_quick);
        });
    }

    @Override
    protected void onDestroy() {
        for (int i = 0; i < view_pager.getChildCount(); i++) {
            View view = view_pager.getChildAt(i);
            if (view != null) {
                VideoPlayer mVideoPlayer = view.findViewById(R.id.video_player);
                Log.d(TAG, "onDestroy: "+i+" : " + mVideoPlayer);
                mVideoPlayer.release();
            }
        }
        super.onDestroy();
    }
}