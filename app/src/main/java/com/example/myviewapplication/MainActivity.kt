package com.example.myviewapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.btnBaseDraw).setOnClickListener(this)
        findViewById<Button>(R.id.btnAnimationTest).setOnClickListener(this)
    }

    private fun to(cls: Class<*>) = startActivity(Intent(this, cls))

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnBaseDraw -> to(BaseDrawActivity::class.java)
            R.id.btnAnimationTest -> to(AnimationTestActivity::class.java)
        }
    }


    //在xml中引用
    fun baseDraw(view: View) = to(BaseDrawActivity::class.java)
    fun animationTest(view: View) = to(AnimationTestActivity::class.java)
}
