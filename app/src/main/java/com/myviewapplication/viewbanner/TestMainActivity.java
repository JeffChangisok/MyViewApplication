package com.myviewapplication.viewbanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.myviewapplication.R;

public class TestMainActivity extends AppCompatActivity implements IMainView {
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_main);
        mainPresenter = new MainPresenter(this);
        findViewById(R.id.btnScreenSaver).setOnClickListener(v -> showTipsView());
    }

    @Override
    protected void onPause() {
        mainPresenter.endTipsTimer();
        super.onPause();
    }

    @Override
    protected void onResume() {
        mainPresenter.startTipsTimer();
        super.onResume();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        mainPresenter.resetTipsTimer();
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void showTipsView() {
        Intent intent = new Intent(this, VideoBannerActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.alpha_fade_in_slow, R.anim.alpha_fade_out_slow);
    }

}