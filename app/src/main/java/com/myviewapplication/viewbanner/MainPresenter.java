package com.myviewapplication.viewbanner;

import android.os.Handler;
import android.os.Message;

public class MainPresenter {
    public final static int MSG_SHOW_TIPS = 0x01;

    private final IMainView mMainView;

    private MainHandler mMainHandler;

    private boolean tipsIsShowed = true;

    private final Runnable tipsShowRunnable = new Runnable() {

        @Override
        public void run() {
            mMainHandler.obtainMessage(MSG_SHOW_TIPS).sendToTarget();
        }
    };

    public MainPresenter(IMainView view) {
        mMainView = view;
        mMainHandler = new MainHandler();
    }

    /**
     * <无操作时开始计时>
     */
    public void startTipsTimer() {
        mMainHandler.postDelayed(tipsShowRunnable, 5000);
    }

    /**
     * <结束当前计时 重置计时>
     */
    public void endTipsTimer() {
        mMainHandler.removeCallbacks(tipsShowRunnable);
    }

    public void resetTipsTimer() {
        tipsIsShowed = false;
        mMainHandler.removeCallbacks(tipsShowRunnable);
        mMainHandler.postDelayed(tipsShowRunnable, 5000);
    }

    public class MainHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == MSG_SHOW_TIPS) {
                mMainView.showTipsView();
                tipsIsShowed = true;
                //屏保显示
            }
        }
    }
}