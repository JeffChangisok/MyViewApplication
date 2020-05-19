package com.MyViewApplication.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.MyViewApplication.R;

import androidx.annotation.Nullable;

public class CircleView extends View {

    private int mColor;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private static final String TAG = "zhang";

    /**
     * 在代码中new，会调用这个
     */
    public CircleView(Context context) {
        super(context);
    }

    /**
     * 在xml中定义，会调用这个
     * @param attrs 在xml中定义的属性值
     */
    public CircleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 相对第二个来说，给了一个默认属性
     * 如Button会给到com.android.internal.R.attr.buttonStyle
     * @param defStyleAttr 默认属性值，没给的话就是0
     */
    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        mColor = typedArray.getColor(R.styleable.CircleView_circle_color, Color.RED); //拿自定义属性
        typedArray.recycle();
    }

    /**
     * 相对第三个来说，又多了一个默认属性
     * @param defStyleRes 只有当defStyleAttr为0时才生效，给R.style.what。没给为0
     */
    public CircleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    private void init() {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        Log.d(TAG, "onDraw: paddingLeft " + paddingLeft);
        Log.d(TAG, "onDraw: paddingTop " + paddingTop);
        Log.d(TAG, "onDraw: paddingRight " + paddingRight);
        Log.d(TAG, "onDraw: paddingBottom " + paddingBottom);

        int width = getWidth() - paddingLeft - paddingRight;
        int height = getHeight() - paddingTop - paddingBottom;
        int radius = Math.min(width, height) / 2;

        Log.d(TAG, "onDraw: getWidth() = " + getWidth());
        Log.d(TAG, "onDraw: getHeight() = " + getHeight());

        Log.d(TAG, "onDraw: width " + width);
        Log.d(TAG, "onDraw: height " + height);

        Log.d(TAG, "x: " + (width / 2 + paddingLeft - paddingRight));
        Log.d(TAG, "y: " + (height / 2 + paddingTop - paddingBottom));

        mPaint.setColor(mColor);
        canvas.drawCircle(getWidth() / 2 + paddingLeft - paddingRight, getHeight() / 2 + paddingTop - paddingBottom, radius, mPaint);
        mPaint.setColor(Color.YELLOW);
        canvas.drawCircle(getWidth() / 2 + paddingLeft - paddingRight, getHeight() / 2 + paddingTop - paddingBottom, 10, mPaint);
    }
}
