package com.myviewapplication.propertyanimation.valueanimator

import android.animation.Animator
import android.animation.ValueAnimator
import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

/**
 * Created by Jeff on 2020/5/9.
 *
 * 上下位移，到达底部换图片的ImageView
 *
 **/
class ChangeImageView(context: Context, attrs: AttributeSet) : AppCompatImageView(context, attrs) {

    private var mTop = 0
    private var mBottom = 0

    /**
     * ChangeImageView的构造方法
     */
    init {
        init()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        mTop = top
        mBottom = bottom
    }

    private fun init(){
        var count = 1
        var num = 2
        val valueAnimator = ValueAnimator.ofInt(0,400) //动画区间值0-400
        valueAnimator.duration = 3000
        valueAnimator.repeatMode = ValueAnimator.REVERSE
        valueAnimator.repeatCount = ValueAnimator.INFINITE
        valueAnimator.addUpdateListener {
            val curValue = valueAnimator.animatedValue as Int
            top = mTop + curValue
            bottom = mBottom + curValue
        }
        valueAnimator.addListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
                if (++count % 2 == 0) {
                    if (num == 5) num = 1
                    val id = resources.getIdentifier("q$num", "drawable", context.packageName)
                    setImageDrawable(resources.getDrawable(id))
                    num++
                }
            }

            override fun onAnimationEnd(animation: Animator?) {}

            override fun onAnimationCancel(animation: Animator?) {}

            override fun onAnimationStart(animation: Animator?) {}
        })
        valueAnimator.start()

    }
}