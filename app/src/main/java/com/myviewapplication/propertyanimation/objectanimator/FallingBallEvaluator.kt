package com.myviewapplication.propertyanimation.objectanimator

import android.animation.TypeEvaluator
import android.graphics.Point

/**
 * Created by dell on 2020/6/13.
 **/
class FallingBallEvaluator : TypeEvaluator<Point> {
    private val point = Point()
    override fun evaluate(fraction: Float, startValue: Point, endValue: Point): Point {
        point.x = (startValue.x + fraction * (endValue.x - startValue.x)).toInt()
        if (fraction * 2 <= 1) {
            point.y = (startValue.y + fraction * 2 * (endValue.y - startValue.y)).toInt()
        } else {
            point.y = endValue.y
        }
        return point
    }
}