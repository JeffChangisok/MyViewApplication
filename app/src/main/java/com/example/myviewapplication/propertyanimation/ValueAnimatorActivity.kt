package com.example.myviewapplication.propertyanimation

import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.myviewapplication.R
import kotlinx.android.synthetic.main.activity_value_animator.*

class ValueAnimatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_animator)
        btn1.setOnClickListener {
            Toast.makeText(this, "click me!", Toast.LENGTH_SHORT).show()
        }
        doAnimation()
    }

    private val valueAnimator = ValueAnimator.ofInt(0, 400)

    private fun doAnimation() {

        valueAnimator.duration = 3000
        valueAnimator.repeatCount = ValueAnimator.INFINITE //无限循环
        valueAnimator.repeatMode = ValueAnimator.REVERSE //倒叙循环
        valueAnimator.addUpdateListener { animation ->
            val curValue = animation.animatedValue as Int  // as强转
            btn1.layout(curValue, curValue, btn1.width + curValue, btn1.height + curValue)
        }
        valueAnimator.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        valueAnimator.cancel() //无限循环的属性动画必须要cancel，不然view无法释放、activity无法释放、最终内存泄漏
    }

}
