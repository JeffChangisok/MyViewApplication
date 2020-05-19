package com.MyViewApplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * canvas
 */
public class View_3 extends View {
    Paint paint;
    Rect rect;

    public View_3(Context context) {
        super(context);
    }

    public View_3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public View_3(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    void init() {
        paint = new Paint();
        rect = new Rect();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0x88888888);//画背景
//        translateTest(canvas);
//        clipTest(canvas);
        saveRestoreTest(canvas);
    }

    void translateTest(Canvas canvas) {
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        rect.set(0, 0, 300, 400);
        canvas.drawRect(rect, paint);//每次调用drawXXX，都会产生一个新的canvas，即透明图层，在canvas上画完之后再覆盖再屏幕上

        //对canvas进行旋转平移等操作之后，之后生成的canvas都是变换之后的位置，这个操作是不可逆的
        //canvas与屏幕合成时，超出屏幕范围图像不会显示
        canvas.translate(100, 100);

        paint.setColor(Color.RED);
        canvas.drawRect(rect, paint);
    }

    void clipTest(Canvas canvas) {
        canvas.drawColor(Color.GREEN);
        canvas.clipRect(500, 800, 900, 1200);
        canvas.drawColor(Color.RED);
    }

    void saveRestoreTest(Canvas canvas) {
        paint.setColor(Color.GRAY);

        canvas.drawColor(Color.GREEN);
        canvas.save();//保存当前canvas状态

        canvas.clipRect(500, 800, 900, 1200);//裁canvas
        canvas.drawColor(Color.YELLOW);
        canvas.drawRect(500, 800, 600, 1000, paint);//注意canvas只是被裁剪了，并没有被平移，所以坐标系还是没有动的

        canvas.restore();//恢复之前的canvas状态
        canvas.drawRect(200, 500, 400, 900, paint);
    }
}
