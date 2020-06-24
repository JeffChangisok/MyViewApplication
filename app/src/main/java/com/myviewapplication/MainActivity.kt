package com.myviewapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.myviewapplication.viewanimation.AnimationCodeActivity
import com.myviewapplication.viewanimation.AnimationTestActivity
import com.myviewapplication.activity.BaseDrawActivity
import com.myviewapplication.kotlin.KotlinTestActivity
import com.myviewapplication.propertyanimation.objectanimator.ObjectAnimatorActivity
import com.myviewapplication.propertyanimation.valueanimator.ValueAnimatorActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnBaseDraw.setOnClickListener(this)
        btnAnimationTest.setOnClickListener(this)
        btnAnimationCode.setOnClickListener(this)
        btnValueAnimator.setOnClickListener(this)
        btnObjectAnimator.setOnClickListener(this)
    }

    private fun to(cls: Class<*>) = startActivity(Intent(this, cls))

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBaseDraw -> to(BaseDrawActivity::class.java)
            R.id.btnAnimationTest -> to(AnimationTestActivity::class.java)
            R.id.btnAnimationCode -> to(AnimationCodeActivity::class.java)
            R.id.btnValueAnimator -> to(ValueAnimatorActivity::class.java)
            R.id.btnObjectAnimator -> to(ObjectAnimatorActivity::class.java)
        }
    }


    //在xml中引用
    fun kotlinTest(view: View) = to(KotlinTestActivity::class.java)
}
