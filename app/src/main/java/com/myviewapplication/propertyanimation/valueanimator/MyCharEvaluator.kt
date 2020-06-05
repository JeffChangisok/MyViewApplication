package com.myviewapplication.propertyanimation.valueanimator

import android.animation.TypeEvaluator

/**
 * Created by Jeff on 2020/5/19.
 * 返回值类型是Char的Evaluator
 **/
class MyCharEvaluator : TypeEvaluator<Char> {
    override fun evaluate(fraction: Float, startValue: Char, endValue: Char): Char {
        val startASCII = startValue.toInt()
        val endASCII = endValue.toInt()
        return ((startASCII + (endASCII - startASCII) * fraction).toInt()).toChar()
    }
}