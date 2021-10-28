package com.myviewapplication.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Created by Jeff on 2021/10/25
 */
public class myview extends View {


    public myview(Context context) {
        super(context);
    }

    public myview(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public myview(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public myview(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

}
