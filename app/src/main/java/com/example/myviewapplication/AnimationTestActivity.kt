package com.example.myviewapplication

import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class AnimationTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation_test)
        findViewById<TextView>(R.id.tvAlpha).animation = AnimationUtils.loadAnimation(this,R.anim.alphaanim)
        findViewById<TextView>(R.id.tvScale).animation = AnimationUtils.loadAnimation(this,R.anim.scaleanim)
        findViewById<TextView>(R.id.tvRotate).animation = AnimationUtils.loadAnimation(this,R.anim.rotateanim)
        findViewById<TextView>(R.id.tvTranslate).animation = AnimationUtils.loadAnimation(this,R.anim.translateanim)
        findViewById<Button>(R.id.btnSetAnim).animation = AnimationUtils.loadAnimation(this,R.anim.setanim)
    }
}