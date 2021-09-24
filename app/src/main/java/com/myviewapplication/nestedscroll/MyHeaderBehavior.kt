package com.myviewapplication.nestedscroll

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import kotlin.math.abs

/**
 * Created by Jeff on 2021/9/22
 */
class MyHeaderBehavior : AppBarLayout.Behavior {

    private val INVALID_POINTER = -1
    private var mIsBeingDragged = false
    private var mActivePointerId = INVALID_POINTER
    private var mLastMotionY = 0
    private var mTouchSlop = -1
    private var mCurrentDownEvent: MotionEvent? = null
    private var mNeedDispatchDown = false
    private var mScrollingViewBehaviorView: View? = null

    constructor() {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        if (mTouchSlop < 0) {
            mTouchSlop = ViewConfiguration.get(context).scaledTouchSlop
        }
    }

    override fun onInterceptTouchEvent(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        ev: MotionEvent
    ): Boolean {
        when (ev.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                mIsBeingDragged = false
                mNeedDispatchDown = true
                val x = ev.x.toInt()
                val y = ev.y.toInt()
                if (parent.isPointInChildBounds(child, x, y)) {
                    mLastMotionY = y
                    mActivePointerId = ev.getPointerId(0)
                    mCurrentDownEvent?.recycle()
                    mCurrentDownEvent = MotionEvent.obtain(ev)
                }
            }
            MotionEvent.ACTION_MOVE -> {
                val activePointerId = mActivePointerId
                if (activePointerId == INVALID_POINTER) {
                    return mIsBeingDragged
                }
                val pointerIndex = ev.findPointerIndex(activePointerId)
                if (pointerIndex == -1) {
                    return mIsBeingDragged
                }
                val y = ev.getY(pointerIndex).toInt()
                val yDiff = abs(y - mLastMotionY)
                if (yDiff > mTouchSlop) {
                    mIsBeingDragged = true
                }
            }
            MotionEvent.ACTION_CANCEL, MotionEvent.ACTION_UP -> {
                mIsBeingDragged = false
                mNeedDispatchDown = true
                mActivePointerId = INVALID_POINTER
            }
        }
        return mIsBeingDragged
    }

    override fun onTouchEvent(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        ev: MotionEvent
    ): Boolean {
        when (ev.actionMasked) {
            MotionEvent.ACTION_MOVE -> {
                if (mIsBeingDragged) {
                    val offset = child.height - child.bottom
                    if (mNeedDispatchDown) {
                        mNeedDispatchDown = false
                        mCurrentDownEvent?.offsetLocation(0f, offset.toFloat())
                        mScrollingViewBehaviorView?.dispatchTouchEvent(mCurrentDownEvent)
                    }
                    ev.offsetLocation(0f, offset.toFloat())
                    mScrollingViewBehaviorView?.dispatchTouchEvent(ev)
                    return true
                }
            }
            MotionEvent.ACTION_UP -> {
                if (mIsBeingDragged) {
                    ev.offsetLocation(0f, (child.height - child.bottom).toFloat())
                    mScrollingViewBehaviorView?.dispatchTouchEvent(ev)
                    return true
                }
            }
        }
        return false
    }

    override fun onLayoutChild(
        parent: CoordinatorLayout,
        abl: AppBarLayout,
        layoutDirection: Int
    ): Boolean {
        val c = parent.childCount
        for (i in 0..c) {
            parent.getChildAt(i)?.let { sibling ->
                val behavior = (sibling.layoutParams as CoordinatorLayout.LayoutParams).behavior
                behavior?.let {
                    if (behavior is AppBarLayout.ScrollingViewBehavior) {
                        mScrollingViewBehaviorView = sibling
                    }
                }
            }

        }
        return super.onLayoutChild(parent, abl, layoutDirection)
    }

    private var mLastDy = 0

    override fun onStartNestedScroll(
        parent: CoordinatorLayout,
        child: AppBarLayout,
        directTargetChild: View,
        target: View,
        nestedScrollAxes: Int,
        type: Int
    ): Boolean {
        mLastDy = 0
        return super.onStartNestedScroll(
            parent,
            child,
            directTargetChild,
            target,
            nestedScrollAxes,
            type
        )
    }

    override fun onNestedPreScroll(
        coordinatorLayout: CoordinatorLayout,
        child: AppBarLayout,
        target: View,
        dx: Int,
        dy: Int,
        consumed: IntArray,
        type: Int
    ) {
        if (mLastDy * dy < 0) {
            mLastDy = dy
            consumed[1] = dy
            return
        }
        mLastDy = dy
        super.onNestedPreScroll(coordinatorLayout, child, target, dx, dy, consumed, type)
    }
}