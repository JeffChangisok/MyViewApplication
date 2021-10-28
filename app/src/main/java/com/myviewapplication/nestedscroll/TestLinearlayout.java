package com.myviewapplication.nestedscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

/**
 * Created by Jeff on 2021/9/26
 */
public class TestLinearlayout extends LinearLayout {
    public TestLinearlayout(Context context) {
        super(context);
    }

    public TestLinearlayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestLinearlayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public TestLinearlayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("zhang", "onMeasure: NestedScrollView里的LinearLayout.MeasureHeight" + getMeasuredHeight());
    }
}
