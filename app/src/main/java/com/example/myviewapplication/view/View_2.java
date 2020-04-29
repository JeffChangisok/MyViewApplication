package com.example.myviewapplication.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;
import android.graphics.RegionIterator;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * Region
 */
public class View_2 extends View {
    Paint paint;
    Path path;
    Region region;//区域
    Region smallRegion;

    public View_2(Context context) {
        super(context);
    }

    public View_2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public View_2(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    void init() {
        paint = new Paint();
        paint = new Paint();
        region = new Region();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);

        region.set(new Rect(50, 100, 300, 500));
        drawRegion(canvas, region, paint);

        //构造椭圆路径
        //构建椭圆path
        RectF rectF = new RectF(100, 300, 980, 500);
        path.addOval(rectF, Path.Direction.CCW);//Path.Direction.CCW:逆时针;Path.Direction.CW:顺时针
        //构建Region
        region.set(540, 300, 980, 500);
        //取path和region的交集
        Region rgn = new Region();
        rgn.setPath(path, region);
        drawRegion(canvas, rgn, paint);

        region.set(10, 10, 200, 200);
        region.union(new Rect(10, 10, 50, 300));
        //取并集
        drawRegion(canvas, region, paint);

        Rect rect1 = new Rect(100, 100, 400, 200);
        Rect rect2 = new Rect(200, 0, 300, 300);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawRect(rect1, paint);
        canvas.drawRect(rect2, paint);
        Region region1 = new Region(rect1);
        Region region2 = new Region(rect2);
        /*
         * INTERSECT 交集
         * DIFFERENCE 补集
         * REPLACE 替换
         * REVERSE_DIFFERENCE 反转补集
         * UNION 并集
         * XOR XOR 异并集
         */
        region.op(region1, region2, Region.Op.INTERSECT);//region1和region2的交集赋值给region
        paint.setStyle(Paint.Style.FILL);
        drawRegion(canvas, region, paint);

    }

    /**
     * canvas没有drawRegion，说明Region本意不是用来画图的，完全可以用Rect来代替
     * 可惜没效果
     */
    void drawRegion(Canvas canvas, Region region, Paint paint) {
        RegionIterator regionIterator = new RegionIterator(region);
        Rect rect = new Rect();
        while (regionIterator.next(rect)) {
            canvas.drawRect(rect, paint);
        }
    }
}
