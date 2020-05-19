package com.MyViewApplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.MyViewApplication.viewanimation.AnimationCodeActivity
import com.MyViewApplication.viewanimation.AnimationTestActivity
import com.MyViewApplication.activity.BaseDrawActivity
import com.MyViewApplication.kotlin.KotlinTestActivity
import com.MyViewApplication.PropertyAnimation.ValueAnimator.ValueAnimatorActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnBaseDraw.setOnClickListener(this)
        btnAnimationTest.setOnClickListener(this)
        btnAnimationCode.setOnClickListener(this)
        btnValueAnimator.setOnClickListener(this)
    }

    private fun to(cls: Class<*>) = startActivity(Intent(this, cls))

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBaseDraw -> to(BaseDrawActivity::class.java)
            R.id.btnAnimationTest -> to(AnimationTestActivity::class.java)
            R.id.btnAnimationCode -> to(AnimationCodeActivity::class.java)
            R.id.btnValueAnimator -> to(ValueAnimatorActivity::class.java)
        }
    }


    //在xml中引用
    fun kotlinTest(view: View) =  to(KotlinTestActivity::class.java)
}