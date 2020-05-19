package com.MyViewApplication.PropertyAnimation.ValueAnimator

import android.animation.TypeEvaluator

/**
 * Created by Jeff on 2020/5/19.
 **/
class MyEvaluator : TypeEvaluator<Int> {
    /**
     * fraction：从插值器传来的值
     * startValue、endValue：声明ValueAnimator时传的取值范围参数
     */
    override fun evaluate(fraction: Float, startValue: Int, endValue: Int): Int {
        return (startValue + (endValue - startValue) * fraction).toInt() + 200//在正常值上加了200
    }
}