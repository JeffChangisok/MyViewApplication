package com.myviewapplication.nestedscroll;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.google.android.material.appbar.AppBarLayout;

/**
 * Created by Jeff on 2021/9/23
 */
public class HomeHeaderBehavior extends AppBarLayout.Behavior {
    private static final int INVALID_POINTER = -1;
    private boolean mIsBeingDragged;
    private int mActivePointerId = INVALID_POINTER;
    private int mLastMotionY;
    private int mTouchSlop;
    private MotionEvent mCurrentDownEvent;
    private boolean mNeedDispatchDown;
    private View mScrollingViewBehaviorView;

    public HomeHeaderBehavior(){

    }

    public HomeHeaderBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        if (mTouchSlop < 0) {
            mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
        }
    }

    @Override
    public boolean onInterceptTouchEvent(@NonNull CoordinatorLayout parent, @NonNull AppBarLayout child, @NonNull MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_DOWN: {
                mIsBeingDragged = false;
                mNeedDispatchDown = true;
                final int x = (int) ev.getX();
                final int y = (int) ev.getY();
                if (parent.isPointInChildBounds(child, x, y)) {
                    mLastMotionY = y;
                    mActivePointerId = ev.getPointerId(0);
                    if (mCurrentDownEvent != null) {
                        mCurrentDownEvent.recycle();
                    }
                    mCurrentDownEvent = MotionEvent.obtain(ev);
                }
                break;
            }
            case MotionEvent.ACTION_MOVE: {
                final int activePointerId = mActivePointerId;
                if (activePointerId == INVALID_POINTER) {
                    break;
                }
                final int pointerIndex = ev.findPointerIndex(activePointerId);
                if (pointerIndex == -1) {
                    break;
                }

                final int y = (int) ev.getY(pointerIndex);
                final int yDiff = Math.abs(y - mLastMotionY);
                if (yDiff > mTouchSlop) {
                    mIsBeingDragged = true;
                }
                break;
            }
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP: {
                mIsBeingDragged = false;
                mNeedDispatchDown = true;
                mActivePointerId = INVALID_POINTER;
                break;
            }
        }
        return mIsBeingDragged;
    }

    @Override
    public boolean onTouchEvent(@NonNull CoordinatorLayout parent, @NonNull AppBarLayout child, @NonNull MotionEvent ev) {
        switch (ev.getActionMasked()) {
            case MotionEvent.ACTION_MOVE: {
                if (mIsBeingDragged) {
                    final int offset = child.getHeight() - child.getBottom();
                    if (mNeedDispatchDown){
                        mNeedDispatchDown = false;
                        mCurrentDownEvent.offsetLocation(0, offset);
                        mScrollingViewBehaviorView.dispatchTouchEvent(mCurrentDownEvent);
                    }
                    ev.offsetLocation(0, offset);
                    mScrollingViewBehaviorView.dispatchTouchEvent(ev);
                    return true;
                }
                break;
            }
            case MotionEvent.ACTION_UP:
                if (mIsBeingDragged) {
                    ev.offsetLocation(0, child.getHeight() - child.getBottom());
                    mScrollingViewBehaviorView.dispatchTouchEvent(ev);
                    return true;
                }
                break;
        }
        return false;
    }

    @Override
    public boolean onLayoutChild(@NonNull CoordinatorLayout parent, @NonNull AppBarLayout abl, int layoutDirection) {
        for (int i = 0, c = parent.getChildCount() ; i < c; i++) {
            View sibling = parent.getChildAt(i);
            final CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) sibling.getLayoutParams()).getBehavior();
            if (behavior != null && behavior instanceof AppBarLayout.ScrollingViewBehavior) {
                mScrollingViewBehaviorView = sibling;
            }
        }
        return super.onLayoutChild(parent, abl, layoutDirection);
    }

    private int mLastDy = 0;

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout parent, @NonNull AppBarLayout child, @NonNull View directTargetChild, View target, int nestedScrollAxes, int type) {
        mLastDy = 0;
        return super.onStartNestedScroll(parent, child, directTargetChild, target, nestedScrollAxes, type);
    }

    @Override
    public void onNestedPreScroll(CoordinatorLayout coordinatorLayout, @NonNull AppBarLayout child, View target, int dx, int dy, int[] consumed, int type) {
        if (mLastDy * dy < 0) {
            mLastDy = dy;
            consumed[1] = dy;
            return;
        }
        mLastDy = dy;
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type);
    }



}
