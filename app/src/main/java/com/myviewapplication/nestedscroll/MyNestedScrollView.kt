package com.myviewapplication.nestedscroll

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.LinearLayout
import androidx.core.view.ViewParentCompat
import androidx.core.widget.NestedScrollView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2

/**
 * Created by Jeff on 2021/9/24
 */
class MyNestedScrollView : NestedScrollView {

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    companion object {
        const val TAG = "zhang"
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
//        Log.d(TAG, "onMeasure: measuredHeight = $measuredHeight")
    }

    override fun onNestedPreScroll(target: View, dx: Int, dy: Int, consumed: IntArray, type: Int) {
        val headerViewHeight = getChildAt(0).measuredHeight - measuredHeight
        //向上滑动，若当前headerView可见，先把headerView滑动至不可见
        val isNeedToHideTop = dy > 0 && scrollY < headerViewHeight
        if (isNeedToHideTop) {
            scrollBy(0, dy)
            consumed[1] = dy //表示dy用掉了，这个值是会带回给recyclerview的
        }
    }

    override fun fling(velocityY: Int) {
        super.fling(velocityY) //先让自己滑完
        if (velocityY > 0) {
            getChildView(this, ViewPager2::class.java)?.let { viewPager2 ->
                getChildView(viewPager2.getChildAt(0), RecyclerView::class.java)?.fling(
                    0,
                    velocityY
                )
            }

        }
    }

    private fun <T> getChildView(viewGroup: View?, targetClass: Class<T>): T? {
        viewGroup?.let {
            if (it.javaClass == targetClass) {
                return it as T
            }
            if (it is ViewGroup) {
                for (i in 0..it.childCount) {
                    it.getChildAt(i).also { child ->
                        if (child is ViewGroup) {
                            getChildView(child, targetClass)?.let { resultChild ->
                                return resultChild
                            }
                        }
                    }
                }
            }
        }
        return null
    }

}