package com.MyViewApplication.PropertyAnimation.ValueAnimator

import android.animation.TimeInterpolator

/**
 * Created by Jeff on 2020/5/13.
 * 自定义插值器
 **/
class MyInterpolator : TimeInterpolator {

    /**
     * input：动画的进度0~1
     * 返回值：当前实际想要显示的进度，可以小于0，也可以大于1
     */
    override fun getInterpolation(input: Float): Float {
        //所以这里需要数学公式来计算变化趋势
        //这里的意思只是简单的把动画反过来执行
        return 1 - input
    }
}