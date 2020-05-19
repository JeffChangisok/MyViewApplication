package com.MyViewApplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Paint
 */
public class View_1 extends View {
    Paint paint;//画笔
    Rect rect;//整型矩形
    Path path;//路径
    RectF rectF;//浮点矩形

    public View_1(Context context) {
        super(context);
    }

    /**
     * 在xml中生声明时用
     */
    public View_1(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public View_1(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    void init() {
        paint = new Paint();
        rect = new Rect();
        path = new Path();
        rectF = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawARGB(136, 136, 136, 136);
        canvas.drawColor(0x88888888);//画背景

//        path.moveTo(800,1200);//直接moveTo是无效的

        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        canvas.drawCircle(150, 150, 50, paint);//画圆框

        paint.setStrokeWidth(20);
        canvas.drawPoint(150, 150, paint);//画点

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        //上面有边框，下面也有边框
        //下面的边框贴着上面内心：按正常逻辑写,就是250（加了两个半径--150 + 50 + 50）
        //下面的边框贴着上面的边框：需要在他的内心底--200--的基础上加上他的边框
        canvas.drawCircle(150, 250 + 20, 50, paint);//画实心圆+边框

        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(150, 250 + 20, 50, paint);//画实心圆

        paint.setColor(Color.YELLOW);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(20);
        //自己有边框时，需要多留出自己边框的一半

        rect.set(150 + 20 / 2, 500, 350 + (20 / 2), 700);
        canvas.drawRect(rect, paint);//画矩形框

        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.FILL);
        //这里的20是上面那个矩形的边框的20
        //上面有边框，下面没边框
        //贴着边框：需要在他的内心底--700--的基础上加上他的边框的一半
        //贴着内心：需要在他的内心底--700--的基础上减去他的边框的一半
        canvas.drawRect(150, 700 + (20 / 2), 350, 900, paint);//画实心矩形

        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);//fill的话就会成填充哦
        paint.setStrokeWidth(10);
        path.moveTo(400, 400);
        path.lineTo(800, 600);
        path.lineTo(800, 500);
        path.close();//自动连接首尾，形成闭环
        canvas.drawPath(path, paint);

        /*
         * rectF 生成椭圆的矩形
         * startAngle 弧开始的角度（X轴正方向为0°）
         * sweepAngle 弧持续的角度
         * forceMoveTo指将弧的起始点作为绘制起始位置，但这里似乎没有效果
         */
        rectF.set(100, 10, 200, 100);
        path.arcTo(rectF, 0, 90, true);
        canvas.drawPath(path, paint);

        //对边框的解释：setStrokeWidth(20) 之后，边框是分为外边框和内边框各占一半
        //图形在边缘时，外边框由于是向外延申，所以被裁剪，就只会剩下一半
        paint.setStrokeWidth(40);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.GREEN);
//        rect.inset(20,20);//没效果
        rect.set(0, 1000, 600, 1800);
        canvas.drawRect(rect, paint);

        paint.setStrokeWidth(1);
        paint.setColor(Color.RED);
        canvas.drawRect(rect, paint);


    }


}
