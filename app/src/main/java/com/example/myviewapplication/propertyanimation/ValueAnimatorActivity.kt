package com.example.myviewapplication.propertyanimation

import android.animation.Animator
import android.animation.ValueAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.myviewapplication.R
import kotlinx.android.synthetic.main.activity_value_animator.*

/**
 * Created by Jeff on 2020/5/8.
 * 属性动画之ValueAnimator
 **/
class ValueAnimatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_animator)
        doAnimation()
        btn1.setOnClickListener {
            valueAnimator.resume()
            Toast.makeText(this, "click me!", Toast.LENGTH_SHORT).show()
        }
        btnRemove.setOnClickListener {
            valueAnimator.pause()
//            valueAnimator.removeAllUpdateListeners()
        }
    }

    private val valueAnimator = ValueAnimator.ofInt(0, 400) //动画值变化范围
    private fun doAnimation() {
        valueAnimator.duration = 3000
        valueAnimator.repeatCount = ValueAnimator.INFINITE //无限循环
        valueAnimator.repeatMode = ValueAnimator.REVERSE //倒叙循环
        valueAnimator.addUpdateListener { animation ->
            val curValue = animation.animatedValue as Int  // as强转
            btn1.layout(curValue, curValue, btn1.width + curValue, btn1.height + curValue)
//            Log.d("zhang", btn1.top.toString())
        }
        valueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                Log.d("zhang", "repeat")
            }

            override fun onAnimationEnd(animation: Animator?) {
                Log.d("zhang", "end")
            }

            override fun onAnimationCancel(animation: Animator?) {
                Log.d("zhang", "cancel")
            }

            override fun onAnimationStart(animation: Animator?) {
                Log.d("zhang", "start")
            }
        })
        valueAnimator.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        valueAnimator.cancel() //无限循环的属性动画必须要cancel，不然view无法释放、activity无法释放、最终内存泄漏
        picValueAnimator.cancel()
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        /*
         * 在Activity得到或者失去焦点的时候，就会被调用。
         * Activity初始化完毕准备显示的时候就会回调该方法。
         * 所以说，只要想做一些Activity加载完毕就马上触发的事情，都可以在这里执行。
         */
        changePicAnim()
    }

    private val picValueAnimator = ValueAnimator.ofInt(0, 400)

    private fun changePicAnim() {
        picValueAnimator.duration = 3000
        picValueAnimator.repeatCount = ValueAnimator.INFINITE //无限循环
        picValueAnimator.repeatMode = ValueAnimator.REVERSE //倒叙循环

        val initTop = imgChange.top  //onCreate的时候，这些view还没有初始化完毕，所以值都是0，要在onWindowFocusChanged中使用才行
        val initBottom = imgChange.bottom

        picValueAnimator.addUpdateListener { animation ->
            val curValue = animation.animatedValue as Int
            imgChange.layout(imgChange.left, initTop + curValue, imgChange.right, initBottom + curValue)
        }

        var count = 1
        var num = 2
        picValueAnimator.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
                if (++count % 2 == 0) {
                    if (num == 5) num = 1
                    val id = resources.getIdentifier("q$num", "drawable", packageName)
                    imgChange.setImageDrawable(resources.getDrawable(id))
                    num++
                }
            }

            override fun onAnimationEnd(animation: Animator?) {
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
        picValueAnimator.start()
    }

    /*
        一、getLeft()、getRight()、getTop()、getBottom(),是子控件的左右上下边，到父控件的左边及上边的距离。

        二、getWidth() = getRight() - getLeft(); getHeight() = getBottom()-getTop();

     */
}
