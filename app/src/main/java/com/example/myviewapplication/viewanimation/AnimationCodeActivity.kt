package com.example.myviewapplication.viewanimation

import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.*
import com.example.myviewapplication.R
import kotlinx.android.synthetic.main.activity_animation_code.*
import kotlinx.android.synthetic.main.activity_animation_code.btnSetAnim
import kotlinx.android.synthetic.main.activity_animation_code.tvAlpha
import kotlinx.android.synthetic.main.activity_animation_code.tvRotate
import kotlinx.android.synthetic.main.activity_animation_code.tvScale
import kotlinx.android.synthetic.main.activity_animation_code.tvTranslate

/**
 * Created by Jeff on 2020/4/29.
 * 视图动画代码实现
 **/
class AnimationCodeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_code)
//        val tvAlpha = findViewById<TextView>(R.id.tvAlpha)
//        val tvRotate = findViewById<TextView>(R.id.tvRotate)
//        val tvScale = findViewById<TextView>(R.id.tvScale)
//        val tvTranslate = findViewById<TextView>(R.id.tvTranslate)
//        val btnAnimSet = findViewById<Button>(R.id.btnSetAnim)

//        val view1 = findViewById<View>(R.id.view1)
//        val view2 = findViewById<View>(R.id.view2)
//        val view3 = findViewById<View>(R.id.view3)
//        val view4 = findViewById<View>(R.id.view4)
        //可以直接引用layout中的id，不用find

        loadingAnim()

        scanAnim()

        val alphaAnim = AlphaAnimation(1.0f, 0.0f)
        alphaAnim.duration = 4000
        alphaAnim.repeatCount = Animation.INFINITE
        alphaAnim.repeatMode = Animation.REVERSE
        alphaAnim.interpolator = AccelerateDecelerateInterpolator() //加速减速插值器
        tvAlpha.startAnimation(alphaAnim)

        val rotateAnim = RotateAnimation(0f, 270f)
        rotateAnim.duration = 2000
        rotateAnim.repeatCount = Animation.INFINITE
        rotateAnim.repeatMode = Animation.REVERSE
        rotateAnim.interpolator = AnticipateOvershootInterpolator()//开始时结束时都有小偏移
        tvRotate.startAnimation(rotateAnim)

        val scaleAnim = ScaleAnimation(1.0f, 2.0f, 1.0f, 2.0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f)
        scaleAnim.duration = 2000
        scaleAnim.repeatCount = Animation.INFINITE
        scaleAnim.repeatMode = Animation.REVERSE
        scaleAnim.interpolator = LinearInterpolator() //匀速插值器
        tvScale.startAnimation(scaleAnim)

        val translateAnim = TranslateAnimation(0f, 150f, 0f, 300f)
        translateAnim.duration = 2000
        translateAnim.repeatCount = Animation.INFINITE
        translateAnim.repeatMode = Animation.REVERSE
        translateAnim.interpolator = BounceInterpolator() //结束时像落地小球一样回弹
        tvTranslate.startAnimation(translateAnim)

//        val setAnimation = AnimationSet(true)  //true:旗下所有动画公用插值器，false:都用自己的
//        setAnimation.addAnimation(alphaAnim)
//        setAnimation.addAnimation(rotateAnim)
//        setAnimation.addAnimation(scaleAnim)
//        setAnimation.addAnimation(translateAnim)
//        btnAnimSet.startAnimation(setAnimation)

        val translateAnim2 = TranslateAnimation(Animation.RELATIVE_TO_PARENT, 0F, Animation.RELATIVE_TO_PARENT, 0.7F,
                Animation.RELATIVE_TO_PARENT, 0f, Animation.RELATIVE_TO_PARENT, 0f)
        translateAnim2.fillAfter = true
        translateAnim2.duration = 2000
        translateAnim2.interpolator = CycleInterpolator(3.0f)  //控制循环次数的插值器，进度依照正弦曲线


        val rotateAnim2 = RotateAnimation(0f, 270f)
        rotateAnim2.fillAfter = true
        rotateAnim2.duration = 1000

        btnSetAnim.startAnimation(translateAnim2)

        //通过匿名类方式注册动画监听，object和java中的new差不多
        translateAnim2.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {}

            override fun onAnimationEnd(animation: Animation?) {
                btnSetAnim.startAnimation(rotateAnim2)
            }

            override fun onAnimationStart(animation: Animation?) {}
        })

        /*
         * tips:
         * 视图动画进行、结束时，他的layout和响应区域都在原地没动，我们看到在动的只是用于显示的视图
         * 所以可以在动画结束后，把layout手动设置到终点处
         *
         * 属性动画进行、结束时，他的响应区域也跟着视图动了，getTop也在动
         */
    }

    /**
     * 扫描效果
     */
    private fun scanAnim() {
        val animationSet1 = AnimationUtils.loadAnimation(this, R.anim.scale_alpha_anim)

        val animationSet2 = AnimationUtils.loadAnimation(this, R.anim.scale_alpha_anim)
        animationSet2.startOffset = 600

        val animationSet3 = AnimationUtils.loadAnimation(this, R.anim.scale_alpha_anim)
        animationSet3.startOffset = 1200

        val animationSet4 = AnimationUtils.loadAnimation(this, R.anim.scale_alpha_anim)
        animationSet4.startOffset = 1800

        view1.startAnimation(animationSet1)
        view2.startAnimation(animationSet2)
        view3.startAnimation(animationSet3)
        view4.startAnimation(animationSet4)
    }

    /**
     * 帧动画
     * 代码实现
     */
    private fun loadingAnim() {
        val animationDrawable = AnimationDrawable()
        for (i in 1 until 18) {
            val drawableId = resources.getIdentifier("loading$i", "drawable", packageName)
            val drawable = resources.getDrawable(drawableId)
            animationDrawable.addFrame(drawable, 60) // duration:这帧动画持续时间
        }
        animationDrawable.isOneShot = false //一直循环
        imgLoading.setBackgroundDrawable(animationDrawable)
        animationDrawable.start()

        for (i in 1..17) {

        }
    }
}
