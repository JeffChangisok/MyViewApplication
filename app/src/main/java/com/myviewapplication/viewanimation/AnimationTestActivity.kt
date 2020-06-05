package com.myviewapplication.viewanimation

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.myviewapplication.R

/**
 * Created by Jeff on 2020/4/28.
 * 视图动画标签实现
 **/
class AnimationTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_test)
        findViewById<TextView>(R.id.tvAlpha).animation = AnimationUtils.loadAnimation(this, R.anim.alphaanim)
        findViewById<TextView>(R.id.tvScale).animation = AnimationUtils.loadAnimation(this, R.anim.scaleanim)
        findViewById<TextView>(R.id.tvRotate).animation = AnimationUtils.loadAnimation(this, R.anim.rotateanim)
        findViewById<TextView>(R.id.tvTranslate).animation = AnimationUtils.loadAnimation(this, R.anim.translateanim)
        findViewById<Button>(R.id.btnSetAnim).animation = AnimationUtils.loadAnimation(this, R.anim.setanim)
    }
}