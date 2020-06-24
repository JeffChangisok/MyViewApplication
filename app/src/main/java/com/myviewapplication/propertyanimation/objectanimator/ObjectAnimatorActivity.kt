package com.myviewapplication.propertyanimation.objectanimator

import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.graphics.Point
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.myviewapplication.R
import kotlinx.android.synthetic.main.activity_object_animator.*

/**
 * Created by Jeff on 2020/5/20.
 **/
class ObjectAnimatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_object_animator)

        /*
         * 如何确定第二个参数propertyName
         * 他会去第一个参数类型中去找对应的set函数
         * 例如，"alpha"，就去找setAlpha
         */
        val alphaAnimator = ObjectAnimator.ofFloat(btn1, "alpha", 1f, 0f, 1f)
        alphaAnimator.duration = 2000
        alphaAnimator.repeatCount = 5
        alphaAnimator.start()
        //setRotation 绕Z轴旋转，除此之外还有X、Y
        val rotationAnimator = ObjectAnimator.ofFloat(btn2, "rotation", 0f, 180f, 0f)
        rotationAnimator.duration = 2000
        rotationAnimator.repeatCount = 5
        rotationAnimator.start()
        //自定义动画
        val myAnimator = ObjectAnimator.ofObject(btnFallingBall, "FallingPos", FallingBallEvaluator(), Point(0, 0), Point(500, 500))
        myAnimator.duration = 3000
        myAnimator.start()
        /*
         * 需要注意的地方，关于何时实现get函数
         * 只给动画设置一个值的时，程序会调用属性对应的get函数来得到动画初始值。没初始值就会用系统默认值，一般是0（所以可以重写get函数更改默认值）
         * 但如果是自定义的动画参数，没有get函数，也没有默认值，就会报错。
         */
        val scaleAnimator = ObjectAnimator.ofFloat(btn3, "scale", 6f)
        scaleAnimator.duration = 3000
        scaleAnimator.repeatMode = ValueAnimator.REVERSE
        scaleAnimator.repeatCount = 5
        scaleAnimator.start()
    }
}
