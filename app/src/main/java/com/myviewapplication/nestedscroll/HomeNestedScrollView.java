package com.myviewapplication.nestedscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

/**
 * Created by Jeff on 2021/9/24
 */
public class HomeNestedScrollView extends NestedScrollView {

    public HomeNestedScrollView(@NonNull Context context) {
        super(context);
    }

    public HomeNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HomeNestedScrollView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onNestedPreScroll(@NonNull View target, int dx, int dy, @NonNull int[] consumed, int type) {
//                int headerViewHeight = ((LinearLayout) getChildAt(0)).getChildAt(0).getMeasuredHeight();
        int headerViewHeight = getChildAt(0).getMeasuredHeight() - getMeasuredHeight();
        boolean isNeedToHideTop = dy > 0 && getScrollY() < headerViewHeight;
        if (isNeedToHideTop) {
            scrollBy(0, dy);
            consumed[1] = dy;
        }
    }

    @Override
    public void fling(int velocityY) {
        super.fling(velocityY);
        if (velocityY > 0) {
            ViewPager2 viewPager2 = getChildView(this, ViewPager2.class);
            if (viewPager2 != null) {
                RecyclerView childRecyclerView = getChildView((ViewGroup)viewPager2.getChildAt(0), RecyclerView.class);
                if (childRecyclerView != null) {
                    childRecyclerView.fling(0, velocityY);
                }
            }
        }
    }

    private <T> T getChildView(View viewGroup, Class<T> targrtClass) {
        if (viewGroup != null && viewGroup.getClass() == targrtClass) {
            return (T) viewGroup;
        }
        if (viewGroup instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) viewGroup).getChildCount(); i++) {
                View view = ((ViewGroup) viewGroup).getChildAt(i);
                if (view instanceof ViewGroup) {
                    T result = getChildView(view, targrtClass);
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        return null;
    }
}
